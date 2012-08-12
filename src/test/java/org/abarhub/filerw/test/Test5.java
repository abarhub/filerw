package org.abarhub.filerw.test;

import static org.junit.Assert.*;

import java.util.List;

import org.abarhub.filerw.Tools;
import org.junit.Test;

public class Test5 {

	@Test
	public void testGetSizeClassOfT() {
		int len;
		len=Tools.getSize(FieldsListChamps1.class);
		assertEquals(48, len);
	}

	@Test
	public void testConvClassEnum() {
		List<FieldsListChamps1> liste;
		liste=Tools.convClassEnum(FieldsListChamps1.class);
		assertNotNull(liste);
		assertFalse(liste.isEmpty());
		assertEquals(3, liste.size());
		assertTrue(liste.contains(FieldsListChamps1.Nom));
		assertTrue(liste.contains(FieldsListChamps1.Prenom));
		assertTrue(liste.contains(FieldsListChamps1.DateNaissance));
	}

}
