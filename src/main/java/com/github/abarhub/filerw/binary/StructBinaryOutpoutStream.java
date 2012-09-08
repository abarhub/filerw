package com.github.abarhub.filerw.binary;

import com.github.abarhub.filerw.Field;
import com.github.abarhub.filerw.Tools;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StructBinaryOutpoutStream<T extends Field> extends
		FilterOutputStream {

	private final List<T> fieldsList;

	public List<T> getFieldsList() {
		return fieldsList;
	}

	public StructBinaryOutpoutStream(OutputStream out, Class<T> clazz) {
		super(out);
		if (out == null) {
			throw new IllegalArgumentException();
		}
		if (clazz == null || !clazz.isEnum()) {
			throw new IllegalArgumentException();
		}
		this.fieldsList = Tools.convClassEnum(clazz);
	}

	public StructBinaryOutpoutStream(OutputStream out, List<T> fieldsList) {
		super(out);
		if (out == null) {
			throw new IllegalArgumentException();
		}
		if (fieldsList == null || fieldsList.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.fieldsList = fieldsList;
	}

	public void writeLine(LineContentBinary<T> line) throws IOException {
		write(line.getLine());
	}
}
