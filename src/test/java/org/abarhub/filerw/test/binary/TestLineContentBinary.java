package org.abarhub.filerw.test.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.abarhub.filerw.binary.LineContentBinary;
import org.junit.Test;

public class TestLineContentBinary {

	@Test
	public void test() {
		byte[] tab={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
		LineContentBinary<FieldsListChamps2> line;
		line=new LineContentBinary<FieldsListChamps2>(FieldsListChamps2.class, tab);
		assertEquals(FieldsListChamps2.Code1.getLength(),size(line.get(FieldsListChamps2.Code1)));
		assertEquals(FieldsListChamps2.Code2.getLength(),size(line.get(FieldsListChamps2.Code2)));
		assertEquals(FieldsListChamps2.Code3.getLength(),size(line.get(FieldsListChamps2.Code3)));
		
		assertTrue(equals(new byte[]{1,2,3,4},line.get(FieldsListChamps2.Code1)));
		assertTrue(equals(new byte[]{5,6,7,8,9,10},line.get(FieldsListChamps2.Code2)));
		assertTrue(equals(new byte[]{11,12,13,14,15,16,17},line.get(FieldsListChamps2.Code3)));
	}


	@Test
	public void test2() {
		byte[] tab={31,32,33,34,35,36,92,38,39,40,41,42,43,44,45,46,47};
		LineContentBinary<FieldsListChamps2> line;
		line=new LineContentBinary<FieldsListChamps2>(FieldsListChamps2.class, tab);
		assertEquals(FieldsListChamps2.Code1.getLength(),size(line.get(FieldsListChamps2.Code1)));
		assertEquals(FieldsListChamps2.Code2.getLength(),size(line.get(FieldsListChamps2.Code2)));
		assertEquals(FieldsListChamps2.Code3.getLength(),size(line.get(FieldsListChamps2.Code3)));
		
		assertTrue(equals(new byte[]{31,32,33,34},line.get(FieldsListChamps2.Code1)));
		assertTrue(equals(new byte[]{35,36,92,38,39,40},line.get(FieldsListChamps2.Code2)));
		assertTrue(equals(new byte[]{41,42,43,44,45,46,47},line.get(FieldsListChamps2.Code3)));
		
		line.setString(FieldsListChamps2.Code2, new byte[]{100,101,102,103,104,105});
		
		assertTrue(equals(new byte[]{31,32,33,34},line.get(FieldsListChamps2.Code1)));
		assertTrue(equals(new byte[]{100,101,102,103,104,105},line.get(FieldsListChamps2.Code2)));
		assertTrue(equals(new byte[]{41,42,43,44,45,46,47},line.get(FieldsListChamps2.Code3)));
		
		
	}
	
	private int size(byte[] tab)
	{
		if(tab==null)
			return 0;
		else
			return tab.length;
	}
	
	private boolean equals(byte[] tab,byte[] tab2)
	{
		if(tab==null)
			return tab2==null;
		else
		{
			if(tab2==null)
				return false;
			if(tab.length!=tab2.length)
				return false;
			for(int i=0;i<tab.length;i++)
			{
				if(tab[i]!=tab2[i])
					return false;
			}
			return true;
		}
	}
	
	@Test
	public void test3()
	{
		//byte[] tab={31,32,33,34,35,36,92,38,39,40,41,42,43,44,45,46,47};
		LineContentBinary<FieldsListChamps2> line;
		line=new LineContentBinary<FieldsListChamps2>(FieldsListChamps2.class);
		
		assertTrue(equals(new byte[]{0,0,0,0},line.get(FieldsListChamps2.Code1)));
		assertTrue(equals(new byte[]{0,0,0,0,0,0},line.get(FieldsListChamps2.Code2)));
		assertTrue(equals(new byte[]{0,0,0,0,0,0,0},line.get(FieldsListChamps2.Code3)));
		
		line.show(System.out);
	}
	
	@Test
	public void test4()
	{
		ByteArrayOutputStream buf;
		byte[] tab={31,32,33,34,35,36,92,38,39,40,41,42,43,44,45,46,47};
		LineContentBinary<FieldsListChamps2> line;
		String ref,val;
		line=new LineContentBinary<FieldsListChamps2>(FieldsListChamps2.class, tab);
		assertEquals(FieldsListChamps2.Code1.getLength(),size(line.get(FieldsListChamps2.Code1)));
		assertEquals(FieldsListChamps2.Code2.getLength(),size(line.get(FieldsListChamps2.Code2)));
		assertEquals(FieldsListChamps2.Code3.getLength(),size(line.get(FieldsListChamps2.Code3)));
		
		buf=new ByteArrayOutputStream();
		line.show(new PrintStream(buf));
		val=buf.toString();
		ref="Code1=31,32,33,34\r\n"+
			"Code2=35,36,92,38,39,40\r\n"+
			"Code3=41,42,43,44,45,46,47\r\n";
		assertEquals(ref,val);
	}
	

	@Test
	public void test5() {
		byte[] tab={31,32,33,34,35,36,92,38,39,40,41,42,43,44,45,46,47};
		LineContentBinary<FieldsListChamps2> line;
		line=new LineContentBinary<FieldsListChamps2>(FieldsListChamps2.class, tab);
		assertEquals(FieldsListChamps2.Code1.getLength(),size(line.get(FieldsListChamps2.Code1)));
		assertEquals(FieldsListChamps2.Code2.getLength(),size(line.get(FieldsListChamps2.Code2)));
		assertEquals(FieldsListChamps2.Code3.getLength(),size(line.get(FieldsListChamps2.Code3)));
		
		assertTrue(equals(tab,line.getLine()));
	}
	
	@Test
	public void test6() {
		byte[] tab={31,32,33,34,35,36,92,38,39,40,41,42,43,44,45,46,47};
		LineContentBinary<FieldsListChamps2> line;
		line=new LineContentBinary<FieldsListChamps2>(FieldsListChamps2.class, tab);
		
		assertTrue(equals(new byte[]{31,32,33,34},line.get(FieldsListChamps2.Code1)));
		assertTrue(equals(new byte[]{35,36,92,38,39,40},line.get(FieldsListChamps2.Code2)));
		assertTrue(equals(new byte[]{41,42,43,44,45,46,47},line.get(FieldsListChamps2.Code3)));
		
		assertTrue(equals(tab,line.getLine()));
		
		line.setString(FieldsListChamps2.Code1, new byte[]{56,63,-41,25});

		assertTrue(equals(new byte[]{56,63,-41,25},line.get(FieldsListChamps2.Code1)));
		assertTrue(equals(new byte[]{35,36,92,38,39,40},line.get(FieldsListChamps2.Code2)));
		assertTrue(equals(new byte[]{41,42,43,44,45,46,47},line.get(FieldsListChamps2.Code3)));

		line.setString(FieldsListChamps2.Code2, new byte[]{21,44});

		assertTrue(equals(new byte[]{56,63,-41,25},line.get(FieldsListChamps2.Code1)));
		assertTrue(equals(new byte[]{21,44,0,0,0,0},line.get(FieldsListChamps2.Code2)));
		assertTrue(equals(new byte[]{41,42,43,44,45,46,47},line.get(FieldsListChamps2.Code3)));

	}
}
