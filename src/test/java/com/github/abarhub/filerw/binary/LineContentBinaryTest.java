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

import com.github.abarhub.filerw.Tools;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class LineContentBinaryTest {

    @Test
    public void test1() {
        byte[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17};
        LineContentBinary<FieldsListChamps2> line;
        line = new LineContentBinary<>(
                FieldsListChamps2.class, tab);
        assertEquals(FieldsListChamps2.Code1.getLength(),
                size(line.get(FieldsListChamps2.Code1)));
        assertEquals(FieldsListChamps2.Code2.getLength(),
                size(line.get(FieldsListChamps2.Code2)));
        assertEquals(FieldsListChamps2.Code3.getLength(),
                size(line.get(FieldsListChamps2.Code3)));

        assertArrayEquals(new byte[]{1, 2, 3, 4},
                line.get(FieldsListChamps2.Code1));
        assertArrayEquals(new byte[]{5, 6, 7, 8, 9, 10},
                line.get(FieldsListChamps2.Code2));
        assertArrayEquals(new byte[]{11, 12, 13, 14, 15, 16, 17},
                line.get(FieldsListChamps2.Code3));
    }

    @Test
    public void test2() {
        byte[] tab = {31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41, 42, 43, 44,
                45, 46, 47};
        LineContentBinary<FieldsListChamps2> line;
        line = new LineContentBinary<>(
                FieldsListChamps2.class, tab);
        assertEquals(FieldsListChamps2.Code1.getLength(),
                size(line.get(FieldsListChamps2.Code1)));
        assertEquals(FieldsListChamps2.Code2.getLength(),
                size(line.get(FieldsListChamps2.Code2)));
        assertEquals(FieldsListChamps2.Code3.getLength(),
                size(line.get(FieldsListChamps2.Code3)));

        assertArrayEquals(new byte[]{31, 32, 33, 34},
                line.get(FieldsListChamps2.Code1));
        assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                line.get(FieldsListChamps2.Code2));
        assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                line.get(FieldsListChamps2.Code3));

        line.setString(FieldsListChamps2.Code2, new byte[]{100, 101, 102,
                103, 104, 105});

        assertArrayEquals(new byte[]{31, 32, 33, 34},
                line.get(FieldsListChamps2.Code1));
        assertArrayEquals(new byte[]{100, 101, 102, 103, 104, 105},
                line.get(FieldsListChamps2.Code2));
        assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                line.get(FieldsListChamps2.Code3));

    }

    private int size(byte[] tab) {
        if (tab == null)
            return 0;
        else
            return tab.length;
    }

    @Test
    public void test3() {
        LineContentBinary<FieldsListChamps2> line;
        line = new LineContentBinary<>(FieldsListChamps2.class);

        assertArrayEquals(new byte[]{0, 0, 0, 0},
                line.get(FieldsListChamps2.Code1));
        assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0},
                line.get(FieldsListChamps2.Code2));
        assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0},
                line.get(FieldsListChamps2.Code3));

        line.show(System.out);
    }

    @Test
    public void test4() {
        ByteArrayOutputStream buf;
        byte[] tab = {31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41, 42, 43, 44,
                45, 46, 47};
        LineContentBinary<FieldsListChamps2> line;
        String ref, val;
        line = new LineContentBinary<>(
                FieldsListChamps2.class, tab);
        assertEquals(FieldsListChamps2.Code1.getLength(),
                size(line.get(FieldsListChamps2.Code1)));
        assertEquals(FieldsListChamps2.Code2.getLength(),
                size(line.get(FieldsListChamps2.Code2)));
        assertEquals(FieldsListChamps2.Code3.getLength(),
                size(line.get(FieldsListChamps2.Code3)));

        buf = new ByteArrayOutputStream();
        line.show(new PrintStream(buf));
        val = buf.toString();
        ref = "Code1=31,32,33,34\r\n" + "Code2=35,36,92,38,39,40\r\n"
                + "Code3=41,42,43,44,45,46,47\r\n";
        val = val.replaceAll("\\r\\n", "\n");
        ref = ref.replaceAll("\\r\\n", "\n");
        assertEquals(ref, val);
    }

    @Test
    public void test5() {
        byte[] tab = {31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41, 42, 43, 44,
                45, 46, 47};
        LineContentBinary<FieldsListChamps2> line;
        line = new LineContentBinary<>(
                FieldsListChamps2.class, tab);
        assertEquals(FieldsListChamps2.Code1.getLength(),
                size(line.get(FieldsListChamps2.Code1)));
        assertEquals(FieldsListChamps2.Code2.getLength(),
                size(line.get(FieldsListChamps2.Code2)));
        assertEquals(FieldsListChamps2.Code3.getLength(),
                size(line.get(FieldsListChamps2.Code3)));

        assertArrayEquals(tab, line.getLine());
    }

    @Test
    public void test6() {
        byte[] tab = {31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41, 42, 43, 44,
                45, 46, 47};
        LineContentBinary<FieldsListChamps2> line;
        line = new LineContentBinary<>(
                FieldsListChamps2.class, tab);

        assertArrayEquals(new byte[]{31, 32, 33, 34},
                line.get(FieldsListChamps2.Code1));
        assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                line.get(FieldsListChamps2.Code2));
        assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                line.get(FieldsListChamps2.Code3));

        assertArrayEquals(tab, line.getLine());

        line.setString(FieldsListChamps2.Code1, new byte[]{56, 63, -41, 25});

        assertArrayEquals(new byte[]{56, 63, -41, 25},
                line.get(FieldsListChamps2.Code1));
        assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                line.get(FieldsListChamps2.Code2));
        assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                line.get(FieldsListChamps2.Code3));

        line.setString(FieldsListChamps2.Code2, new byte[]{21, 44});

        assertArrayEquals(new byte[]{56, 63, -41, 25},
                line.get(FieldsListChamps2.Code1));
        assertArrayEquals(new byte[]{21, 44, 0, 0, 0, 0},
                line.get(FieldsListChamps2.Code2));
        assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                line.get(FieldsListChamps2.Code3));

    }

    @Test
    public void test7() {
        byte[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17};
        LineContentBinary<FieldsListChamps2> line;
        line = new LineContentBinary<>(
                Tools.convClassEnum(FieldsListChamps2.class), tab);
        assertEquals(FieldsListChamps2.Code1.getLength(),
                size(line.get(FieldsListChamps2.Code1)));
        assertEquals(FieldsListChamps2.Code2.getLength(),
                size(line.get(FieldsListChamps2.Code2)));
        assertEquals(FieldsListChamps2.Code3.getLength(),
                size(line.get(FieldsListChamps2.Code3)));

        assertArrayEquals(new byte[]{1, 2, 3, 4},
                line.get(FieldsListChamps2.Code1));
        assertArrayEquals(new byte[]{5, 6, 7, 8, 9, 10},
                line.get(FieldsListChamps2.Code2));
        assertArrayEquals(new byte[]{11, 12, 13, 14, 15, 16, 17},
                line.get(FieldsListChamps2.Code3));
    }

    @Test
    public void test8() {
        LineContentBinary<FieldsListChamps2> line;
        line = new LineContentBinary<>(Tools.convClassEnum(FieldsListChamps2.class));

        assertArrayEquals(new byte[]{0, 0, 0, 0},
                line.get(FieldsListChamps2.Code1));
        assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0},
                line.get(FieldsListChamps2.Code2));
        assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0, 0},
                line.get(FieldsListChamps2.Code3));

        line.show(System.out);
    }

    @Test
    public void test9() {
        LineContentBinary<FieldsListChamps2> line = new LineContentBinary<>(FieldsListChamps2.class);
        LineContentBinary<FieldsListChamps2> line2 = new LineContentBinary<>(FieldsListChamps2.class);

        assertEquals(line, line);
        assertEquals(line, line2);
        assertEquals(line2, line);
        assertNotEquals(line, null);
        assertNotEquals(line, "abc");
        assertEquals(line.hashCode(), line2.hashCode());
    }

}
