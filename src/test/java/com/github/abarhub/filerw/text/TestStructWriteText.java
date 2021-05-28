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


import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestStructWriteText {

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

    private String padding(String nom, FieldsListChamps1 nom2) {
        StringBuilder res;
        res = new StringBuilder(nom);
        while (res.length() < nom2.getLength())
            res.append(" ");
        return res.toString();
    }

}
