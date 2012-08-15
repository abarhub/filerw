package org.abarhub.filerw.test.binary;

import org.abarhub.filerw.binary.FileContentBinary;
import org.abarhub.filerw.binary.LineContentBinary;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFileContentBinary {

	@Test
	public void testAdd() {
		byte[] tab = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
				17 };
		LineContentBinary<FieldsListChamps2> line;
		FileContentBinary<FieldsListChamps2> file;
		line = new LineContentBinary<FieldsListChamps2>(
				FieldsListChamps2.class, tab);

		file = new FileContentBinary<FieldsListChamps2>();

		assertTrue(file.getListe() != null);
		assertTrue(file.getListe().isEmpty());

		file.add(line);

		assertTrue(file.getListe() != null);
		assertFalse(file.getListe().isEmpty());
		assertEquals(1, file.getListe().size());
	}

	@Test
	public void testShow() {
		byte[] tab = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
				17 };
		LineContentBinary<FieldsListChamps2> line;
		FileContentBinary<FieldsListChamps2> file;
		line = new LineContentBinary<FieldsListChamps2>(
				FieldsListChamps2.class, tab);

		file = new FileContentBinary<FieldsListChamps2>();

		assertTrue(file.getListe() != null);
		assertTrue(file.getListe().isEmpty());

		file.show();

		file.add(line);

		assertTrue(file.getListe() != null);
		assertFalse(file.getListe().isEmpty());
		assertEquals(1, file.getListe().size());

		file.show();
	}

}
