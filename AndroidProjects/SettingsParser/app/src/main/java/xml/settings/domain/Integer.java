
package com.huawei.android.xml.settings.domain;

/**
 * Represents a simple settings int element.
 */
public class Integer extends SettingsObject implements
		ISettingsSimpleObject<java.lang.Integer> {

	protected java.lang.Integer intgr;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5952071046933925529L;

	public Integer() {
		setType(SettingsObjectType.INTEGER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#getValue()
	 */
	@Override
	public java.lang.Integer getValue() {
		return intgr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#setValue
	 * (java.lang.Object)
	 */
	@Override
	public void setValue(java.lang.Integer val) {
		this.intgr = val;
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
		this.intgr = new java.lang.Integer(java.lang.Integer.parseInt(val
				.trim()));
	}

}