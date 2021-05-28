package com.github.abarhub.filerw.binary;

import com.github.abarhub.filerw.Tools;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StructBinaryOutpoutStreamTest {


    @Test
    public void test1() throws IOException {
        ByteArrayOutputStream buf;
        byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                42, 43, 44, 45, 46, 47};
        LineContentBinary<FieldsListChamps2> line;

        buf = new ByteArrayOutputStream();
        try (StructBinaryOutpoutStream<FieldsListChamps2> out = new StructBinaryOutpoutStream<>(buf,
                FieldsListChamps2.class)) {

            line = new LineContentBinary<>(
                    FieldsListChamps2.class, buf0);

            out.writeLine(line);

            assertArrayEquals(buf0, buf.toByteArray());

        }
    }

    @Test
    public void test2() throws IOException {
        ByteArrayOutputStream buf;
        byte[] buf0 = new byte[]{31, 32, 33, 34, 35, 36, 92, 38, 39, 40, 41,
                42, 43, 44, 45, 46, 47};
        LineContentBinary<FieldsListChamps2> line;

        buf = new ByteArrayOutputStream();
        try (StructBinaryOutpoutStream<FieldsListChamps2> out = new StructBinaryOutpoutStream<>(buf,
                Tools.convClassEnum(FieldsListChamps2.class))) {

            line = new LineContentBinary<>(
                    FieldsListChamps2.class, buf0);

            out.writeLine(line);

            assertArrayEquals(buf0, buf.toByteArray());

        }
    }

    @Test
    void getFieldsList() {
    }

    @Test
    void writeLine() {
    }

    @Nested
    class TestConstructor {

        @Test
        public void testConstructor1() throws Exception {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            try (StructBinaryOutpoutStream<FieldsListChamps2> out = new StructBinaryOutpoutStream<>(buf,
                    FieldsListChamps2.class)) {
                assertNotNull(out);
                assertNotNull(out.getFieldsList());
                assertEquals(3, out.getFieldsList().size());
                assertTrue(out.getFieldsList().contains(FieldsListChamps2.Code1));
                assertTrue(out.getFieldsList().contains(FieldsListChamps2.Code2));
                assertTrue(out.getFieldsList().contains(FieldsListChamps2.Code3));
            }
        }

        @Test
        public void testConstructor2() throws Exception {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            try (StructBinaryOutpoutStream<FieldsListChamps2> out = new StructBinaryOutpoutStream<>(buf,
                    Tools.convClassEnum(FieldsListChamps2.class))) {
                assertNotNull(out);
                assertNotNull(out.getFieldsList());
                assertEquals(3, out.getFieldsList().size());
                assertTrue(out.getFieldsList().contains(FieldsListChamps2.Code1));
                assertTrue(out.getFieldsList().contains(FieldsListChamps2.Code2));
                assertTrue(out.getFieldsList().contains(FieldsListChamps2.Code3));
            }
        }

        @Test
        public void testConstructor3() {
            ByteArrayOutputStream buf = null;
            assertThrows(IllegalArgumentException.class, () -> new StructBinaryOutpoutStream<>(buf,
                    FieldsListChamps2.class));
        }

        @Test
        public void testConstructor4() {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            assertThrows(IllegalArgumentException.class, () -> new StructBinaryOutpoutStream<>(buf,
                    (Class<FieldsListChamps2>) null));
        }

        @Test
        public void testConstructor5() {
            ByteArrayOutputStream buf = null;
            assertThrows(IllegalArgumentException.class, () -> new StructBinaryOutpoutStream<>(buf,
                    Tools.convClassEnum(FieldsListChamps2.class)));
        }

        @Test
        public void testConstructor6() {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            assertThrows(IllegalArgumentException.class, () -> new StructBinaryOutpoutStream<>(buf,
                    (List<FieldsListChamps2>) null));
        }

    }

}