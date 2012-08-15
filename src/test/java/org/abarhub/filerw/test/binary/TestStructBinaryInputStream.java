package org.abarhub.filerw.test.binary;

import org.abarhub.filerw.Tools;
import org.abarhub.filerw.binary.LineContentBinary;
import org.abarhub.filerw.binary.StructBinaryInputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class TestStructBinaryInputStream {

	@Test
	public void testStructBinaryInputStreamInputStreamClassOfT()
			throws IOException, ParseException {
		ByteArrayInputStream buf;
		byte[] buf0 = new byte[] { 31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
				42, 43, 44, 45, 46, 47 };
		LineContentBinary<FieldsListChamps2> line;
		StructBinaryInputStream<FieldsListChamps2> in = null;

		buf = new ByteArrayInputStream(buf0);

		try {
			in = new StructBinaryInputStream<FieldsListChamps2>(buf,
					FieldsListChamps2.class);
			line = in.readLn();
			assertNotNull(line);

			assertTrue(Tools.equals(new byte[] { 31, 32, 33, 34 },
					line.get(FieldsListChamps2.Code1)));
			assertTrue(Tools.equals(new byte[] { 35, 36, 92, 38, 39, 40 },
					line.get(FieldsListChamps2.Code2)));
			assertTrue(Tools.equals(new byte[] { 41, 42, 43, 44, 45, 46, 47 },
					line.get(FieldsListChamps2.Code3)));

			assertTrue(Tools.equals(buf0, line.getLine()));

			line = in.readLn();
			assertNull(line);

		} finally {
			if (in != null)
				in.close();
		}
	}

	@Test
	public void testStructBinaryInputStreamInputStreamClassOfT2()
			throws IOException, ParseException {
		ByteArrayInputStream buf;
		byte[] buf0 = new byte[] { 31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
				42, 43, 44, 45, 46, 47 };
		LineContentBinary<FieldsListChamps2> line;
		StructBinaryInputStream<FieldsListChamps2> in = null;

		buf = new ByteArrayInputStream(buf0);

		try {
			in = new StructBinaryInputStream<FieldsListChamps2>(buf,
					Tools.convClassEnum(FieldsListChamps2.class));
			line = in.readLn();
			assertNotNull(line);

			assertTrue(Tools.equals(new byte[] { 31, 32, 33, 34 },
					line.get(FieldsListChamps2.Code1)));
			assertTrue(Tools.equals(new byte[] { 35, 36, 92, 38, 39, 40 },
					line.get(FieldsListChamps2.Code2)));
			assertTrue(Tools.equals(new byte[] { 41, 42, 43, 44, 45, 46, 47 },
					line.get(FieldsListChamps2.Code3)));

			assertTrue(Tools.equals(buf0, line.getLine()));

			line = in.readLn();
			assertNull(line);

		} finally {
			if (in != null)
				in.close();
		}
	}

	@Test
	public void testStructBinaryInputStreamInputStreamClassOfT3()
			throws IOException, ParseException {
		ByteArrayInputStream buf;
		byte[] buf0 = new byte[] { 31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
				42, 43, 44, 45, 46, 47, 121, 32, 33, 34, 35, 36, 92, 38, 39,
				40, 41, 42, 43, 44, 45, 46, 47, -124, 32, 33, 34, 35, 36, 92,
				38, 39, 40, 41, 42, 43, 44, 45, 46, 47 };
		LineContentBinary<FieldsListChamps2> line;
		StructBinaryInputStream<FieldsListChamps2> in = null;

		buf = new ByteArrayInputStream(buf0);

		try {
			in = new StructBinaryInputStream<FieldsListChamps2>(buf,
					FieldsListChamps2.class);
			line = in.readLn();
			assertNotNull(line);

			assertTrue(Tools.equals(new byte[] { 31, 32, 33, 34 },
					line.get(FieldsListChamps2.Code1)));
			assertTrue(Tools.equals(new byte[] { 35, 36, 92, 38, 39, 40 },
					line.get(FieldsListChamps2.Code2)));
			assertTrue(Tools.equals(new byte[] { 41, 42, 43, 44, 45, 46, 47 },
					line.get(FieldsListChamps2.Code3)));

			line = in.readLn();
			assertNotNull(line);

			assertTrue(Tools.equals(new byte[] { 121, 32, 33, 34 },
					line.get(FieldsListChamps2.Code1)));
			assertTrue(Tools.equals(new byte[] { 35, 36, 92, 38, 39, 40 },
					line.get(FieldsListChamps2.Code2)));
			assertTrue(Tools.equals(new byte[] { 41, 42, 43, 44, 45, 46, 47 },
					line.get(FieldsListChamps2.Code3)));

			line = in.readLn();
			assertNotNull(line);

			assertTrue(Tools.equals(new byte[] { -124, 32, 33, 34 },
					line.get(FieldsListChamps2.Code1)));
			assertTrue(Tools.equals(new byte[] { 35, 36, 92, 38, 39, 40 },
					line.get(FieldsListChamps2.Code2)));
			assertTrue(Tools.equals(new byte[] { 41, 42, 43, 44, 45, 46, 47 },
					line.get(FieldsListChamps2.Code3)));

			line = in.readLn();
			assertNull(line);

		} finally {
			if (in != null)
				in.close();
		}
	}

}
