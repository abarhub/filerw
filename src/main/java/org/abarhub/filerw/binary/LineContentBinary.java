package org.abarhub.filerw.binary;

import java.io.PrintStream;
import java.util.List;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.LineContent;
import org.abarhub.filerw.Tools;

public class LineContentBinary<T extends Field> extends LineContent<T> {

	private byte[] line;

	public LineContentBinary(List<T> fieldsList, byte[] line) {
		super(fieldsList);
		init(line);
	}

	public LineContentBinary(List<T> fieldsList) {
		super(fieldsList);
		init(null);
	}

	private void init(byte[] line) {
		int len, len2;
		len = Tools.getSize(fieldsList);
		if (len <= 0) {
			throw new IllegalArgumentException();
		}
		if (line != null && line.length > len) {
			throw new IllegalArgumentException();
		}
		this.line = new byte[len];
		if (line == null) {
			len2 = 0;
		} else if (line.length < len) {
			len2 = line.length;
		} else {
			len2 = len;
		}
		for (int i = 0; i < len2; i++) {
			this.line[i] = line[i];
		}
	}

	public LineContentBinary(Class<T> clazz) {
		super(clazz);
		init(null);
	}

	public LineContentBinary(Class<T> clazz, byte[] line) {
		super(clazz);
		init(line);
	}

	@Override
	public void show(PrintStream out) {
		byte tab[];
		for (T champs : fieldsList) {
			tab = get(champs);
			out.print(champs.name() + "=");
			if (tab != null) {
				boolean start = true;
				for (byte b : tab) {
					if (!start) {
						out.print(',');
					}
					out.print(b);
					start = false;
				}
			}
			out.println();
		}
	}

	public byte[] get(T field) {
		byte[] res;
		res = new byte[field.getLength()];
		if (line != null && line.length > field.getPosition()
				&& line.length >= field.getPosition() + field.getLength()) {
			for (int i = field.getPosition(); i < field.getPosition()
					+ field.getLength(); i++) {
				res[i - field.getPosition()] = line[i];
			}
		}
		return res;
	}

	public void setString(T field, byte[] value) {
		for (int i = field.getPosition(), j = 0; i < field.getPosition()
				+ field.getLength(); i++, j++) {
			if (value != null && j < value.length) {
				line[i] = value[j];
			} else {
				line[i] = 0;
			}
		}
	}

	public byte[] getLine() {
		return line;
	}
}
