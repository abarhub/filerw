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


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileContentBinary {

    @Test
    public void testAdd() {
        byte[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17};
        LineContentBinary<FieldsListChamps2> line;
        FileContentBinary<FieldsListChamps2> file;
        line = new LineContentBinary<>(
                FieldsListChamps2.class, tab);

        file = new FileContentBinary<>();

        assertNotNull(file.getListe());
        assertTrue(file.getListe().isEmpty());

        file.add(line);

        assertNotNull(file.getListe());
        assertFalse(file.getListe().isEmpty());
        assertEquals(1, file.getListe().size());
    }

    @Test
    public void testShow() {
        byte[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17};
        LineContentBinary<FieldsListChamps2> line;
        FileContentBinary<FieldsListChamps2> file;
        line = new LineContentBinary<>(
                FieldsListChamps2.class, tab);

        file = new FileContentBinary<>();

        assertNotNull(file.getListe());
        assertTrue(file.getListe().isEmpty());

        file.show();

        file.add(line);

        assertNotNull(file.getListe());
        assertFalse(file.getListe().isEmpty());
        assertEquals(1, file.getListe().size());

        file.show();
    }

    @Test
    public void testHash() {
        byte[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17};
        LineContentBinary<FieldsListChamps2> line;
        FileContentBinary<FieldsListChamps2> file;
        FileContentBinary<FieldsListChamps2> file2;
        line = new LineContentBinary<>(
                FieldsListChamps2.class, tab);

        file = new FileContentBinary<>();

        assertNotNull(file.getListe());
        assertTrue(file.getListe().isEmpty());

        file2 = new FileContentBinary<>();
        assertEquals(file.hashCode(), file2.hashCode());

        file.add(line);

        assertNotNull(file.getListe());
        assertFalse(file.getListe().isEmpty());
        assertEquals(1, file.getListe().size());
        assertNotEquals(file.hashCode(), file2.hashCode());
    }

    @Test
    public void testEquals() {
        byte[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17};
        LineContentBinary<FieldsListChamps2> line;
        FileContentBinary<FieldsListChamps2> file;
        FileContentBinary<FieldsListChamps2> file2;
        line = new LineContentBinary<>(
                FieldsListChamps2.class, tab);

        file = new FileContentBinary<>();
        file2 = new FileContentBinary<>();

        assertNotNull(file.getListe());
        assertTrue(file.getListe().isEmpty());
        assertEquals(file, file2);
        assertEquals(file, file);
        assertNotEquals(file, "abc");

        file.add(line);

        assertNotNull(file.getListe());
        assertFalse(file.getListe().isEmpty());
        assertEquals(1, file.getListe().size());
        assertNotEquals(file, file2);

        file2.add(new LineContentBinary<>(
                FieldsListChamps2.class, tab));

        assertEquals(file, file2);
    }

}
