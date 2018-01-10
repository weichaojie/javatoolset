
package com.huawei.android.xml.settings.domain;

/**
 * Represents a simple settings real element.
 */
public class Real extends SettingsObject implements ISettingsSimpleObject<Float> {

	// TODO: Double?
	protected Float real;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4204214862534504729L;

	public Real() {
		setType(SettingsObjectType.REAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#getValue()
	 */
	@Override
	public Float getValue() {
		return real;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#setValue
	 * (java.lang.Object)
	 */
	@Override
	public void setValue(Float val) {
		this.real = val;
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
		this.real = new Float(Float.parseFloat(val.trim()));
	}
}