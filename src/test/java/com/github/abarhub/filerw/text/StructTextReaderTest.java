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


import com.github.abarhub.filerw.Tools;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class StructTextReaderTest {

    @Test
    public void testReadLine() throws URISyntaxException, IOException,
            ParseException {
        File f;
        StructTextReader<FieldsListChamps1> in = null;
        URL url = getClass().getResource("/data/exemple1.txt");
        List<LineContentText<FieldsListChamps1>> list;
        LineContentText<FieldsListChamps1> line;
        int no;
        assertNotNull(url);
        f = new File(url.toURI());
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        try {
            in = new StructTextReader<>(new FileReader(f),
                    FieldsListChamps1.class);
            list = new ArrayList<>();
            no = 1;
            while ((line = in.readLn()) != null) {
                list.add(line);
                avanceNewLine(in);
                no++;
            }
            assertEquals(3, list.size(), "Error in line " + no);
        } finally {
            if (in != null)
                in.close();
        }
    }

    @Test
    public void testReadLine2() throws URISyntaxException,
            IOException, ParseException {
        File f;
        StructTextReader<FieldsListChamps1> in = null;
        URL url = getClass().getResource("/data/exemple1.txt");
        List<LineContentText<FieldsListChamps1>> list;
        LineContentText<FieldsListChamps1> line;
        int no;
        String[] tab = {"Newton2             Isaac               04011643",
                "Einstein            Albert              14103879",
                "Copernic            Nicolas             19021473"};
        assertNotNull(url);
        f = new File(url.toURI());
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        try {
            in = new StructTextReader<>(Files.newBufferedReader(Paths.get(url.toURI())),
                    FieldsListChamps1.class);
            list = new ArrayList<>();
            no = 1;
            while ((line = in.readLn()) != null) {
                assertTrue(no <= tab.length);
                assertEquals(tab[no - 1], line.getLine());
                list.add(line);
                avanceNewLine(in);
                no++;
            }
            assertEquals(3, list.size(), "Error in line " + no);
        } finally {
            if (in != null)
                in.close();
        }
    }

    @Test
    public void testReadLine3() throws URISyntaxException,
            IOException, ParseException {
        File f;
        URL url = getClass().getResource("/data/exemple3.txt");
        LineContentText<FieldsListChamps1> line;
        String[] tab = {"Newton2             Isaac               04011643"};
        assertNotNull(url);
        f = new File(url.toURI());
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        try (StructTextReader<FieldsListChamps1> in = new StructTextReader<>(Files.newBufferedReader(Paths.get(url.toURI())),
                FieldsListChamps1.class)) {
            line = in.readLn();
            assertEquals(tab[0], line.getLine());

            ParseException exception = assertThrows(ParseException.class, in::readLn);

            assertEquals("Invalid Size (12!=48)", exception.getMessage());
        }
    }

    private void avanceNewLine(Reader reader) throws IOException {
        int c=reader.read();
        if(c=='\r'){
            c=reader.read();
            if(c=='\n'){
                // cas normal
            } else {
                fail("Caractere invalide : '"+c+"'");
            }
        } else if(c=='\n'){
            // cas normal
        } else {
            fail("Caractere invalide : '"+c+"'");
        }
    }

    @Nested
    class TestConstucteur {

        @Test
        void test1() {
            // arrange
            StringReader in = new StringReader("abc");

            // act
            StructTextReader<FieldsListChamps1> str = new StructTextReader<>(in, FieldsListChamps1.class);

            // assert
            assertNotNull(str.getFieldsList());
            assertEquals(3, str.getFieldsList().size());
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.Nom));
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.Prenom));
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.DateNaissance));
        }

        @Test
        void test2() {
            // arrange
            StringReader in = new StringReader("abc");

            // act
            StructTextReader<FieldsListChamps1> str = new StructTextReader<>(in, Tools.convClassEnum(FieldsListChamps1.class));

            // assert
            assertNotNull(str.getFieldsList());
            assertEquals(3, str.getFieldsList().size());
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.Nom));
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.Prenom));
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.DateNaissance));
        }

        @Test
        void test3() {
            // arrange
            StringReader in = new StringReader("abc");

            // act
            StructTextReader<FieldsListChamps1> str = new StructTextReader<>(in, FieldsListChamps1.class, 20);

            // assert
            assertNotNull(str.getFieldsList());
            assertEquals(3, str.getFieldsList().size());
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.Nom));
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.Prenom));
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.DateNaissance));
        }

        @Test
        void test4() {
            // arrange
            StringReader in = new StringReader("abc");

            // act
            StructTextReader<FieldsListChamps1> str = new StructTextReader<>(in, Tools.convClassEnum(FieldsListChamps1.class), 30);

            // assert
            assertNotNull(str.getFieldsList());
            assertEquals(3, str.getFieldsList().size());
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.Nom));
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.Prenom));
            assertTrue(str.getFieldsList().contains(FieldsListChamps1.DateNaissance));
        }

        @Test
        void test5() {
            // arrange
            StringReader in = new StringReader("abc");

            // act
            assertThrows(IllegalArgumentException.class, () ->
                    new StructTextReader<>(in, (Class<FieldsListChamps1>) null, 20));
        }

        @Test
        void test6() {
            // act
            assertThrows(NullPointerException.class, () ->
                    new StructTextReader<>(null, FieldsListChamps1.class, 20));
        }

        @Test
        void test7() {
            // arrange
            StringReader in = new StringReader("abc");

            // act
            assertThrows(IllegalArgumentException.class, () ->
                    new StructTextReader<>(in, FieldsListChamps1.class, -10));
        }

        @Test
        void test8() {
            // arrange
            StringReader in = new StringReader("abc");

            // act
            assertThrows(IllegalArgumentException.class, () ->
                    new StructTextReader<>(in, FieldsListChamps1.class, 0));
        }

        @Test
        void test9() {
            // arrange
            StringReader in = new StringReader("abc");

            // act
            assertThrows(IllegalArgumentException.class, () ->
                    new StructTextReader<>(in, (List<FieldsListChamps1>) null, 20));
        }

        @Test
        void test10() {
            // act
            assertThrows(NullPointerException.class, () ->
                    new StructTextReader<>(null, Tools.convClassEnum(FieldsListChamps1.class), 20));
        }

        @Test
        void test11() {
            // arrange
            StringReader in = new StringReader("abc");

            // act
            assertThrows(IllegalArgumentException.class, () ->
                    new StructTextReader<>(in, Tools.convClassEnum(FieldsListChamps1.class), -10));
        }

        @Test
        void test12() {
            // arrange
            StringReader in = new StringReader("abc");

            // act
            assertThrows(IllegalArgumentException.class, () ->
                    new StructTextReader<>(in, Tools.convClassEnum(FieldsListChamps1.class), 0));
        }
    }

    @Nested
    class TestReadLn {

        @Test
        void test1() throws Exception {
            String s = "Newton2             Isaac               04011643";
            StringReader str = new StringReader(s);
            try (StructTextReader<FieldsListChamps1> in = new StructTextReader<>(str,
                    FieldsListChamps1.class)) {

                // act
                LineContentText<FieldsListChamps1> line = in.readLn();

                // assert
                assertNotNull(line);
                assertEquals(s, line.getLine());
                assertEquals(-1, str.read());
            }
        }

        @Test
        void test2() throws Exception {
            String s1 = "Newton2             Isaac               04011643";
            String s2 = "Einstein            Albert              14103879";
            String s3 = "Copernic            Nicolas             19021473";
            String s = s1 + "\n" + s2 + "\n" + s3 + "\n";
            StringReader str = new StringReader(s);
            try (StructTextReader<FieldsListChamps1> in = new StructTextReader<>(str,
                    FieldsListChamps1.class)) {

                // act
                LineContentText<FieldsListChamps1> line = in.readLn();

                // assert
                assertNotNull(line);
                assertEquals(s1, line.getLine());
                int c = in.read();
                assertEquals('\n', c);

                LineContentText<FieldsListChamps1> line2 = in.readLn();
                assertNotNull(line2);
                assertEquals(s2, line2.getLine());
                c = in.read();
                assertEquals('\n', c);

                LineContentText<FieldsListChamps1> line3 = in.readLn();
                assertNotNull(line3);
                assertEquals(s3, line3.getLine());
                c = in.read();
                assertEquals('\n', c);

                assertEquals(-1, str.read());
            }
        }

        @Test
        void test3() throws Exception {
            String s1 = "Newton2             Isaac               04011643";
            String s2 = "Einstein            Albert              14103879";
            String s3 = "Copernic            Nicolas             19021473";
            String s = s1 + s2 + s3;
            StringReader str = new StringReader(s);
            try (StructTextReader<FieldsListChamps1> in = new StructTextReader<>(str,
                    FieldsListChamps1.class)) {

                // act
                LineContentText<FieldsListChamps1> line = in.readLn();

                // assert
                assertNotNull(line);
                assertEquals(s1, line.getLine());

                LineContentText<FieldsListChamps1> line2 = in.readLn();
                assertNotNull(line2);
                assertEquals(s2, line2.getLine());

                LineContentText<FieldsListChamps1> line3 = in.readLn();
                assertNotNull(line3);
                assertEquals(s3, line3.getLine());

                assertEquals(-1, str.read());
            }
        }

        @Test
        void test4() throws Exception {
            String s1 = "Newton2             Isaac               04011643";
            String s2 = "Einstein            Albert              14103879";
            String s3 = "Copernic            Nicolas             19021473";
            String s = s1 + "\r\n" + s2 + "\r\n" + s3 + "\r\n";
            StringReader str = new StringReader(s);
            try (StructTextReader<FieldsListChamps1> in = new StructTextReader<>(str,
                    FieldsListChamps1.class)) {

                // act
                LineContentText<FieldsListChamps1> line = in.readLn();

                // assert
                assertNotNull(line);
                assertEquals(s1, line.getLine());
                assertEquals('\r',in.read());
                assertEquals('\n',in.read());

                LineContentText<FieldsListChamps1> line2 = in.readLn();
                assertNotNull(line2);
                assertEquals(s2, line2.getLine());
                assertEquals('\r',in.read());
                assertEquals('\n',in.read());

                LineContentText<FieldsListChamps1> line3 = in.readLn();
                assertNotNull(line3);
                assertEquals(s3, line3.getLine());
                assertEquals('\r',in.read());
                assertEquals('\n',in.read());

                assertEquals(-1, str.read());
            }
        }

        @Test
        void test5() throws Exception {
            String s1 = "Newton2             Isaac               04011643";
            String s2 = "Einstein            Albert              14103879";
            String s3 = "Copernic            Nicolas             19021473";
            String s = s1 + "\r" + s2 + "\r" + s3 + "\r";
            StringReader str = new StringReader(s);
            try (StructTextReader<FieldsListChamps1> in = new StructTextReader<>(str,
                    FieldsListChamps1.class)) {

                // act
                LineContentText<FieldsListChamps1> line = in.readLn();

                // assert
                assertNotNull(line);
                assertEquals(s1, line.getLine());
                assertEquals('\r',in.read());

                LineContentText<FieldsListChamps1> line2 = in.readLn();
                assertNotNull(line2);
                assertEquals(s2, line2.getLine());
                assertEquals('\r',in.read());

                LineContentText<FieldsListChamps1> line3 = in.readLn();
                assertNotNull(line3);
                assertEquals(s3, line3.getLine());
                assertEquals('\r',in.read());

                assertEquals(-1, str.read());
            }
        }

    }
}
