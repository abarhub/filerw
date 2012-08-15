package org.abarhub.filerw.test.binary;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.abarhub.filerw.Tools;
import org.abarhub.filerw.binary.LineContentBinary;
import org.abarhub.filerw.binary.StructBinaryOutpoutStream;
import org.junit.Test;

public class TestStructBinaryOutputStream {

	@Test
	public void test1() throws IOException {
		ByteArrayOutputStream buf;
		byte[] buf0=new byte[]{31,32,33,34,35,36,92,38,39,40,41,42,43,44,45,46,47};
		LineContentBinary<FieldsListChamps2> line;
		StructBinaryOutpoutStream<FieldsListChamps2> out=null;
		
		buf=new ByteArrayOutputStream();
		try{
			out=new StructBinaryOutpoutStream<FieldsListChamps2>(buf, FieldsListChamps2.class);
			
			line=new LineContentBinary<FieldsListChamps2>(FieldsListChamps2.class, buf0);
			
			out.writeLine(line);
			
			assertTrue(equals(buf0,buf.toByteArray()));
			
		}finally{
			if(out!=null)
				out.close();
		}
	}


	@Test
	public void test2() throws IOException {
		ByteArrayOutputStream buf;
		byte[] buf0=new byte[]{31,32,33,34,35,36,92,38,39,40,41,42,43,44,45,46,47};
		LineContentBinary<FieldsListChamps2> line;
		StructBinaryOutpoutStream<FieldsListChamps2> out=null;
		
		buf=new ByteArrayOutputStream();
		try{
			out=new StructBinaryOutpoutStream<FieldsListChamps2>(buf, Tools.convClassEnum(FieldsListChamps2.class));
			
			line=new LineContentBinary<FieldsListChamps2>(FieldsListChamps2.class, buf0);
			
			out.writeLine(line);
			
			assertTrue(equals(buf0,buf.toByteArray()));
			
		}finally{
			if(out!=null)
				out.close();
		}
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
	
}
