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


import com.github.abarhub.filerw.Field;
import com.github.abarhub.filerw.Tools;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class StructBinaryInputStreamTest {

    @Test
    public void testStructBinaryInputStreamInputStreamClassOfT()
            throws IOException, ParseException {
        ByteArrayInputStream buf;
        byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                42, 43, 44, 45, 46, 47};
        LineContentBinary<FieldsListChamps2> line;

        buf = new ByteArrayInputStream(buf0);
        try (StructBinaryInputStream<FieldsListChamps2> in = new StructBinaryInputStream<>(buf,
                FieldsListChamps2.class)) {
            line = in.readLn();
            assertNotNull(line);

            assertArrayEquals(new byte[]{31, 32, 33, 34},
                    line.get(FieldsListChamps2.Code1));
            assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                    line.get(FieldsListChamps2.Code2));
            assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                    line.get(FieldsListChamps2.Code3));

            assertArrayEquals(buf0, line.getLine());

            line = in.readLn();
            assertNull(line);

        }
    }

    @Test
    public void testStructBinaryInputStreamInputStreamClassOfT2()
            throws IOException, ParseException {
        ByteArrayInputStream buf;
        byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                42, 43, 44, 45, 46, 47};
        LineContentBinary<FieldsListChamps2> line;

        buf = new ByteArrayInputStream(buf0);
        try (StructBinaryInputStream<FieldsListChamps2> in = new StructBinaryInputStream<>(buf,
                Tools.convClassEnum(FieldsListChamps2.class))) {
            line = in.readLn();
            assertNotNull(line);

            assertArrayEquals(new byte[]{31, 32, 33, 34},
                    line.get(FieldsListChamps2.Code1));
            assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                    line.get(FieldsListChamps2.Code2));
            assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                    line.get(FieldsListChamps2.Code3));

            assertArrayEquals(buf0, line.getLine());

            line = in.readLn();
            assertNull(line);

        }
    }

    @Test
    public void testStructBinaryInputStreamInputStreamClassOfT3()
            throws IOException, ParseException {
        ByteArrayInputStream buf;
        byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                42, 43, 44, 45, 46, 47, 121, 32, 33, 34, 35, 36, 92, 38, 39,
                40, 41, 42, 43, 44, 45, 46, 47, -124, 32, 33, 34, 35, 36, 92,
                38, 39, 40, 41, 42, 43, 44, 45, 46, 47};
        LineContentBinary<FieldsListChamps2> line;

        buf = new ByteArrayInputStream(buf0);
        try (StructBinaryInputStream<FieldsListChamps2> in = new StructBinaryInputStream<>(buf,
                FieldsListChamps2.class)) {
            line = in.readLn();
            assertNotNull(line);

            assertArrayEquals(new byte[]{31, 32, 33, 34},
                    line.get(FieldsListChamps2.Code1));
            assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                    line.get(FieldsListChamps2.Code2));
            assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                    line.get(FieldsListChamps2.Code3));

            line = in.readLn();
            assertNotNull(line);

            assertArrayEquals(new byte[]{121, 32, 33, 34},
                    line.get(FieldsListChamps2.Code1));
            assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                    line.get(FieldsListChamps2.Code2));
            assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                    line.get(FieldsListChamps2.Code3));

            line = in.readLn();
            assertNotNull(line);

            assertArrayEquals(new byte[]{-124, 32, 33, 34},
                    line.get(FieldsListChamps2.Code1));
            assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                    line.get(FieldsListChamps2.Code2));
            assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                    line.get(FieldsListChamps2.Code3));

            line = in.readLn();
            assertNull(line);

        }
    }

    @Nested
    class TestConstructor {

        @Test
        public void testConstructor()
                throws IOException {
            ByteArrayInputStream buf;
            byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                    42, 43, 44, 45, 46, 47};

            buf = new ByteArrayInputStream(buf0);
            try (StructBinaryInputStream<FieldsListChamps2> in = new StructBinaryInputStream<>(buf,
                    FieldsListChamps2.class)) {
                assertNotNull(in.getFieldsList());
                assertEquals(3, in.getFieldsList().size());
                assertTrue(in.getFieldsList().contains(FieldsListChamps2.Code1));
                assertTrue(in.getFieldsList().contains(FieldsListChamps2.Code2));
                assertTrue(in.getFieldsList().contains(FieldsListChamps2.Code3));
            }
        }

        @Test
        public void testConstructor2()
                throws IOException {
            ByteArrayInputStream buf;
            byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                    42, 43, 44, 45, 46, 47};

            buf = new ByteArrayInputStream(buf0);
            try (StructBinaryInputStream<FieldsListChamps2> in = new StructBinaryInputStream<>(buf,
                    Tools.convClassEnum(FieldsListChamps2.class))) {
                assertNotNull(in.getFieldsList());
                assertEquals(3, in.getFieldsList().size());
                assertTrue(in.getFieldsList().contains(FieldsListChamps2.Code1));
                assertTrue(in.getFieldsList().contains(FieldsListChamps2.Code2));
                assertTrue(in.getFieldsList().contains(FieldsListChamps2.Code3));

            }
        }

        @Test
        public void testConstructor3() {
            ByteArrayInputStream buf = null;
            assertThrows(IllegalArgumentException.class, () -> new StructBinaryInputStream<>(buf,
                    FieldsListChamps2.class));
        }

        @Test
        public void testConstructor4() {
            ByteArrayInputStream buf;
            byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                    42, 43, 44, 45, 46, 47};

            buf = new ByteArrayInputStream(buf0);
            assertThrows(IllegalArgumentException.class, () ->
                    new StructBinaryInputStream<>(buf,
                            (Class<FieldsListChamps2>) null));
        }

        @Test
        public void testConstructor5() {
            ByteArrayInputStream buf = null;
            assertThrows(IllegalArgumentException.class, () -> new StructBinaryInputStream<>(buf,
                    new ArrayList<FieldsListChamps2>()));
        }

        @Test
        public void testConstructor6() {
            ByteArrayInputStream buf;
            byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                    42, 43, 44, 45, 46, 47};

            buf = new ByteArrayInputStream(buf0);
            assertThrows(IllegalArgumentException.class, () ->
                    new StructBinaryInputStream<>(buf,
                            (List<FieldsListChamps2>) null));
        }
    }

    @Nested
    class TestReadLn {

        @Test
        public void testReadLn1() throws Exception {
            ByteArrayInputStream buf;
            byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                    42, 43, 44, 45, 46, 47, 121, 32, 33, 34, 35, 36, 92, 38, 39,
                    40, 41, 42, 43, 44, 45, 46, 47, -124, 32, 33, 34, 35, 36, 92,
                    38, 39, 40, 41, 42, 43, 44, 45, 46, 47};
            LineContentBinary<FieldsListChamps2> line;

            buf = new ByteArrayInputStream(buf0);
            try (LimitInputStream<FieldsListChamps2> in = new LimitInputStream<>(buf,
                    FieldsListChamps2.class, 5)) {
                line = in.readLn();
                assertNotNull(line);

                assertArrayEquals(new byte[]{31, 32, 33, 34},
                        line.get(FieldsListChamps2.Code1));
                assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                        line.get(FieldsListChamps2.Code2));
                assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                        line.get(FieldsListChamps2.Code3));

                line = in.readLn();
                assertNotNull(line);

                assertArrayEquals(new byte[]{121, 32, 33, 34},
                        line.get(FieldsListChamps2.Code1));
                assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                        line.get(FieldsListChamps2.Code2));
                assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                        line.get(FieldsListChamps2.Code3));

                line = in.readLn();
                assertNotNull(line);

                assertArrayEquals(new byte[]{-124, 32, 33, 34},
                        line.get(FieldsListChamps2.Code1));
                assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                        line.get(FieldsListChamps2.Code2));
                assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                        line.get(FieldsListChamps2.Code3));

                line = in.readLn();
                assertNull(line);

            }
        }

        @Test
        public void testReadLn2() throws Exception {
            ByteArrayInputStream buf;
            byte[] buf0 = new byte[]{31, 32, 33};
            LineContentBinary<FieldsListChamps2> line;

            buf = new ByteArrayInputStream(buf0);
            try (LimitInputStream<FieldsListChamps2> in = new LimitInputStream<>(buf,
                    FieldsListChamps2.class, 5)) {
                ParseException exception = assertThrows(ParseException.class, () -> in.readLn());

                assertNotNull(exception);
                assertEquals("Invalid Size (3!=17)", exception.getMessage());
            }
        }

        @Test
        public void testReadLn3() throws Exception {
            ByteArrayInputStream buf;
            byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                    42, 43, 44, 45, 46, 47, 121, 32, 33, 34, 35, 36, 92, 38, 39,
                    40, 41, 42, 43, 44, 45, 46, 47, -124, 32, 33, 34, 35, 36, 92,
                    38, 39, 40, 41, 42, 43, 44, 45, 46};
            LineContentBinary<FieldsListChamps2> line;

            buf = new ByteArrayInputStream(buf0);
            try (LimitInputStream<FieldsListChamps2> in = new LimitInputStream<>(buf,
                    FieldsListChamps2.class, 5)) {
                line = in.readLn();
                assertNotNull(line);

                assertArrayEquals(new byte[]{31, 32, 33, 34},
                        line.get(FieldsListChamps2.Code1));
                assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                        line.get(FieldsListChamps2.Code2));
                assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                        line.get(FieldsListChamps2.Code3));

                line = in.readLn();
                assertNotNull(line);

                assertArrayEquals(new byte[]{121, 32, 33, 34},
                        line.get(FieldsListChamps2.Code1));
                assertArrayEquals(new byte[]{35, 36, 92, 38, 39, 40},
                        line.get(FieldsListChamps2.Code2));
                assertArrayEquals(new byte[]{41, 42, 43, 44, 45, 46, 47},
                        line.get(FieldsListChamps2.Code3));

                ParseException exception = assertThrows(ParseException.class, () -> in.readLn());

                assertNotNull(exception);
                assertEquals("Invalid Size (16!=17)", exception.getMessage());

            }
        }
    }

    class LimitInputStream<T extends Field> extends StructBinaryInputStream<T> {

        private final int size;

        public LimitInputStream(InputStream in, Class clazz, int size) {
            super(in, clazz);
            this.size = size;
        }

        @Override
        public int read(byte[] b) throws IOException {
            if (b.length <= size) {
                return super.read(b);
            } else {
                byte[] buf = new byte[size];
                int len = super.read(buf);
                if (len >= 0) {
                    System.arraycopy(buf, 0, b, 0, len);
                }
                return len;
            }
        }
    }
}
