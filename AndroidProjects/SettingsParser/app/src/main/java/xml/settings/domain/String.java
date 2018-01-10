package com.huawei.android.xml.settings.domain;

import xml.settings.util.Stringer;

/**
 * Represents a simple settings string element. Not to be confused with
 * {@link java.lang.String}.
 */
public class String extends SettingsObject implements
		ISettingsSimpleObject<java.lang.String> {

	protected Stringer str;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8134261357175236382L;

	public String() {
		setType(SettingsObjectType.STRING);
		str = new Stringer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#getValue()
	 */
	@Override
	public java.lang.String getValue() {
		return this.str.getBuilder().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#setValue
	 * (java.lang.Object)
	 */
	@Override
	public void setValue(java.lang.String val) {
		str.newBuilder().append(val);
	}

}