
package com.huawei.android.xml.settings.domain;

/**
 * Defines valid Settings object types. These correspond to the
 * elements defined in the Settings XML DTD at {@link http
 * ://www.ultra.com/DTDs/PropertyList-1.0.dtd}.
 * 
 * @author weichaojie
 * 
 */
public enum SettingsObjectType {
	ARRAY(0), DATA(1), DATE(2), DICT(3), REAL(4), INTEGER(5), STRING(
			6), TRUE(7), FALSE(8);

	private int type;

	private SettingsObjectType(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

}