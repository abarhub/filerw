package com.github.abarhub.filerw;

import com.github.abarhub.filerw.binary.FieldsListChamps2;
import com.github.abarhub.filerw.text.FieldsListChamps1;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToolsTest {

    @Test
    void getSize() {
        assertEquals(48, Tools.getSize(FieldsListChamps1.class));
    }

    @Test
    void getSize2() {
        assertEquals(17, Tools.getSize(FieldsListChamps2.class));
    }

    @Test
    void testGetSize() {
        // arrange
        List<FieldsListChamps1> liste = Tools.convClassEnum(FieldsListChamps1.class);

        // act
        int res = Tools.getSize(liste);

        // assert
        assertEquals(48, res);
    }

    @Test
    void testGetSize2() {
        // act
        List<FieldsListChamps2> liste = Tools.convClassEnum(FieldsListChamps2.class);

        // assert
        assertEquals(17, Tools.getSize(liste));
    }

    @Test
    void convClassEnum() {
        // act
        List<FieldsListChamps1> liste = Tools.convClassEnum(FieldsListChamps1.class);

        // assert
        assertNotNull(liste);
        assertEquals(3, liste.size());
        assertTrue(liste.contains(FieldsListChamps1.Nom));
        assertTrue(liste.contains(FieldsListChamps1.Prenom));
        assertTrue(liste.contains(FieldsListChamps1.DateNaissance));
    }

    @Test
    void convClassEnum2() {
        // act
        List<FieldsListChamps2> liste = Tools.convClassEnum(FieldsListChamps2.class);

        // assert
        assertNotNull(liste);
        assertEquals(3, liste.size());
        assertTrue(liste.contains(FieldsListChamps2.Code1));
        assertTrue(liste.contains(FieldsListChamps2.Code2));
        assertTrue(liste.contains(FieldsListChamps2.Code3));
    }
}