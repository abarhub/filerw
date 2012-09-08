package com.github.abarhub.filerw.text;

import com.github.abarhub.filerw.Field;
import com.github.abarhub.filerw.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.List;

public class StructTextReader<T extends Field> extends BufferedReader {

	private static final int SIZEBUFFER = 512;
	private final List<T> fieldsList;

	public List<T> getFieldsList() {
		return fieldsList;
	}

	public StructTextReader(Reader reader, Class<T> clazz) {
		this(reader, clazz, defaultSize(Tools.convClassEnum(clazz)));
	}

	public StructTextReader(Reader reader, List<T> fieldsList) {
		this(reader, fieldsList, defaultSize(fieldsList));
	}

	private static <T extends Field> int defaultSize(List<T> fieldsList2) {
		int res = SIZEBUFFER, i;
		if (fieldsList2 != null && !fieldsList2.isEmpty()) {
			i = Tools.getSize(fieldsList2);
			if (i > 0) {
				res = i;
			}
		}
		return res;
	}

	public StructTextReader(Reader reader, Class<T> clazz, int sz) {
		super(reader, sz);
		if (reader == null) {
			throw new IllegalArgumentException();
		}
		if (clazz == null || !clazz.isEnum()) {
			throw new IllegalArgumentException();
		}
		this.fieldsList = Tools.convClassEnum(clazz);
	}

	public StructTextReader(Reader reader, List<T> fieldsList, int sz) {
		super(reader, sz);
		if (reader == null) {
			throw new IllegalArgumentException();
		}
		if (fieldsList == null || fieldsList.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.fieldsList = fieldsList;
	}

	public LineContentText<T> readLn() throws IOException, ParseException {
		char buf[] = new char[Tools.getSize(fieldsList)];
		int len;
		LineContentText<T> res = null;
		String ligne;

		len = read(buf);
		if (len != -1 && len > 0) {
			if (len != Tools.getSize(fieldsList)) {
				throw new ParseException("Invalid Size (" + len + "!="
						+ Tools.getSize(fieldsList) + ")", len);
			}
			ligne = new String(buf);
			res = new LineContentText<T>(fieldsList, ligne);
		}

		return res;
	}
}
