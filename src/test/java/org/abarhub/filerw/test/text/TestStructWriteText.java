package org.abarhub.filerw.test.text;

import org.abarhub.filerw.text.LineContentText;
import org.abarhub.filerw.text.StructTextWriter;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class TestStructWriteText {

	@Test
	public void testWriteLine() {
		StructTextWriter<FieldsListChamps1> out = null;
		StringWriter out2;
		LineContentText<FieldsListChamps1> line;
		String nom = "Martin", prenom = "Pierre", date = "01011960";
		out2 = new StringWriter();
		try {
			out = new StructTextWriter<FieldsListChamps1>(out2,
					FieldsListChamps1.class);
			line = new LineContentText<FieldsListChamps1>(
					FieldsListChamps1.class);
			line.setString(FieldsListChamps1.Nom, nom);
			line.setString(FieldsListChamps1.Prenom, prenom);
			line.setString(FieldsListChamps1.DateNaissance, date);
			out.writeLine(line);
			assertEquals(
					padding(nom, FieldsListChamps1.Nom)
							+ padding(prenom, FieldsListChamps1.Prenom)
							+ padding(date, FieldsListChamps1.DateNaissance),
					out2.toString());
		} finally {
			if (out != null)
				out.close();
			out = null;
		}
	}

	private String padding(String nom, FieldsListChamps1 nom2) {
		String res;
		res = nom;
		while (res.length() < nom2.getLength())
			res += " ";
		return res;
	}

}
