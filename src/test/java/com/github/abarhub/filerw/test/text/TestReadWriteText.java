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

package com.github.abarhub.filerw.test.text;

import org.junit.Test;

import com.github.abarhub.filerw.test.ToolBox;
import com.github.abarhub.filerw.text.FileContentText;
import com.github.abarhub.filerw.text.ReadWriteText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestReadWriteText {

	@Test
	public void test1() throws URISyntaxException, FileNotFoundException,
			IOException, ParseException {
		ReadWriteText<FieldsListChamps1> lecture;
		FileContentText<FieldsListChamps1> fichier;
		File f;
		f = getFile1();
		System.out.println("Lecture du fichier " + f.getPath() + " :");
		lecture = new ReadWriteText<FieldsListChamps1>(f,
				FieldsListChamps1.class);
		fichier = lecture.readFile();
		assertTrue(fichier != null);
		fichier.show();

	}

	@Test
	public void test2() throws URISyntaxException, FileNotFoundException,
			IOException, ParseException {
		ReadWriteText<FieldsListChamps1> lecture;
		FileContentText<FieldsListChamps1> fichier;
		File f, f2;
		f = getFile1();
		f2 = new File(f.getAbsoluteFile().getParentFile(), "exemple1_1.txt");
		System.out.println("Lecture du fichier " + f.getPath() + " :");
		lecture = new ReadWriteText<FieldsListChamps1>(f,
				FieldsListChamps1.class);
		fichier = lecture.readFile();
		assertTrue(fichier != null);
		fichier.show();
		lecture.writeFile(f2, fichier);
		assertTrue(ToolBox.compare(f, f2));
	}

	@Test
	public void test3() throws URISyntaxException, FileNotFoundException,
			IOException, ParseException {
		ReadWriteText<FieldsListChamps1> lecture;
		FileContentText<FieldsListChamps1> fichier;
		File f, f2;
		f = getFile1();
		f2 = new File(f.getAbsoluteFile().getParentFile(), "exemple1_2.txt");
		System.out.println("Lecture du fichier " + f.getPath() + " :");
		lecture = new ReadWriteText<FieldsListChamps1>(f,
				FieldsListChamps1.class);
		lecture.setNewLineSeparator();
		fichier = lecture.readFile();
		assertTrue(fichier != null);
		fichier.show();
		lecture.writeFile(f2, fichier);
		assertTrue(ToolBox.compare(f, f2));
	}

	@Test
	public void test4() throws URISyntaxException, FileNotFoundException,
			IOException, ParseException {
		ReadWriteText<FieldsListChamps1> lecture;
		FileContentText<FieldsListChamps1> fichier;
		File f, f2;
		f = getFile2();
		f2 = new File(f.getAbsoluteFile().getParentFile(), "exemple2_1.txt");
		System.out.println("Lecture du fichier " + f.getPath() + " :");
		lecture = new ReadWriteText<FieldsListChamps1>(f,
				FieldsListChamps1.class);
		lecture.setNoSeparator();
		fichier = lecture.readFile();
		assertTrue(fichier != null);
		fichier.show();
		lecture.writeFile(f2, fichier);
		assertTrue(ToolBox.compare(f, f2));
	}

	@Test
	public void test5() throws URISyntaxException {
		ReadWriteText<FieldsListChamps1> tmp;

		tmp = new ReadWriteText<FieldsListChamps1>(getFile1(),
				FieldsListChamps1.class);

		assertTrue(tmp.isNewLineSeparator());
		assertFalse(tmp.isNoSeparator());
		assertFalse(tmp.isStringSeparator());

		tmp.setNoSeparator();

		assertFalse(tmp.isNewLineSeparator());
		assertTrue(tmp.isNoSeparator());
		assertFalse(tmp.isStringSeparator());

		tmp.setStringSeparator("abc");

		assertFalse(tmp.isNewLineSeparator());
		assertFalse(tmp.isNoSeparator());
		assertTrue(tmp.isStringSeparator());

		tmp.setNewLineSeparator();

		assertTrue(tmp.isNewLineSeparator());
		assertFalse(tmp.isNoSeparator());
		assertFalse(tmp.isStringSeparator());

	}

	public File getFile1() throws URISyntaxException {
		File f;
		URL url = getClass().getResource("/data/exemple1.txt");
		f = new File(url.toURI());
		return f;
	}

	public File getFile2() throws URISyntaxException {
		File f;
		URL url = getClass().getResource("/data/exemple2.txt");
		f = new File(url.toURI());
		return f;
	}
}
