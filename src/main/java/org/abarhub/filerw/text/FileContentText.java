/*
 * To change this template,   choose Tools | Templates
 * and open the template in the editor.
 */
package org.abarhub.filerw.text;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.LineContent;

/**
 * 
 * @author abarhub
 */
public class FileContentText<T extends Field> {

	private List<LineContentText<T>> liste;

	public FileContentText() {
		liste = new ArrayList<LineContentText<T>>();
	}

	public void add(LineContentText<T> line) {
		liste.add(line);
	}

	public List<LineContentText<T>> getListe() {
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
		if (!(obj instanceof FileContentText)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		FileContentText<T> other = (FileContentText<T>) obj;
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
