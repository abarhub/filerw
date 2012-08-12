package org.abarhub.filerw.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.abarhub.filerw.ascii.FileContentAscii;
import org.abarhub.filerw.ascii.LineContentAscii;
import org.abarhub.filerw.ascii.ReadWriteAscii;
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
	    	while((line=in.readLn())!=null)
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

	@Test
	public void testReadLine2() throws URISyntaxException, FileNotFoundException, IOException, ParseException {
		ReadWriteAscii<FieldsListChamps1> lecture;
        FileContentAscii<FieldsListChamps1> fichier;
        File f;
        StructAsciiReader<FieldsListChamps1> in=null;
        URL url = getClass().getResource("/data/exemple1.txt");
        List<LineContentAscii<FieldsListChamps1>> list;
        LineContentAscii<FieldsListChamps1> line;
        char c;
        int i,no;
        StringReader in_str;
        String tab[]={"Newton2             Isaac               04011643",
        		"Einstein            Albert              14103879",
        		"Copernic            Nicolas             19021473"};
    	f=new File(url.toURI());
    	System.out.println("Lecture du fichier "+f.getPath()+" :");
    	in_str=new StringReader(tab[0]+tab[1]+tab[2]);
    	try{
	    	in=new StructAsciiReader<FieldsListChamps1>(in_str,FieldsListChamps1.class);
	    	list=new ArrayList<LineContentAscii<FieldsListChamps1>>();
	    	no=1;
	    	while((line=in.readLn())!=null)
	    	{
	    		assertTrue(no<=tab.length);
	    		assertEquals(tab[no-1],line.getLine());
	    		list.add(line);
	    		no++;
	    	}
	    	assertEquals("Error in line "+no,3,list.size());
    	}finally{
    		if(in!=null)
    			in.close();
    	}
	}
}
