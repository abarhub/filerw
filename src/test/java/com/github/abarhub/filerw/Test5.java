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

package com.github.abarhub.filerw;

import com.github.abarhub.filerw.text.FieldsListChamps1;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
