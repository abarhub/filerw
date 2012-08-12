package org.abarhub.filerw.test;

import static org.junit.Assert.*;

import java.io.StringWriter;

import org.abarhub.filerw.ascii.LineContentAscii;
import org.abarhub.filerw.ascii.StructAsciiWriter;
import org.junit.Test;

public class Test3 {

	@Test
	public void testWriteLine() {
		StructAsciiWriter<FieldsListChamps1> out=null;
		StringWriter out2;
		LineContentAscii<FieldsListChamps1> line;
		String nom="Martin",prenom="Pierre",date="01011960";
		out2=new StringWriter();
		try{
			out=new StructAsciiWriter<FieldsListChamps1>(out2, FieldsListChamps1.class);
			line=new LineContentAscii<FieldsListChamps1>(FieldsListChamps1.class);
			line.setString(FieldsListChamps1.Nom, nom);
			line.setString(FieldsListChamps1.Prenom, prenom);
			line.setString(FieldsListChamps1.DateNaissance, date);
			out.writeLine(line);
			assertEquals(padding(nom,FieldsListChamps1.Nom)+
					padding(prenom,FieldsListChamps1.Prenom)+
					padding(date,FieldsListChamps1.DateNaissance), out2.toString());
		}finally{
			if(out!=null)
				out.close();
			out=null;
		}
	}

	private String padding(String nom, FieldsListChamps1 nom2) {
		String res;
		res=nom;
		while(res.length()<nom2.getLength())
			res+=" ";
		return res;
	}

}
