
package com.huawei.android.xml.settings;

import java.io.IOException;
import java.io.InputStream;

import xml.settings.util.Stringer;

/**
 * @author weichaojie
 * 
 */
public class SettingsXMLParser extends BaseXMLParser {

	public static final String TAG = "SettingsXMLParser";

	/**
	 * 
	 */
	public SettingsXMLParser() {
		super();
	}

	/**
	 * Parse a Settings XML document.
	 * 
	 * @param xml
	 */
	public void parse(String xml) throws IllegalStateException {
		SettingsXMLHandler settingsHandler = (SettingsXMLHandler) getHandler();
		if (null == settingsHandler) {
			throw new IllegalStateException(
					"handler is null, must set a document handler before calling parse");
		}
		if (null == xml) {
			settingsHandler.setPlist(null);
			return;
		}
		initParser();
		super.parse(xml);
	}

	/**
	 * Parse a Settings XML document from an {@link InputStream}.
	 * 
	 * @param xml
	 * @throws IOException
	 */
	public void parse(InputStream is) throws IllegalStateException, IOException {
		SettingsXMLHandler settingsHandler = (SettingsXMLHandler) getHandler();
		if (null == settingsHandler) {
			throw new IllegalStateException(
					"handler is null, must set a document handler before calling parse");
		}
		if (null == is) {
			SettingsHandler.setSettings(null);
			return;
		}
		Stringer xml = null;
		try {
			xml = Stringer.convert(is);
		} catch (IOException e) {
			throw new IOException(
					"error reading from input string - is it encoded as UTF-8?");
		}
		initParser();
		super.parse(xml.getBuilder().toString());
	}

}
