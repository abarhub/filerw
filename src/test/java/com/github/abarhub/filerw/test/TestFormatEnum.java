package com.github.abarhub.filerw.test;

import org.junit.Test;

import com.github.abarhub.filerw.TestTools;
import com.github.abarhub.filerw.test.binary.FieldsListChamps2;
import com.github.abarhub.filerw.test.text.FieldsListChamps1;

import static org.junit.Assert.assertTrue;

public class TestFormatEnum {

	@Test
	public void test1() {
		TestTools<FieldsListChamps1> test;
		test = new TestTools<FieldsListChamps1>(FieldsListChamps1.class);
		assertTrue(test.testAll());
	}

	@Test
	public void test2() {
		TestTools<FieldsListChamps2> test;
		test = new TestTools<FieldsListChamps2>(FieldsListChamps2.class);
		assertTrue(test.testAll());
	}

}
