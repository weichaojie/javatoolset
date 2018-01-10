
package com.huawei.android.xml.settings.domain;

/**
 * Interface that simple Settings objects implement. This includes all objects
 * besides from {@link Array}s and {@link Dict}s.
 */
public interface ISettingsSimpleObject<E extends Object> {

	/**
	 * Get the value of the settings object.
	 * 
	 * @return
	 */
	public E getValue();

	/**
	 * Set the value of the Settings object.
	 * 
	 * @param val
	 */
	public void setValue(E val);

	/**
	 * Set the value of the Settings object from a string.
	 * 
	 * @param val
	 */
	public void setValue(java.lang.String val);
}