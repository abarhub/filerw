package org.abarhub.filerw.binary;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.LineContent;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
}
