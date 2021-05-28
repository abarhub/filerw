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

package com.github.abarhub.filerw.binary;


import com.github.abarhub.filerw.Tools;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestStructBinaryOutputStream {

	@Test
	public void test1() throws IOException {
		ByteArrayOutputStream buf;
		byte[] buf0 = new byte[] { 31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
				42, 43, 44, 45, 46, 47 };
		LineContentBinary<FieldsListChamps2> line;

		buf = new ByteArrayOutputStream();
		try (StructBinaryOutpoutStream<FieldsListChamps2> out = new StructBinaryOutpoutStream<>(buf,
				FieldsListChamps2.class)) {

			line = new LineContentBinary<>(
					FieldsListChamps2.class, buf0);

			out.writeLine(line);

			assertTrue(Tools.equals(buf0, buf.toByteArray()));

		}
	}

	@Test
	public void test2() throws IOException {
		ByteArrayOutputStream buf;
		byte[] buf0 = new byte[] { 31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
				42, 43, 44, 45, 46, 47 };
		LineContentBinary<FieldsListChamps2> line;

		buf = new ByteArrayOutputStream();
		try (StructBinaryOutpoutStream<FieldsListChamps2> out = new StructBinaryOutpoutStream<>(buf,
				Tools.convClassEnum(FieldsListChamps2.class))) {

			line = new LineContentBinary<>(
					FieldsListChamps2.class, buf0);

			out.writeLine(line);

			assertTrue(Tools.equals(buf0, buf.toByteArray()));

		}
	}

}
