package com.github.abarhub.filerw.binary;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.github.abarhub.filerw.Field;
import com.github.abarhub.filerw.LineContent;

public class FileContentBinary<T extends Field> {

	private List<LineContentBinary<T>> liste;

	public FileContentBinary() {
		liste = new ArrayList<LineContentBinary<T>>();
	}

	public void add(LineContentBinary<T> line) {
		liste.add(line);
	}

	public List<LineContentBinary<T>> getListe() {
		return liste;
	}

	public void show() {
		show(System.out);
	}

	public void show(PrintStream out) {
		if (liste != null && !liste.isEmpty()) {
			for (int i = 0; i < liste.size(); i++) {
				LineContent<T> line = liste.get(i);
				out.println("Line no " + i);
				line.show(out);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((liste == null) ? 0 : liste.hashCode());
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
		if (!(obj instanceof FileContentBinary)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		FileContentBinary<T> other = (FileContentBinary<T>) obj;
		if (liste == null) {
			if (other.liste != null) {
				return false;
			}
		} else if (!liste.equals(other.liste)) {
			return false;
		}
		return true;
	}
}
