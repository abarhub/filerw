package org.abarhub.filerw.test;

import static org.junit.Assert.*;

import org.abarhub.filerw.TestTools;
import org.abarhub.filerw.test.binary.FieldsListChamps2;
import org.abarhub.filerw.test.text.FieldsListChamps1;
import org.junit.Test;

public class TestFormatEnum {

	@Test
	public void test1() {
		TestTools<FieldsListChamps1> test;
		test=new TestTools<FieldsListChamps1>(FieldsListChamps1.class);
		assertTrue(test.testAll());
	}

	@Test
	public void test2() {
		TestTools<FieldsListChamps2> test;
		test=new TestTools<FieldsListChamps2>(FieldsListChamps2.class);
		assertTrue(test.testAll());
	}

}
