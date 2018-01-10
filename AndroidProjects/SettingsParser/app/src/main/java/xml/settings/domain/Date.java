
package com.huawei.android.xml.settings.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import android.util.Log;

/**
 * Represents a simple settings date elements.
 */
public class Date extends SettingsObject implements
		ISettingsSimpleObject<java.util.Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3846688440069431376L;

	private static final java.lang.String TAG = "Date";

	/**
	 * The parsed date object.
	 */
	protected java.util.Date date;

	/**
	 * Used for parsing ISO dates.
	 */
	private SimpleDateFormat iso8601Format;

	public Date() {
		setType(SettingsObjectType.DATE);
		iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#getValue()
	 */
	@Override
	public java.util.Date getValue() {
		return (java.util.Date) date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#setValue
	 * (java.lang.Object)
	 */
	@Override
	public void setValue(java.util.Date val) {
		this.date = val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#setValue
	 * (java.lang.String)
	 */
	@Override
	public void setValue(java.lang.String val) {
		// sniff date
		if (null == val || val.length() < 1) {
			this.date = null;
			return;
		}
		Scanner scanner = new java.util.Scanner(val).useDelimiter("-");
		if (scanner.hasNextInt()) {
			try {
				this.date = iso8601Format.parse(val);
			} catch (ParseException e) {
				Log.e(TAG, new StringBuilder("#setValue - error parsing val=")
						.append(val).toString(), e);
			}
		} else {
			this.date = new java.util.Date(java.util.Date.parse(val.trim()));
		}
	}
}