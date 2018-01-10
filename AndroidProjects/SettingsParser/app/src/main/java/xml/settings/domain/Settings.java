
package com.huawei.android.xml.settings.domain;

import java.util.Stack;

import android.util.Log;

import xml.settings.util.Stringer;
import com.huawei.android.xml.settings.Constants;

/**
 * A Settings class contains the objects and methods used to build and access a
 * Settings. TODO: refactor so this meets the contract stated above
 */
public class Settings {

	public static final java.lang.String TAG = "Settings";

	private Stringer stringer;

	/**
	 * The Settings root config element.
	 */
	private SettingsObject root;

	private boolean stackCtxInDict;
	private boolean stackCtxInArray;
	private int stackCtxNestedDepth;
	// TODO - replace with some type of Map
	private Stack<SettingsObject> stack;

	public Settings() {
		stringer = new Stringer();
		stackCtxInDict = false;
		stackCtxInArray = false;
		stackCtxNestedDepth = 0;
		stack = new Stack<SettingsObject>();
	}

	/**
	 * @return the Settings root config element
	 */
	public SettingsObject getRootElement() {
		return root;
	}

	/**
	 * @param root
	 *            the Settings root object to set
	 */
	public void setRootElement(SettingsObject root) {
		this.root = root;
	}

	/**
	 * @param Settings
	 * @param stackCtxNestedDepth
	 * @param stackCtxInArray
	 * @param stackCtxInDict
	 * @param stack
	 * @param obj
	 * @param key
	 */
	private void attachSettingsObjToParent(SettingsObject obj, java.lang.String key) {
		if (stackCtxInArray) {
			// attach obj to array parent
			attachSettingsObjToArrayParent(stack, obj);
		} else if (stackCtxInDict) {
			// attach obj to dict parent
			attachSettingsObjToDictParent(obj, key);
		} else if (stackCtxNestedDepth == 0) {
			// set root DICT elm
			setRootElement(obj);
		}
	}

	/**
	 * @param stack
	 * @param key
	 * @param obj
	 */
	private void attachSettingsObjToDictParent(SettingsObject obj,
			java.lang.String key) {
		Log.v(stringer.newBuilder().append(TAG)
				.append("#attachSettingsObjToDictParent").toString(),
				stringer.newBuilder().append("key|obj-type|obj: ").append(key)
						.append(Constants.PIPE).append(obj.getType())
						.append(Constants.PIPE).append(obj.toString())
						.append(Constants.PIPE).toString());
		Dict parent = (Dict) stack.pop();
		parent.putConfig(key, obj);
		stack.push(parent);
	}

	/**
	 * @param stack
	 * @param key
	 * @param obj
	 */
	private void attachSettingsObjToArrayParent(Stack<SettingsObject> stack,
												SettingsObject obj) {
		Log.v(stringer.newBuilder().append(TAG)
				.append("#attachSettingsObjToArrayParent").toString(),
				stringer.newBuilder().append("obj-type|obj: ")
						.append(Constants.PIPE).append(obj.getType())
						.append(Constants.PIPE).append(obj.toString())
						.append(Constants.PIPE).toString());
		Array parent = (Array) stack.pop();
		parent.add(obj);
		stack.push(parent);
	}

	/**
	 * Stack an object onto {@link this}. Stacking means: sequentially adding
	 * {@SettingsObject}s onto the {@Settings}. The previous
	 * object that was stacked affects the context of the current object being
	 * stacked. For example - if the previous element stacked was an
	 * {@link Array} or {@link Dict} - the current object being stacked will be
	 * a child.
	 * 
	 * @param obj
	 * @param key
	 *            If the parent of the element being added is a {@link Dict} -
	 *            this is required and must be non-null. Otherwise it's not
	 *            used.
	 * @throws Exception
	 *             TODO: refactor - move me
	 */
	public void stackObject(SettingsObject obj, java.lang.String key)
			throws Exception {
		if (null == key && stackCtxInDict) {
			throw new Exception(
					"Settings objects with Dict parents require a key.");
		}
		if (stackCtxNestedDepth > 0 && !stackCtxInDict && !stackCtxInArray) {
			// if obj is not at root, its parent should be an Array or
			// Dict
			throw new Exception(
					"Settings elements that are not at the root should have an Array or Dict parent.");
		}
		switch (obj.getType()) {
		case DICT:
			attachSettingsObjToParent(obj, key);
			stack.push(obj);
			stackCtxInArray = false;
			stackCtxInDict = true;
			stackCtxNestedDepth++;
			break;
		case ARRAY:
			attachSettingsObjToParent(obj, key);
			stack.push(obj);
			stackCtxInArray = true;
			stackCtxInDict = false;
			stackCtxNestedDepth++;
			break;
		default:
			attachSettingsObjToParent(obj, key);
		}
	}

	/**
	 * @param obj
	 * @param key
	 * 
	 * @todo refactor - move me - generating Settings from a stack of objets is
	 *       not part of being a Settings.
	 */
	public SettingsObject popStack() {
		if (stack.isEmpty()) {
			return null;
		}
		SettingsObject ret = stack.pop();
		stackCtxNestedDepth--;
		if (!stack.isEmpty()) {
			switch (stack.lastElement().getType()) {
			case DICT:
				stackCtxInArray = false;
				stackCtxInDict = true;
				break;
			case ARRAY:
				stackCtxInArray = true;
				stackCtxInDict = false;
				break;
			}
		} else {
			stackCtxInArray = false;
			stackCtxInDict = false;
		}
		return ret;
	}

	/**
	 * Build a {@SettingsObject} from a string that matches one of
	 * the tags defined in {@link Constants}.
	 * 
	 * @param tag
	 * @param value
	 *            can be null if tag equals {@link Constants#TAG_BOOL_FALSE} or
	 *            {@link Constants#TAG_BOOL_TRUE}.
	 * @throws Exception
	 * 
	 * @todo replace with factory for SettingsObject
	 */
	public SettingsObject buildObject(java.lang.String tag, java.lang.String value)
			throws Exception {
		if (null == tag) {
			throw new Exception(
					"Cannot add a child with a null tag to a Settings.");
		}
		SettingsObject ret = null;
		if (tag.equalsIgnoreCase(Constants.TAG_INTEGER)) {
			ret = new Integer();
			((Integer) ret).setValue(value);
		} else if (tag.equalsIgnoreCase(Constants.TAG_STRING)) {
			ret = new String();
			((String) ret).setValue(value);
		} else if (tag.equalsIgnoreCase(Constants.TAG_REAL)) {
			ret = new Real();
			((Real) ret).setValue(value);
		} else if (tag.equalsIgnoreCase(Constants.TAG_DATE)) {
			ret = new Date();
			((Date) ret).setValue(value);
		} else if (tag.equalsIgnoreCase(Constants.TAG_BOOL_FALSE)) {
			ret = new False();
		} else if (tag.equalsIgnoreCase(Constants.TAG_BOOL_TRUE)) {
			ret = new True();
		} else if (tag.equalsIgnoreCase(Constants.TAG_DATA)) {
			ret = new Data();
			((Data) ret).setValue(value.trim(), true);
		} else if (tag.equalsIgnoreCase(Constants.TAG_DICT)) {
			ret = new Dict();
		} else if (tag.equalsIgnoreCase(Constants.TAG_SETTINGS_ARRAY)) {
			ret = new Array();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public java.lang.String toString() {
		if (null == root) {
			return null;
		}
		return root.toString();
	}

}