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

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class StructTextWriterTest {

    @Test
    public void testWriteLine() {
        StructTextWriter<FieldsListChamps1> out = null;
        StringWriter out2;
        LineContentText<FieldsListChamps1> line;
        String nom = "Martin", prenom = "Pierre", date = "01011960";
        out2 = new StringWriter();
        try {
            out = new StructTextWriter<>(out2,
                    FieldsListChamps1.class);
            line = new LineContentText<>(
                    FieldsListChamps1.class);
            line.setString(FieldsListChamps1.Nom, nom);
            line.setString(FieldsListChamps1.Prenom, prenom);
            line.setString(FieldsListChamps1.DateNaissance, date);
            out.writeLine(line);
            assertEquals(
                    padding(nom, FieldsListChamps1.Nom)
                            + padding(prenom, FieldsListChamps1.Prenom)
                            + padding(date, FieldsListChamps1.DateNaissance),
                    out2.toString());
        } finally {
            if (out != null)
                out.close();
        }
    }

    @Nested
    class TestConstructeur {

        @Test
        void test1() {
            // arrange
            StringWriter out2 = new StringWriter();

            // act
            StructTextWriter<FieldsListChamps1> out = new StructTextWriter<>(out2,
                    FieldsListChamps1.class);

            // assert
            assertNotNull(out);
            assertNotNull(out.getFieldsList());
            assertEquals(3, out.getFieldsList().size());
            assertTrue(out.getFieldsList().contains(FieldsListChamps1.Nom));
            assertTrue(out.getFieldsList().contains(FieldsListChamps1.Prenom));
            assertTrue(out.getFieldsList().contains(FieldsListChamps1.DateNaissance));
        }

        @Test
        void test2() {
            // arrange
            StringWriter out2 = new StringWriter();

            // act
            StructTextWriter<FieldsListChamps1> out = new StructTextWriter<>(out2,
                    Tools.convClassEnum(FieldsListChamps1.class));

            // assert
            assertNotNull(out);
            assertNotNull(out.getFieldsList());
            assertEquals(3, out.getFieldsList().size());
            assertTrue(out.getFieldsList().contains(FieldsListChamps1.Nom));
            assertTrue(out.getFieldsList().contains(FieldsListChamps1.Prenom));
            assertTrue(out.getFieldsList().contains(FieldsListChamps1.DateNaissance));
        }

        @Test
        void test3() {
            // act
            assertThrows(NullPointerException.class,()-> new StructTextWriter<>(null,
                    FieldsListChamps1.class));
        }

        @Test
        void test4() {
            // arrange
            StringWriter out2 = new StringWriter();

            // act
            assertThrows(IllegalArgumentException.class,()-> new StructTextWriter<>(out2,
                    (Class<FieldsListChamps1>) null));
        }

        @Test
        void test5() {
            // act
            assertThrows(NullPointerException.class,()-> new StructTextWriter<>(null,
                    new ArrayList<FieldsListChamps1>()));
        }

        @Test
        void test6() {
            // arrange
            StringWriter out2 = new StringWriter();

            // act
            assertThrows(IllegalArgumentException.class,()-> new StructTextWriter<>(out2,
                    (List<FieldsListChamps1>) null));
        }

    }

    private String padding(String nom, FieldsListChamps1 nom2) {
        StringBuilder res;
        res = new StringBuilder(nom);
        while (res.length() < nom2.getLength())
            res.append(" ");
        return res.toString();
    }

}
