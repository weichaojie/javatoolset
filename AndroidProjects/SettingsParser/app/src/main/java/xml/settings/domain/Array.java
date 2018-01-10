
package xml.settings.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents a Settings Array object. Essentially a proxy for a
 * {@link java.util.List} implementation that contains a list of
 * {@link SettingsObject}s.
 * 
 * @author weichaojie
 * 
 */


public class Array extends SettingsObject implements java.util.List<SettingsObject> {

	private ArrayList<SettingsObject> data;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2673110114913406413L;

	/**
	 * 
	 */
	public Array() {
		setType(SettingsObjectType.ARRAY);
		data = new ArrayList<SettingsObject>();
	}

	/**
	 * @param collection
	 */
	public Array(Collection<? extends SettingsObject> collection) {
		setType(SettingsObjectType.ARRAY);
		data = new ArrayList<SettingsObject>(collection);
	}

	/**
	 * @param capacity
	 */
	public Array(int capacity) {
		setType(SettingsObjectType.ARRAY);
		data = new ArrayList<SettingsObject>(capacity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int arg0, SettingsObject arg1) {
		data.add(arg0, (SettingsObject) arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(SettingsObject arg0) {
		return data.add((SettingsObject) arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends SettingsObject> arg0) {
		return data.addAll(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int arg0, Collection<? extends SettingsObject> arg1) {
		return data.addAll(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object arg0) {
		return data.indexOf(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<SettingsObject> listIterator() {
		return data.listIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<SettingsObject> listIterator(int arg0) {
		return data.listIterator(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(int)
	 */
	@Override
	public SettingsObject remove(int arg0) {
		return data.remove(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object arg0) {
		return data.remove(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		return data.remove(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		return data.retainAll(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public SettingsObject set(int arg0, SettingsObject arg1) {
		return data.set(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<SettingsObject> subList(int arg0, int arg1) {
		return data.subList(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		return data.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray(T[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] array) {
		return data.toArray(array);
	}

	/**
	 * @see {@link java.util.ArrayList#clear()}
	 */
	public void clear() {
		data.clear();
	}

	/**
	 * @see {@link java.util.ArrayList#clone()}
	 */
	public Object clone() {
		return data.clone();
	}

	/**
	 * @see {@link java.util.ArrayList#contains(Object)}
	 */
	public boolean contains(Object obj) {
		return data.contains(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(@SuppressWarnings("rawtypes") Collection arg0) {
		return data.contains(arg0);
	}

	/**
	 * @see {@link java.util.ArrayList#equals(Object)}
	 */
	public boolean equals(Object that) {
		return data.equals(that);
	}

	/**
	 * @see {@link java.util.ArrayList#get(int)}
	 */
	public SettingsObject get(int index) {
		return data.get(index);
	}

	/**
	 * @see {@link java.util.ArrayList#indexOf(Object)}
	 */
	public int indexOf(Object object) {
		return data.indexOf(object);
	}

	/**
	 * @see {@link java.util.ArrayList#iterator()}
	 */
	public Iterator<SettingsObject> iterator() {
		return data.iterator();
	}

	/**
	 * @see {@link java.util.ArrayList#size()}
	 */
	public int size() {
		return data.size();
	}

}