package com.github.abarhub.filerw;

import com.github.abarhub.filerw.binary.FieldsListChamps2;
import com.github.abarhub.filerw.text.FieldsListChamps1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineContentTest {


    @Nested
    class TestConstructeur {

        @Test
        void constructeur1() {
            // act
            LineContent<FieldsListChamps1> result = createLineContent(FieldsListChamps1.class);
            
            // assert
            assertNotNull(result);
        }

        @Test
        void constructeur2() {
            // act
            LineContent<FieldsListChamps2> result = createLineContent(FieldsListChamps2.class);

            // assert
            assertNotNull(result);
        }

        @Test
        void constructeur3() {
            // act
            IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                    () -> createLineContent((Class<Field>) null));

            // assert
            assertNotNull(result);
        }

        @Test
        void constructeur4() {
            // arrange
            List<FieldsListChamps1> liste = Tools.convClassEnum(FieldsListChamps1.class);

            // act
            LineContent<FieldsListChamps1> result = createLineContent(liste);

            // assert
            assertNotNull(result);
        }

        @Test
        void constructeur5() {
            // arrange
            List<FieldsListChamps1> liste = new ArrayList<>();

            // act
            IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                    () -> createLineContent(liste));

            // assert
            assertNotNull(result);
        }

        @Test
        void constructeur6() {
            // arrange
            List<FieldsListChamps1> liste = null;

            // act
            IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                    () -> createLineContent(liste));

            // assert
            assertNotNull(result);
        }
    }

    @Test
    void getFieldsList() {
        // arrange
        LineContent<FieldsListChamps1> lineContent = createLineContent();

        // act
        List<FieldsListChamps1> result = lineContent.getFieldsList();

        // assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(FieldsListChamps1.Nom));
        assertTrue(result.contains(FieldsListChamps1.Prenom));
        assertTrue(result.contains(FieldsListChamps1.DateNaissance));
    }

    @Nested
    class TestHashCode {

        @Test
        void testHashCode1() {
            // arrange
            LineContent<FieldsListChamps1> lineContent = createLineContent();
            LineContent<FieldsListChamps2> lineContent2 = createLineContent2();

            // act
            int result = lineContent.hashCode();
            int result2 = lineContent2.hashCode();

            // assert
            assertNotEquals(result2, result);
        }

        @Test
        void testHashCode2() {
            // arrange
            LineContent<FieldsListChamps1> lineContent = createLineContent();
            LineContent<FieldsListChamps1> lineContent2 = createLineContent();

            // act
            int result = lineContent.hashCode();
            int result2 = lineContent2.hashCode();

            // assert
            assertEquals(result2, result);
        }

        @Test
        void testHashCode3() {
            // arrange
            LineContent<FieldsListChamps2> lineContent = createLineContent2();
            LineContent<FieldsListChamps2> lineContent2 = createLineContent2();

            // act
            int result = lineContent.hashCode();
            int result2 = lineContent2.hashCode();

            // assert
            assertEquals(result2, result);
        }
    }

    @Nested
    class TestEquals {

        @Test
        void testEquals() {
            // arrange
            LineContent<FieldsListChamps1> lineContent = createLineContent();
            LineContent<FieldsListChamps1> lineContent2 = createLineContent();

            // act
            boolean result = lineContent.equals(lineContent2);

            // assert
            assertTrue(result);
        }

        @Test
        void testEquals2() {
            // arrange
            LineContent<FieldsListChamps1> lineContent = createLineContent();
            LineContent<FieldsListChamps2> lineContent2 = createLineContent2();

            // act
            boolean result = lineContent.equals(lineContent2);

            // assert
            assertFalse(result);
        }

        @Test
        void testEquals3() {
            // arrange
            LineContent<FieldsListChamps1> lineContent = createLineContent();
            LineContent<FieldsListChamps2> lineContent2 = null;

            // act
            boolean result = lineContent.equals(lineContent2);

            // assert
            assertFalse(result);
        }

        @Test
        void testEquals4() {
            // arrange
            LineContent<FieldsListChamps1> lineContent = createLineContent();
            LineContent<FieldsListChamps1> lineContent2 = lineContent;

            // act
            boolean result = lineContent.equals(lineContent2);

            // assert
            assertTrue(result);
        }

        @Test
        void testEquals5() {
            // arrange
            LineContent<FieldsListChamps1> lineContent = createLineContent();

            // act
            boolean result = lineContent.equals("abc");

            // assert
            assertFalse(result);
        }
    }

    // tools

    private LineContent<FieldsListChamps1> createLineContent() {
        return createLineContent(FieldsListChamps1.class);
    }

    private LineContent<FieldsListChamps2> createLineContent2() {
        return createLineContent(FieldsListChamps2.class);
    }

    private <T extends Field> LineContent<T> createLineContent(Class<T> clazz) {
        return new LineContent<T>(clazz) {
            @Override
            public void show(PrintStream out) {

            }
        };
    }

    private <T extends Field> LineContent<T> createLineContent(List<T> fieldsList) {
        return new LineContent<T>(fieldsList) {
            @Override
            public void show(PrintStream out) {

            }
        };
    }
}