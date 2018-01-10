package com.huawei.android.xml.settings.domain;

/**
 * Represents a simple settings true element.
 */
public class True extends SettingsObject implements ISettingsSimpleObject<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3560354198720649001L;

	public True() {
		setType(SettingsObjectType.TRUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#getValue()
	 */
	@Override
	public Boolean getValue() {
		return new Boolean(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#setValue
	 * (java.lang.Object)
	 */
	@Override
	public void setValue(Boolean val) {
		// noop
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
		// noop
	}

}