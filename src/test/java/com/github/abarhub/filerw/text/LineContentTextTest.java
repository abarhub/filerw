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

package com.github.abarhub.filerw.text;


import com.github.abarhub.filerw.ToolBox;
import com.github.abarhub.filerw.Tools;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class LineContentTextTest {

    @Test
    public void test1() {
        LineContentText<FieldsListChamps1> line;
        int len;
        line = new LineContentText<>(FieldsListChamps1.class,
                "ABC");
        len = Tools.getSize(FieldsListChamps1.class);
        assertEquals(padding("ABC", len), line.getLine());
    }

    @Test
    public void test2() {
        LineContentText<FieldsListChamps1> line;
        int len;
        line = new LineContentText<>(FieldsListChamps1.class);
        len = Tools.getSize(FieldsListChamps1.class);
        assertEquals(padding("", len), line.getLine());
    }

    @Test
    public void test3() {
        LineContentText<FieldsListChamps1> line;
        line = new LineContentText<>(FieldsListChamps1.class,
                "ABC");
        line.setString(FieldsListChamps1.Prenom, "AAA");
        assertEquals(
                padding("ABC", FieldsListChamps1.Nom)
                        + padding("AAA", FieldsListChamps1.Prenom)
                        + padding("", FieldsListChamps1.DateNaissance),
                line.getLine());
    }

    @Test
    public void test4() {
        LineContentText<FieldsListChamps1> line;
        line = new LineContentText<>(FieldsListChamps1.class);
        line.setString(FieldsListChamps1.Prenom, "BBC");
        assertEquals(
                padding("", FieldsListChamps1.Nom)
                        + padding("BBC", FieldsListChamps1.Prenom)
                        + padding("", FieldsListChamps1.DateNaissance),
                line.getLine());
    }

    @Test
    public void test5() {
        LineContentText<FieldsListChamps1> line;
        line = new LineContentText<>(Tools.convClassEnum(FieldsListChamps1.class));
        line.setString(FieldsListChamps1.Prenom, "BBC");
        assertEquals(
                padding("", FieldsListChamps1.Nom)
                        + padding("BBC", FieldsListChamps1.Prenom)
                        + padding("", FieldsListChamps1.DateNaissance),
                line.getLine());
    }

    @Test
    public void testHash() {
        LineContentText<FieldsListChamps1> line = new LineContentText<>(FieldsListChamps1.class, "abc");
        LineContentText<FieldsListChamps1> line2 = new LineContentText<>(FieldsListChamps1.class, "abc");

        assertEquals(line.hashCode(), line.hashCode());
        assertEquals(line.hashCode(), line2.hashCode());

        line.setString(FieldsListChamps1.Prenom, "BBC");

        assertEquals(line.hashCode(), line.hashCode());
        assertNotEquals(line.hashCode(), line2.hashCode());
    }

    @Test
    public void testEquals() {
        LineContentText<FieldsListChamps1> line = new LineContentText<>(FieldsListChamps1.class, "abc");
        LineContentText<FieldsListChamps1> line2 = new LineContentText<>(FieldsListChamps1.class, "abc");

        assertEquals(line, line);
        assertEquals(line, line2);

        line.setString(FieldsListChamps1.Prenom, "BBC");

        assertEquals(line, line);
        assertNotEquals(line, line2);
        assertNotEquals(line, "abc");
    }

    @Nested
    class TestConstructor {

        @Test
        public void testConstructor1() {

            String s = getLine1() + "0";

            assertThrows(IllegalArgumentException.class,
                    () -> new LineContentText<>(FieldsListChamps1.class, s));
        }

        @Test
        public void testConstructor2() {

            String s = getLine1() + "0";

            assertThrows(IllegalArgumentException.class,
                    () -> new LineContentText<>(Tools.convClassEnum(FieldsListChamps1.class), s));
        }

        @Test
        public void testConstructor3() {

            String s = getLine1();

            LineContentText<FieldsListChamps1> lineContentText = new LineContentText<>(FieldsListChamps1.class, s);

            assertNotNull(lineContentText);
            assertEquals(s, lineContentText.getLine());
        }

        @Test
        public void testConstructor4() {

            String s = getLine1();

            LineContentText<FieldsListChamps1> lineContentText = new LineContentText<>(Tools.convClassEnum(FieldsListChamps1.class), s);

            assertNotNull(lineContentText);
            assertEquals(s, lineContentText.getLine());
        }
    }

    @Test
    public void testShow() {
        // arrange
        String s = getLine1();

        LineContentText<FieldsListChamps1> lineContentText = new LineContentText<>(Tools.convClassEnum(FieldsListChamps1.class), s);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);

        // act
        lineContentText.show(printStream);

        // assert
        String s2 = out.toString();
        assertNotNull(s2);
        assertEquals(ToolBox.convertNewLine("Nom=abc12345123123145412\n" +
                        "Prenom=31212312300000000000\n" +
                        "DateNaissance=00000000\n"),
                ToolBox.convertNewLine(s2));
    }

    private String padding(String nom, int len) {
        StringBuilder res;
        res = new StringBuilder(nom);
        while (res.length() < len)
            res.append(" ");
        return res.toString();
    }

    private String padding(String nom, FieldsListChamps1 nom2) {
        StringBuilder res;
        res = new StringBuilder(nom);
        while (res.length() < nom2.getLength())
            res.append(" ");
        return res.toString();
    }

    private String getLine1() {
        return "abc123451231231454123121231230000000000" +
                "000000000";
    }

}
