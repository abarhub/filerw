package org.abarhub.filerw;

import java.io.PrintStream;
import java.util.List;

public abstract class LineContent<T extends Field> {

	protected final List<T> fieldsList;

	public LineContent(List<T> fieldsList) {
		if (fieldsList == null || fieldsList.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.fieldsList = fieldsList;
	}

	public LineContent(Class<T> clazz) {
		if (clazz == null || !clazz.isEnum()) {
			throw new IllegalArgumentException();
		}
		this.fieldsList = Tools.convClassEnum(clazz);
	}

	public List<T> getFieldsList() {
		return fieldsList;
	}

	public abstract void show(PrintStream out);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fieldsList == null) ? 0 : fieldsList.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LineContent)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		LineContent<T> other = (LineContent<T>) obj;
		if (fieldsList == null) {
			if (other.fieldsList != null) {
				return false;
			}
		} else if (!fieldsList.equals(other.fieldsList)) {
			return false;
		}
		return true;
	}

}
