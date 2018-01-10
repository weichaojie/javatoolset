
package com.huawei.android.xml.settings;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

import android.util.Log;

import xml.settings.util.Stringer;
import com.huawei.android.xml.settings.domain.Settings;
import com.huawei.android.xml.settings.domain.SettingsObject;

/**
 * <p>
 * Parse the a Settings. Documentation on Settings can be found at:
 * 
 * @author weichaojie
 * 
 */
public class SettingsXMLHandler extends DefaultHandler2 {

	public static final java.lang.String TAG = "SettingsXMLHandler";

	/**
	 * Defines the modes the parser reports to registered listeners.
	 * 
	 * @author weichaojie
	 * 
	 */
	public enum ParseMode {
		START_TAG, END_TAG
	};

	/**
	 * Implementors can listen for events defined by {@link ParseMode}.
	 * 
	 * @author weichaojie
	 * 
	 */
	public static interface SettingsParserListener {
		public void onSettingsParseDone(Settings settings, ParseMode mode);
	}

	/**
	 * {@link Stringer} for this class.
	 */
	private Stringer stringer;

	/**
	 * Listener for this parser.
	 */
	private SettingsParserListener parseListener;

	/**
	 * The value of parsed characters from elements and attributes.
	 */
	private Stringer tempVal;

	/**
	 * The parsed {@link Settings}.
	 */
	private Settings settings;

	// Registers to hold state of parsing the workflow as Dict
	protected java.lang.String key;

	/**
	 * 
	 */
	public SettingsXMLHandler() {
		super();
		stringer = new Stringer();
	}

	/**
	 * @return the settings
	 */
	public Settings getSettings() {
		return settings;
	}

	/**
	 * @param settings
	 *            the settings to set
	 */
	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	/**
	 * @return the parseListener
	 */
	public SettingsParserListener getParseListener() {
		return parseListener;
	}

	/**
	 * @param parseListener
	 *            the parseListener to set
	 */
	public void setParseListener(SettingsParserListener parseListener) {
		this.parseListener = parseListener;
	}

	/**
	 * @return the tempVal
	 */
	public Stringer getTempVal() {
		return tempVal;
	}

	/**
	 * @param tempVal
	 *            the tempVal to set
	 */
	public void setTempVal(Stringer tempVal) {
		this.tempVal = tempVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		tempVal = new Stringer();
		settings = null;
		key = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(java.lang.String uri, java.lang.String localName,
			java.lang.String qName, Attributes attributes) throws SAXException {
		Log.v(stringer.newBuilder().append(TAG).append("#startElement")
				.toString(),
				stringer.newBuilder()
						.append("Start Element lname|uri|attr.length :")
						.append(localName).append(Constants.PIPE).append(uri)
						.append(Constants.PIPE).append(attributes.getLength())
						.toString());
		tempVal.newBuilder();
		if (localName.equalsIgnoreCase(Constants.TAG_SETTINGS)) {
			if (null != settings) {
				// there should only be one Settings element in the root
				throw new SAXException(
						"there should only be one Settings element in Settings XML");
			}
			settings = new Settings();
		} else {
			if (null == settings) {
				throw new SAXException(
						"invalid Settings - please see http://www.ultra.com/DTDs/PropertyList-1.0.dtd");
			}
			if (localName.equalsIgnoreCase(Constants.TAG_DICT) || 
					localName.equalsIgnoreCase(Constants.TAG_SETTINGS_ARRAY)) {
				try {
					SettingsObject objToAdd = settings.buildObject(localName, tempVal
							.getBuilder().toString());
					settings.stackObject(objToAdd, key);
				} catch (Exception e) {
					throw new SAXException(e);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		Log.v(stringer.newBuilder().append(TAG).append("#characters")
				.toString(),
				stringer.newBuilder().append(ch).append(Constants.PIPE)
						.append(start).append(Constants.PIPE).append(length)
						.append(Constants.PIPE).toString());
		tempVal.getBuilder().append(new java.lang.String(ch, start, length));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(java.lang.String uri, java.lang.String localName,
			java.lang.String qName) throws SAXException {
		Log.v(stringer.newBuilder().append(TAG).append("#endElement")
				.toString(),
				stringer.newBuilder().append("localName|qName|uri|tempVal: ")
						.append(localName).append(Constants.PIPE).append(qName)
						.append(Constants.PIPE).append(uri)
						.append(Constants.PIPE)
						.append(tempVal.getBuilder().toString()).toString());
		if (localName.equalsIgnoreCase(Constants.TAG_KEY)) {
			key = tempVal.getBuilder().toString().trim();
		} else if (localName.equalsIgnoreCase(Constants.TAG_DICT) || 
				localName.equalsIgnoreCase(Constants.TAG_SETTINGS_ARRAY)) {
			settings.popStack();
		} else if (!localName.equalsIgnoreCase(Constants.TAG_SETTINGS)) {
			try {
				SettingsObject objToAdd = settings.buildObject(localName, tempVal
						.getBuilder().toString());
				settings.stackObject(objToAdd, key);
			} catch (Exception e) {
				throw new SAXException(e);
			}
			key = null;
		} else if (localName.equalsIgnoreCase(Constants.TAG_SETTINGS)) {
			if (null != parseListener) {
				parseListener.onSettingsParseDone(settings, ParseMode.END_TAG);
			}
		}
		tempVal.newBuilder();

	}

}
