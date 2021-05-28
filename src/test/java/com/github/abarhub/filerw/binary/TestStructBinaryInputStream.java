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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;


public class TestStructBinaryInputStream {

	@Test
	public void testStructBinaryInputStreamInputStreamClassOfT()
			throws IOException, ParseException {
		ByteArrayInputStream buf;
		byte[] buf0 = new byte[] { 31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
				42, 43, 44, 45, 46, 47 };
		LineContentBinary<FieldsListChamps2> line;

		buf = new ByteArrayInputStream(buf0);
		try (StructBinaryInputStream<FieldsListChamps2> in = new StructBinaryInputStream<>(buf,
				FieldsListChamps2.class)) {
			line = in.readLn();
			assertNotNull(line);

			assertArrayEquals(new byte[]{31, 32, 33, 34},
					line.get(FieldsListChamps2.Code1));
			assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
					line.get(FieldsListChamps2.Code2));
			assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
					line.get(FieldsListChamps2.Code3));

			assertArrayEquals(buf0, line.getLine());

			line = in.readLn();
			assertNull(line);

		}
	}

	@Test
	public void testStructBinaryInputStreamInputStreamClassOfT2()
			throws IOException, ParseException {
		ByteArrayInputStream buf;
		byte[] buf0 = new byte[] { 31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
				42, 43, 44, 45, 46, 47 };
		LineContentBinary<FieldsListChamps2> line;

		buf = new ByteArrayInputStream(buf0);
		try (StructBinaryInputStream<FieldsListChamps2> in = new StructBinaryInputStream<>(buf,
				Tools.convClassEnum(FieldsListChamps2.class))) {
			line = in.readLn();
			assertNotNull(line);

			assertArrayEquals(new byte[]{31, 32, 33, 34},
					line.get(FieldsListChamps2.Code1));
			assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
					line.get(FieldsListChamps2.Code2));
			assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
					line.get(FieldsListChamps2.Code3));

			assertArrayEquals(buf0, line.getLine());

			line = in.readLn();
			assertNull(line);

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

		buf = new ByteArrayInputStream(buf0);
		try (StructBinaryInputStream<FieldsListChamps2> in = new StructBinaryInputStream<>(buf,
				FieldsListChamps2.class)) {
			line = in.readLn();
			assertNotNull(line);

			assertArrayEquals(new byte[]{31, 32, 33, 34},
					line.get(FieldsListChamps2.Code1));
			assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
					line.get(FieldsListChamps2.Code2));
			assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
					line.get(FieldsListChamps2.Code3));

			line = in.readLn();
			assertNotNull(line);

			assertArrayEquals(new byte[]{121, 32, 33, 34},
					line.get(FieldsListChamps2.Code1));
			assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
					line.get(FieldsListChamps2.Code2));
			assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
					line.get(FieldsListChamps2.Code3));

			line = in.readLn();
			assertNotNull(line);

			assertArrayEquals(new byte[]{-124, 32, 33, 34},
					line.get(FieldsListChamps2.Code1));
			assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
					line.get(FieldsListChamps2.Code2));
			assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
					line.get(FieldsListChamps2.Code3));

			line = in.readLn();
			assertNull(line);

		}
	}

}
