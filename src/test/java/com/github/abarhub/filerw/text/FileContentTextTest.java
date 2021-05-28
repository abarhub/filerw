package com.github.abarhub.filerw.text;

import com.github.abarhub.filerw.ToolBox;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileContentTextTest {

    @Test
    void testConstructor() {
        // act
        FileContentText<FieldsListChamps1> fileContentText = new FileContentText<>();

        // assert
        assertNotNull(fileContentText.getListe());
        assertTrue(fileContentText.getListe().isEmpty());
    }

    @Test
    void add() {
        // arrange
        FileContentText<FieldsListChamps1> fileContentText = new FileContentText<>();

        LineContentText<FieldsListChamps1> line = new LineContentText<>(FieldsListChamps1.class, "abc");

        // act
        fileContentText.add(line);

        // assert
        assertNotNull(fileContentText.getListe());
        assertEquals(1, fileContentText.getListe().size());
        assertEquals(line, fileContentText.getListe().get(0));
    }

    @Test
    void getListe() {
        // arrange
        FileContentText<FieldsListChamps1> fileContentText = new FileContentText<>();

        // act
        List<LineContentText<FieldsListChamps1>> liste = fileContentText.getListe();

        // assert
        assertNotNull(liste);
        assertTrue(liste.isEmpty());
    }

    @Test
    void show() {
        // arrange
        String sRef = "Line no 0\n" +
                "Nom=abc                 \n" +
                "Prenom=                    \n" +
                "DateNaissance=        \n";

        FileContentText<FieldsListChamps1> fileContentText = new FileContentText<>();

        LineContentText<FieldsListChamps1> line = new LineContentText<>(FieldsListChamps1.class, "abc");
        fileContentText.add(line);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);

        // act
        fileContentText.show(printStream);

        // assert
        String s = out.toString();
        assertEquals(ToolBox.convertNewLine(sRef),
                ToolBox.convertNewLine(s));
    }

    @Test
    void testShow() {
        // arrange
        FileContentText<FieldsListChamps1> fileContentText = new FileContentText<>();

        LineContentText<FieldsListChamps1> line = new LineContentText<>(FieldsListChamps1.class, "abc");
        fileContentText.add(line);

        // act
        fileContentText.show();

        // assert
        assertNotNull(fileContentText.getListe());
    }

    @Test
    void testHashCode() {
        FileContentText<FieldsListChamps1> fileContentText = new FileContentText<>();
        FileContentText<FieldsListChamps1> fileContentText2 = new FileContentText<>();

        assertEquals(fileContentText.hashCode(), fileContentText.hashCode());
        assertEquals(fileContentText.hashCode(), fileContentText2.hashCode());

        LineContentText<FieldsListChamps1> line = new LineContentText<>(FieldsListChamps1.class, "abc");
        fileContentText.add(line);

        assertNotEquals(fileContentText.hashCode(), fileContentText2.hashCode());

        LineContentText<FieldsListChamps1> line2 = new LineContentText<>(FieldsListChamps1.class, "abc");
        fileContentText2.add(line2);

        assertEquals(fileContentText.hashCode(), fileContentText2.hashCode());
    }

    @Test
    void testEquals() {
        FileContentText<FieldsListChamps1> fileContentText = new FileContentText<>();
        FileContentText<FieldsListChamps1> fileContentText2 = new FileContentText<>();

        assertEquals(fileContentText, fileContentText);
        assertEquals(fileContentText, fileContentText2);

        LineContentText<FieldsListChamps1> line = new LineContentText<>(FieldsListChamps1.class, "abc");
        fileContentText.add(line);

        assertNotEquals(fileContentText, fileContentText2);

        LineContentText<FieldsListChamps1> line2 = new LineContentText<>(FieldsListChamps1.class, "abc");
        fileContentText2.add(line2);

        assertEquals(fileContentText, fileContentText2);
        assertNotEquals(fileContentText, null);
        assertNotEquals(fileContentText, "abc");
    }
}