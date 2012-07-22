package org.abarhub.filerw;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.abarhub.filerw.ascii.LineContentAscii;
import org.abarhub.filerw.ascii.StructAsciiReader;
import org.junit.Test;

import static org.junit.Assert.*;

public class Test2 {

	@Test
	public void testReadLine() throws URISyntaxException, FileNotFoundException, IOException, ParseException {
		ReadWriteAscii<FieldsListChamps1> lecture;
        FileContentAscii<FieldsListChamps1> fichier;
        File f;
        StructAsciiReader<FieldsListChamps1> in=null;
        URL url = getClass().getResource("/data/exemple1.txt");
        List<LineContentAscii<FieldsListChamps1>> list;
        LineContentAscii<FieldsListChamps1> line;
        char c;
        int i,no;
    	f=new File(url.toURI());
    	System.out.println("Lecture du fichier "+f.getPath()+" :");
    	try{
	    	in=new StructAsciiReader<FieldsListChamps1>(new FileReader(f),FieldsListChamps1.class);
	    	list=new ArrayList<LineContentAscii<FieldsListChamps1>>();
	    	no=1;
	    	while((line=in.readLine())!=null)
	    	{
	    		list.add(line);
	    		i=in.read();
	    		assertTrue(i!=-1);
	    		c=(char) i;
	    		assertEquals('\r',c);
	    		i=in.read();
	    		assertTrue(i!=-1);
	    		c=(char) i;
	    		assertEquals('\n',c);
	    		no++;
	    	}
	    	assertEquals("Error in line "+no,3,list.size());
    	}finally{
    		if(in!=null)
    			in.close();
    	}
	}

}
