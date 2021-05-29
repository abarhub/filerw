package com.github.abarhub.filerw;

import com.github.abarhub.filerw.text.FieldsListChamps1;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestToolsTest {

    @Test
    void testConstructeur1() {
        TestTools<FieldsListChamps1> testTools = new TestTools<>(FieldsListChamps1.class);
        assertTrue(testTools.testAll());
        pasMessageErreur(testTools);
    }

    @Test
    void testConstructeur2() {
        TestTools<FieldsListChamps1> testTools = new TestTools<>(Tools.convClassEnum(FieldsListChamps1.class));
        assertTrue(testTools.testAll());
        pasMessageErreur(testTools);
    }

    @Test
    void testBasic() {
        List<StubField> liste = new ArrayList<>();
        liste.add(new StubField(0, 5, "aaa"));
        liste.add(new StubField(5, 5, "bbb"));
        TestTools<StubField> testTools = new TestTools<>(liste);
        assertTrue(testTools.testBasic());
        pasMessageErreur(testTools);
    }

    @Test
    void testBasic2() {
        List<StubField> liste = new ArrayList<>();
        liste.add(new StubField(0, 5, "aaa"));
        liste.add(new StubField(-5, 5, "bbb"));
        TestTools<StubField> testTools = new TestTools<>(liste);
        assertFalse(testTools.testBasic());
        assertEquals("La position du champs bbb est incorrecte",
                testTools.getMessageError());
    }

    @Test
    void testBasic3() {
        List<StubField> liste = new ArrayList<>();
        liste.add(new StubField(0, 5, "aaa"));
        liste.add(new StubField(5, 0, "bbb"));
        TestTools<StubField> testTools = new TestTools<>(liste);
        assertFalse(testTools.testBasic());
        assertEquals("La longueur du champs bbb est trop petite",
                testTools.getMessageError());
    }

    @Test
    void testBasic4() {
        List<StubField> liste = new ArrayList<>();
        liste.add(new StubField(0, 5, "aaa"));
        liste.add(new StubField(5, -2, "bbb"));
        TestTools<StubField> testTools = new TestTools<>(liste);
        assertFalse(testTools.testBasic());
        assertEquals("La longueur du champs bbb est trop petite",
                testTools.getMessageError());
    }

    @Test
    void testBasic5() {
        List<StubField> liste = new ArrayList<>();
        liste.add(new StubField(1, 5, "aaa"));
        liste.add(new StubField(6, 5, "bbb"));
        TestTools<StubField> testTools = new TestTools<>(liste);
        assertFalse(testTools.testBasic());
        assertEquals("Il n'y a aucun champs pour la colonne no 0",
                testTools.getMessageError());
    }

    @Test
    void testDuplicate() {
        List<StubField> liste = new ArrayList<>();
        liste.add(new StubField(0, 5, "aaa"));
        liste.add(new StubField(5, 5, "bbb"));
        TestTools<StubField> testTools = new TestTools<>(liste);
        assertTrue(testTools.testDuplicate());
        pasMessageErreur(testTools);
    }

    @Test
    void testDuplicate2() {
        List<StubField> liste = new ArrayList<>();
        liste.add(new StubField(0, -5, "aaa"));
        liste.add(new StubField(5, -20, "bbb"));
        TestTools<StubField> testTools = new TestTools<>(liste);
        assertFalse(testTools.testDuplicate());
        assertEquals("La taille n'est pas correcte",
                testTools.getMessageError());
    }

    @Test
    void testDuplicate3() {
        List<StubField> liste = new ArrayList<>();
        liste.add(new StubField(0, 5, "aaa"));
        liste.add(new StubField(4, 5, "bbb"));
        TestTools<StubField> testTools = new TestTools<>(liste);
        assertFalse(testTools.testDuplicate());
        assertEquals("La case n°4 est associé a deux champs différents :bbb et aaa",
                testTools.getMessageError());
    }

    @Test
    void testDuplicate4() {
        List<StubField> liste = new ArrayList<>();
        liste.add(new StubField(0, 5, "aaa"));
        liste.add(new StubField(6, 5, "bbb"));
        TestTools<StubField> testTools = new TestTools<>(liste);
        assertFalse(testTools.testDuplicate());
        assertEquals("La case n°5 n'est associée a aucun champs",
                testTools.getMessageError());
    }

    @Test
    void testAll() {
        TestTools<FieldsListChamps1> testTools = new TestTools<>(FieldsListChamps1.class);
        assertTrue(testTools.testAll());
        pasMessageErreur(testTools);
    }

    @Test
    void getMessageError() {
        TestTools<FieldsListChamps1> testTools = new TestTools<>(FieldsListChamps1.class);
        assertTrue(testTools.testAll());
        pasMessageErreur(testTools);
    }

    private <T extends Field> void pasMessageErreur(TestTools<T> testTools) {
        assertTrue(testTools.getMessageError() == null
                || testTools.getMessageError().isEmpty());
    }
}