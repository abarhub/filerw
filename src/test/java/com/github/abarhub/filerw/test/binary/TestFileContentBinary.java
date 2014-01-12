/*
 * Copyright (c) 2012-2014. Alain Barret
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.abarhub.filerw.test.binary;

import org.junit.Test;

import com.github.abarhub.filerw.binary.FileContentBinary;
import com.github.abarhub.filerw.binary.LineContentBinary;

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
