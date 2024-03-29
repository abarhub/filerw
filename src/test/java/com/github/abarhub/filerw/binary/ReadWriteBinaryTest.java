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

package com.github.abarhub.filerw.binary;

import com.github.abarhub.filerw.ToolBox;
import com.github.abarhub.filerw.Tools;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;


public class ReadWriteBinaryTest {

    @Test
    public void testReadFile() throws URISyntaxException,
            IOException, ParseException {
        ReadWriteBinary<FieldsListChamps2> lecture;
        FileContentBinary<FieldsListChamps2> fichier;
        File f;
        f = getFile1();
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteBinary<>(f,
                FieldsListChamps2.class);
        fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();

        assertNotNull(fichier.getListe());
        assertFalse(fichier.getListe().isEmpty());
        assertEquals(2, fichier.getListe().size());
        assertArrayEquals(fichier.getListe().get(0).getLine(),
                new byte[]{49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50,
                        51, 52, 53, 54, 55});
    }

    @Test
    public void testReadFile2() throws URISyntaxException,
            IOException, ParseException {
        ReadWriteBinary<FieldsListChamps2> lecture;
        FileContentBinary<FieldsListChamps2> fichier;
        File f;
        f = getFile1();
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteBinary<>(f,
                Tools.convClassEnum(FieldsListChamps2.class));
        fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();

        assertNotNull(fichier.getListe());
        assertFalse(fichier.getListe().isEmpty());
        assertEquals(2, fichier.getListe().size());
        assertArrayEquals(fichier.getListe().get(0).getLine(),
                new byte[]{49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50,
                        51, 52, 53, 54, 55});
    }

    @Test
    public void testWriteFile() throws URISyntaxException,
            IOException, ParseException {
        ReadWriteBinary<FieldsListChamps2> lecture;
        FileContentBinary<FieldsListChamps2> fichier;
        File f, f2;
        f = getFile2();
        System.out.println("Lecture du fichier " + f.getPath() + " :");
        lecture = new ReadWriteBinary<>(f,
                FieldsListChamps2.class);

        fichier = lecture.readFile();
        assertNotNull(fichier);
        fichier.show();

        assertNotNull(fichier.getListe());
        assertFalse(fichier.getListe().isEmpty());
        assertEquals(2, fichier.getListe().size());
        assertArrayEquals(fichier.getListe().get(0).getLine(),
                new byte[]{49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50,
                        51, 52, 53, 54, 55});
        assertArrayEquals(fichier.getListe().get(1).getLine(),
                new byte[]{56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57,
                        48, 49, 50, 51, 52});

        f2 = new File(f.getAbsoluteFile().getParentFile(), "exemplebin2_1.txt");
        lecture.writeFile(f2, fichier);

        assertTrue(ToolBox.compare(f, f2));
    }

    public File getFile1() throws URISyntaxException {
        File f;
        URL url = getClass().getResource("/data/exemplebin1.txt");
        assertNotNull(url);
        f = new File(url.toURI());
        return f;
    }

    public File getFile2() throws URISyntaxException {
        File f;
        URL url = getClass().getResource("/data/exemplebin2.txt");
        assertNotNull(url);
        f = new File(url.toURI());
        return f;
    }

}
