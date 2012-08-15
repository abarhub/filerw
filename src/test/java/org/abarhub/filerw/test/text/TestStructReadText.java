package org.abarhub.filerw.test.text;

import org.abarhub.filerw.text.FileContentText;
import org.abarhub.filerw.text.LineContentText;
import org.abarhub.filerw.text.ReadWriteText;
import org.abarhub.filerw.text.StructTextReader;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestStructReadText {

	@Test
	public void testReadLine() throws URISyntaxException,
			FileNotFoundException, IOException, ParseException {
		ReadWriteText<FieldsListChamps1> lecture;
		FileContentText<FieldsListChamps1> fichier;
		File f;
		StructTextReader<FieldsListChamps1> in = null;
		URL url = getClass().getResource("/data/exemple1.txt");
		List<LineContentText<FieldsListChamps1>> list;
		LineContentText<FieldsListChamps1> line;
		char c;
		int i, no;
		f = new File(url.toURI());
		System.out.println("Lecture du fichier " + f.getPath() + " :");
		try {
			in = new StructTextReader<FieldsListChamps1>(new FileReader(f),
					FieldsListChamps1.class);
			list = new ArrayList<LineContentText<FieldsListChamps1>>();
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
			assertEquals("Error in line " + no, 3, list.size());
		} finally {
			if (in != null)
				in.close();
		}
	}

	@Test
	public void testReadLine2() throws URISyntaxException,
			FileNotFoundException, IOException, ParseException {
		ReadWriteText<FieldsListChamps1> lecture;
		FileContentText<FieldsListChamps1> fichier;
		File f;
		StructTextReader<FieldsListChamps1> in = null;
		URL url = getClass().getResource("/data/exemple1.txt");
		List<LineContentText<FieldsListChamps1>> list;
		LineContentText<FieldsListChamps1> line;
		char c;
		int i, no;
		StringReader in_str;
		String tab[] = { "Newton2             Isaac               04011643",
				"Einstein            Albert              14103879",
				"Copernic            Nicolas             19021473" };
		f = new File(url.toURI());
		System.out.println("Lecture du fichier " + f.getPath() + " :");
		in_str = new StringReader(tab[0] + tab[1] + tab[2]);
		try {
			in = new StructTextReader<FieldsListChamps1>(in_str,
					FieldsListChamps1.class);
			list = new ArrayList<LineContentText<FieldsListChamps1>>();
			no = 1;
			while ((line = in.readLn()) != null) {
				assertTrue(no <= tab.length);
				assertEquals(tab[no - 1], line.getLine());
				list.add(line);
				no++;
			}
			assertEquals("Error in line " + no, 3, list.size());
		} finally {
			if (in != null)
				in.close();
		}
	}
}
