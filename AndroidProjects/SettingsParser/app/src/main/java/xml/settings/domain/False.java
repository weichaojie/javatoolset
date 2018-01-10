
package com.huawei.android.xml.settings.domain;

/**
 * Represents a simple settings false element.
 */
public class False extends SettingsObject implements ISettingsSimpleObject<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8533886020773567552L;

	public False() {
		setType(SettingsObjectType.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huawei.android.xml.settings.domain.ISettingsSimpleObject#getValue()
	 */
	@Override
	public Boolean getValue() {
		return new Boolean(false);
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