package org.abarhub.filerw.test.binary;

import static org.junit.Assert.*;

import org.abarhub.filerw.binary.LineContentBinary;
import org.junit.Test;

public class TestLineContentBinary {

	@Test
	public void test() {
		byte[] tab={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		LineContentBinary<FieldsListChamps2> line;
		line=new LineContentBinary<FieldsListChamps2>(FieldsListChamps2.class, tab);
		assertEquals(FieldsListChamps2.Code1.getLength(),line.get(FieldsListChamps2.Code1));
		assertEquals(FieldsListChamps2.Code2.getLength(),line.get(FieldsListChamps2.Code2));
		assertEquals(FieldsListChamps2.Code3.getLength(),line.get(FieldsListChamps2.Code3));
	}

}
