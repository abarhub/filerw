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
}
