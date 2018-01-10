
package com.huawei.android.xml.settings.domain;

import java.io.Serializable;

/**
 * A SettingsObject is an object which has a valid {@link SettingsObjectType}.
 */
public class SettingsObject extends Object implements Cloneable,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5258056855425643835L;

	private SettingsObjectType type;

	/**
	 * @return the type
	 */
	public SettingsObjectType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(SettingsObjectType type) {
		this.type = type;
	}

}