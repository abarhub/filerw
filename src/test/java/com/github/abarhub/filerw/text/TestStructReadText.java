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

package com.github.abarhub.filerw.text;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestStructReadText {

	@Test
	public void testReadLine() throws URISyntaxException, IOException,
			ParseException {
		File f;
		StructTextReader<FieldsListChamps1> in = null;
		URL url = getClass().getResource("/data/exemple1.txt");
		List<LineContentText<FieldsListChamps1>> list;
		LineContentText<FieldsListChamps1> line;
		char c;
		int i, no;
		assertNotNull(url);
		f = new File(url.toURI());
		System.out.println("Lecture du fichier " + f.getPath() + " :");
		try {
			in = new StructTextReader<>(new FileReader(f),
					FieldsListChamps1.class);
			list = new ArrayList<>();
			no = 1;
			while ((line = in.readLn()) != null) {
				list.add(line);
				i = in.read();
				assertTrue(i != -1);
				c = (char) i;
				assertEquals('\r', c);
				i = in.read();
				assertTrue(i != -1);
				c = (char) i;
				assertEquals('\n', c);
				no++;
			}
			assertEquals( 3, list.size(), "Error in line " + no);
		} finally {
			if (in != null)
				in.close();
		}
	}

	@Test
	public void testReadLine2() throws URISyntaxException,
			IOException, ParseException {
		File f;
		StructTextReader<FieldsListChamps1> in = null;
		URL url = getClass().getResource("/data/exemple1.txt");
		List<LineContentText<FieldsListChamps1>> list;
		LineContentText<FieldsListChamps1> line;
		int no;
		StringReader in_str;
		String[] tab = { "Newton2             Isaac               04011643",
				"Einstein            Albert              14103879",
				"Copernic            Nicolas             19021473" };
		assertNotNull(url);
		f = new File(url.toURI());
		System.out.println("Lecture du fichier " + f.getPath() + " :");
		in_str = new StringReader(tab[0] + tab[1] + tab[2]);
		try {
			in = new StructTextReader<>(in_str,
					FieldsListChamps1.class);
			list = new ArrayList<>();
			no = 1;
			while ((line = in.readLn()) != null) {
				assertTrue(no <= tab.length);
				assertEquals(tab[no - 1], line.getLine());
				list.add(line);
				no++;
			}
			assertEquals(3, list.size(), "Error in line " + no);
		} finally {
			if (in != null)
				in.close();
		}
	}
}
