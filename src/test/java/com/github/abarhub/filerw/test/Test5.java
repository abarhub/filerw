package com.github.abarhub.filerw.test;

import org.junit.Test;

import com.github.abarhub.filerw.Tools;
import com.github.abarhub.filerw.test.text.FieldsListChamps1;

import java.util.List;

import static org.junit.Assert.*;

public class Test5 {

	@Test
	public void testGetSizeClassOfT() {
		int len;
		len = Tools.getSize(FieldsListChamps1.class);
		assertEquals(48, len);
	}

	@Test
	public void testConvClassEnum() {
		List<FieldsListChamps1> liste;
		liste = Tools.convClassEnum(FieldsListChamps1.class);
		assertNotNull(liste);
		assertFalse(liste.isEmpty());
		assertEquals(3, liste.size());
		assertTrue(liste.contains(FieldsListChamps1.Nom));
		assertTrue(liste.contains(FieldsListChamps1.Prenom));
		assertTrue(liste.contains(FieldsListChamps1.DateNaissance));
	}

}
