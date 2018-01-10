
package xml.settings.domain;

import xml.settings.domain.*;
import xml.settings.net.sf.migbase64.Base64;
import xml.settings.util.Stringer;

/**
 * Represents a simple Settings data element. The value is stored as a raw string.
 */
public class Data extends SettingsObject implements
		ISettingsSimpleObject<java.lang.String> {

	protected Stringer dataStringer;
	protected byte[] rawData;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3101592260075687323L;

	public Data() {
		setType(SettingsObjectType.DATA);
		dataStringer = new Stringer();
	}

	/**
	 * Get the raw Base64 encoded data value on this object. Assumes the data
	 * has already been encoded.
	 * 
	 * @see com.huawei.android.xml.settings.SettingsXMLHandler.Settings.
	 *      ISettingsSimpleObject#getValue()
	 */
	@Override
	public java.lang.String getValue() {
		return getValue(true);
	}

	/**
	 * Get the raw Base64 encoded data value on this object. Assumes the data
	 * has already been encoded.
	 * 
	 * @param decode
	 *            - if true, the result is Base64 decoded before being returned.
	 *            NOTE: this calls {@link Base64#decodeFast(byte[])} which
	 *            expects the raw encoded data to be on one line (no line
	 *            separators). To change this behavior, subclass and override.
	 * 
	 * @see com.huawei.android.xml.settings.SettingsXMLHandler.Settings.
	 *      ISettingsSimpleObject#getValue()
	 */
	public java.lang.String getValue(boolean decode) {
		dataStringer.newBuilder();
		if (decode) {
			return dataStringer.getBuilder()
					.append(new java.lang.String(Base64.decodeFast(rawData)))
					.toString();
		} else {
			return dataStringer.getBuilder().append(rawData).toString();
		}
	}

	/**
	 * Sets the raw Base64 data value on this object. Assumes the data is
	 * properly encoded.
	 * 
	 * @param val
	 *            - Base64 encoded data to set
	 * 
	 * @see com.huawei.android.xml.settings.SettingsXMLHandler.Settings.
	 *      ISettingsSimpleObject#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(java.lang.String val) {
		setValue(val, true);
	}

	/**
	 * Sets the data value on this object.
	 * 
	 * @param val
	 *            - unencoded data to set
	 * @param encoded
	 *            - flag true if val is Base64 encoded already. If false, val is
	 *            encoded (without line seperators) before being stored.
	 * 
	 * @see com.huawei.android.xml.settings.SettingsXMLHandler.Settings.
	 *      ISettingsSimpleObject#setValue(java.lang.Object)
	 */
	public void setValue(java.lang.String val, boolean encoded) {
		if (!encoded) {
			rawData = Base64.encodeToByte(val.getBytes(), false);
		} else {
			rawData = val.getBytes();
		}
	}

	/**
	 * Sets the data value on this object.
	 * 
	 * @param val
	 *            - unencoded data to set
	 * @param encoded
	 *            - flag true if val is Base64 encoded already. If false, val is
	 *            encoded (without line seperators) before being stored.
	 * 
	 * @see com.huawei.android.xml.settings.SettingsXMLHandler.Settings.
	 *      ISettingsSimpleObject#setValue(java.lang.Object)
	 */
	public void setValue(byte[] val, boolean encoded) {
		if (!encoded) {
			rawData = Base64.encodeToByte(val, false);
		} else {
			rawData = val;
		}
	}

}