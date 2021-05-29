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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;


public class ReadWriteTextTest {

    @Test
    public void test1() throws URISyntaxException,
            IOException, ParseException {
        ReadWriteText<FieldsListChamps1> lecture;
        FileContentText<FieldsListChamps1> fichier;
        File f;
        f = getFile1();
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteText<>(f,
                FieldsListChamps1.class);
        fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();

    }

    @Test
    public void test2() throws URISyntaxException,
            IOException, ParseException {
        ReadWriteText<FieldsListChamps1> lecture;
        FileContentText<FieldsListChamps1> fichier;
        File f, f2;
        f = getFile1();
        f2 = new File(f.getAbsoluteFile().getParentFile(), "exemple1_1.txt");
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteText<>(f,
                FieldsListChamps1.class);
        fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();
        lecture.writeFile(f2, fichier);
        assertTrue(ToolBox.compare(f, f2));
    }

    @Test
    public void test3() throws URISyntaxException,
            IOException, ParseException {
        ReadWriteText<FieldsListChamps1> lecture;
        FileContentText<FieldsListChamps1> fichier;
        File f, f2;
        f = getFile1();
        f2 = new File(f.getAbsoluteFile().getParentFile(), "exemple1_2.txt");
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteText<>(f,
                FieldsListChamps1.class);
        lecture.setNewLineSeparator();
        fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();
        lecture.writeFile(f2, fichier);
        assertTrue(ToolBox.compare(f, f2));
    }

    @Test
    public void test4() throws URISyntaxException,
            IOException, ParseException {
        ReadWriteText<FieldsListChamps1> lecture;
        FileContentText<FieldsListChamps1> fichier;
        File f, f2;
        f = getFile2();
        f2 = new File(f.getAbsoluteFile().getParentFile(), "exemple2_1.txt");
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteText<>(f,
                FieldsListChamps1.class);
        lecture.setNoSeparator();
        fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();
        lecture.writeFile(f2, fichier);
        assertTrue(ToolBox.compare(f, f2));
    }

    @Test
    public void test5() throws URISyntaxException {
        ReadWriteText<FieldsListChamps1> tmp;

        tmp = new ReadWriteText<>(getFile1(),
                FieldsListChamps1.class);

        assertTrue(tmp.isNewLineSeparator());
        assertFalse(tmp.isNoSeparator());
        assertFalse(tmp.isStringSeparator());

        tmp.setNoSeparator();

        assertFalse(tmp.isNewLineSeparator());
        assertTrue(tmp.isNoSeparator());
        assertFalse(tmp.isStringSeparator());

        tmp.setStringSeparator("abc");

        assertFalse(tmp.isNewLineSeparator());
        assertFalse(tmp.isNoSeparator());
        assertTrue(tmp.isStringSeparator());

        tmp.setNewLineSeparator();

        assertTrue(tmp.isNewLineSeparator());
        assertFalse(tmp.isNoSeparator());
        assertFalse(tmp.isStringSeparator());
    }

    @Test
    public void test6() throws URISyntaxException {
        ReadWriteText<FieldsListChamps1> tmp;

        tmp = new ReadWriteText<>(getFile1(),
                FieldsListChamps1.class);

        assertThrows(IllegalArgumentException.class, () -> tmp.setStringSeparator(null));
    }

    @Test
    @DisplayName("lecture/ecriture fihcier avec separateur")
    public void test7() throws URISyntaxException,
            IOException, ParseException {
        ReadWriteText<FieldsListChamps1> lecture;
        FileContentText<FieldsListChamps1> fichier;
        File f, f2;
        f = getFile4();
        f2 = new File(f.getAbsoluteFile().getParentFile(), "exemple4_1.txt");
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteText<>(f,
                FieldsListChamps1.class);
        lecture.setStringSeparator("ABC");
        fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();
        lecture.writeFile(f2, fichier);
        assertTrue(ToolBox.compare(f, f2));
    }

    @Test
    @DisplayName("Lecture fichier d'une seule ligne sans séparateur avec configuration newline")
    public void test8() throws URISyntaxException,
            IOException, ParseException {
        ReadWriteText<FieldsListChamps1> lecture;
        FileContentText<FieldsListChamps1> fichier;
        File f;
        f = getFile5();
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteText<>(f,
                FieldsListChamps1.class);
        lecture.setNewLineSeparator();
        fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();
    }

    @Test
    @DisplayName("Lecture fichier d'une seule ligne sans séparateur avec configuration string")
    public void test9() throws URISyntaxException,
            IOException, ParseException {
        ReadWriteText<FieldsListChamps1> lecture;
        FileContentText<FieldsListChamps1> fichier;
        File f;
        f = getFile5();
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteText<>(f,
                FieldsListChamps1.class);
        lecture.setStringSeparator("ABC");
        fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();
    }

