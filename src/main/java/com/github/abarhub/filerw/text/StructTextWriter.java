package com.github.abarhub.filerw.text;

import com.github.abarhub.filerw.Field;
import com.github.abarhub.filerw.Tools;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public class StructTextWriter<T extends Field> extends PrintWriter {

	private final List<T> fieldsList;

	public List<T> getFieldsList() {
		return fieldsList;
	}

	public StructTextWriter(Writer out, Class<T> clazz) {
		super(out);
		if (out == null) {
			throw new IllegalArgumentException();
		}
		if (clazz == null || !clazz.isEnum()) {
			throw new IllegalArgumentException();
		}
		this.fieldsList = Tools.convClassEnum(clazz);
	}

	public StructTextWriter(Writer out, List<T> fieldsList) {
		super(out);
		if (out == null) {
			throw new IllegalArgumentException();
		}
		if (fieldsList == null || fieldsList.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.fieldsList = fieldsList;
	}

	public void writeLine(LineContentText<T> line) {
		print(line.getLine());
	}

}
