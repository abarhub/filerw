package org.abarhub.filerw.test;

import static org.junit.Assert.*;

import org.abarhub.filerw.Tools;
import org.abarhub.filerw.ascii.LineContentAscii;
import org.junit.Test;

public class Test4 {

	@Test
	public void test1() {
		LineContentAscii<FieldsListChamps1> line;
		int len;
		line=new LineContentAscii<FieldsListChamps1>(FieldsListChamps1.class, "ABC");
		len=Tools.getSize(FieldsListChamps1.class);
		assertEquals(padding("ABC",len), line.getLine());
	}
	
	@Test
	public void test2() {
		LineContentAscii<FieldsListChamps1> line;
		int len;
		line=new LineContentAscii<FieldsListChamps1>(FieldsListChamps1.class);
		len=Tools.getSize(FieldsListChamps1.class);
		assertEquals(padding("",len), line.getLine());
	}

	@Test
	public void test3() {
		LineContentAscii<FieldsListChamps1> line;
		line=new LineContentAscii<FieldsListChamps1>(FieldsListChamps1.class, "ABC");
		line.setString(FieldsListChamps1.Prenom, "AAA");
		assertEquals(padding("ABC",FieldsListChamps1.Nom)+
				padding("AAA",FieldsListChamps1.Prenom)+
				padding("",FieldsListChamps1.DateNaissance), line.getLine());
	}

	@Test
	public void test4() {
		LineContentAscii<FieldsListChamps1> line;
		line=new LineContentAscii<FieldsListChamps1>(FieldsListChamps1.class);
		line.setString(FieldsListChamps1.Prenom, "BBC");
		assertEquals(padding("",FieldsListChamps1.Nom)+
				padding("BBC",FieldsListChamps1.Prenom)+
				padding("",FieldsListChamps1.DateNaissance), line.getLine());
	}
	
	private String padding(String nom, int len) {
		String res;
		res=nom;
		while(res.length()<len)
			res+=" ";
		return res;
	}
	

	private String padding(String nom, FieldsListChamps1 nom2) {
		String res;
		res=nom;
		while(res.length()<nom2.getLength())
			res+=" ";
		return res;
	}
}