    @Test
    @DisplayName("Lecture d'un fichier avec separateur string avec configuration newline")
    public void test10() throws URISyntaxException {
        ReadWriteText<FieldsListChamps1> lecture;
        File f;
        f = getFile4();
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteText<>(f,
                FieldsListChamps1.class);
        lecture.setNewLineSeparator();
        IOException exception = assertThrows(IOException.class, lecture::readFile);
        assertNotNull(exception);
        assertEquals("Bad format", exception.getMessage());
    }

    @Test
    @DisplayName("Lecture d'un fichier avec separateur string avec séparateur invalide")
    public void test11() throws URISyntaxException {
        ReadWriteText<FieldsListChamps1> lecture;
        File f;
        f = getFile1();
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteText<>(f,
                FieldsListChamps1.class);
        lecture.setStringSeparator("ABC");
        IOException exception = assertThrows(IOException.class, lecture::readFile);
        assertNotNull(exception);
        assertTrue(exception.getMessage().startsWith("Le séparateur n'est pas correcte (attendu='ABC',reel='"));
    }

    @Test
    @DisplayName("Lecture d'un fichier avec separateur string avec fichier fini trop tot (separateur tronqué)")
    public void test12() throws URISyntaxException {
        ReadWriteText<FieldsListChamps1> lecture;
        File f;
        f = getFile6();
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteText<>(f,
                FieldsListChamps1.class);
        lecture.setStringSeparator("ABC");
        IOException exception = assertThrows(IOException.class, lecture::readFile);
        assertNotNull(exception);
        assertEquals("Le separateur n'a pas la bonne taille", exception.getMessage());
    }

    @Test
    @DisplayName("Lecture d'un fichier avec separateur string avec separateur=\n")
    public void test13() throws Exception {
        ReadWriteText<FieldsListChamps1> lecture;
        Path f;
        String s = "Newton2             Isaac               04011643\n" +
                "Einstein            Albert              14103879\n" +
                "Copernic            Nicolas             19021473\n";
        f = getNewFile();
        Files.write(f, s.getBytes(StandardCharsets.UTF_8));
        System.out.println("Lecture du fichier " + f + " :");
        lecture = new ReadWriteText<>(f.toFile(),
                FieldsListChamps1.class);
        lecture.setNewLineSeparator();
        FileContentText<FieldsListChamps1> fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();
        assertEquals(3, fichier.getListe().size());
        assertEquals("Newton2             ", fichier.getListe().get(0).getString(FieldsListChamps1.Nom));
        assertEquals("Copernic            ", fichier.getListe().get(2).getString(FieldsListChamps1.Nom));
    }

    @Test
    @DisplayName("Lecture d'un fichier avec separateur string avec separateur=\\r\\n")
    public void test14() throws Exception {
        ReadWriteText<FieldsListChamps1> lecture;
        Path f;
        String s = "Newton2             Isaac               04011643\r\n" +
                "Einstein            Albert              14103879\r\n" +
                "Copernic            Nicolas             19021473\r\n";
        f = getNewFile();
        Files.write(f, s.getBytes(StandardCharsets.UTF_8));
        System.out.println("Lecture du fichier " + f + " :");
        lecture = new ReadWriteText<>(f.toFile(),
                FieldsListChamps1.class);
        lecture.setNewLineSeparator();
        FileContentText<FieldsListChamps1> fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();
        assertEquals(3, fichier.getListe().size());
        assertEquals("Newton2             ", fichier.getListe().get(0).getString(FieldsListChamps1.Nom));
        assertEquals("Copernic            ", fichier.getListe().get(2).getString(FieldsListChamps1.Nom));
    }

    @Test
    @DisplayName("Lecture d'un fichier avec separateur string avec separateur=\\r et une seule ligne")
    public void test15() throws Exception {
        ReadWriteText<FieldsListChamps1> lecture;
        Path f;
        String s = "Newton2             Isaac               04011643\r";
        f = getNewFile();
        Files.write(f, s.getBytes(StandardCharsets.UTF_8));
        System.out.println("Lecture du fichier " + f + " :");
        lecture = new ReadWriteText<>(f.toFile(),
                FieldsListChamps1.class);
        lecture.setNewLineSeparator();
        FileContentText<FieldsListChamps1> fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();
        assertEquals(1, fichier.getListe().size());
        assertEquals("Newton2             ", fichier.getListe().get(0).getString(FieldsListChamps1.Nom));
    }

    private File getFile1() throws URISyntaxException {
        return getFile("exemple1.txt");
    }

    private File getFile2() throws URISyntaxException {
        return getFile("exemple2.txt");
    }

    private File getFile4() throws URISyntaxException {
        return getFile("exemple4.txt");
    }

    private File getFile5() throws URISyntaxException {
        return getFile("exemple5.txt");
    }

    private File getFile6() throws URISyntaxException {
        return getFile("exemple6.txt");
    }

    private Path getNewFile() throws IOException {
        Path p = Files.createTempFile("filerw", "test");
        return p;
    }

    private File getFile(String nomFichier) throws URISyntaxException {
        URL url = getClass().getResource("/data/" + nomFichier);
        assertNotNull(url);
        return new File(url.toURI());
    }
}
