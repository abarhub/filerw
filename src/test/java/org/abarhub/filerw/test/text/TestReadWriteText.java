package org.abarhub.filerw.test.text;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;

import org.abarhub.filerw.test.ToolBox;
import org.abarhub.filerw.text.FileContentText;
import org.abarhub.filerw.text.ReadWriteText;
import org.junit.Test;

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
