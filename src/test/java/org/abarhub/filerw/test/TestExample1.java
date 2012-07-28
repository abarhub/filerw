package org.abarhub.filerw.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;

import org.abarhub.filerw.FileContentAscii;
import org.abarhub.filerw.ReadWriteAscii;
import org.junit.Test;

public class TestExample1 {

	@Test
	public void test1() throws URISyntaxException, FileNotFoundException, IOException, ParseException
	{
		ReadWriteAscii<FieldsListChamps1> lecture;
        FileContentAscii<FieldsListChamps1> fichier;
        File f;
        URL url = getClass().getResource("/data/exemple1.txt");
        	f=new File(url.toURI());
        	System.out.println("Lecture du fichier "+f.getPath()+" :");
            lecture=new ReadWriteAscii<FieldsListChamps1>(f,FieldsListChamps1.class);
            fichier=lecture.readFile();
            assertTrue(fichier!=null);
            fichier.show();
        
	}

	@Test
	public void test2() throws URISyntaxException, FileNotFoundException, IOException, ParseException
	{
		ReadWriteAscii<FieldsListChamps1> lecture;
        FileContentAscii<FieldsListChamps1> fichier;
        File f,f2;
        URL url = getClass().getResource("/data/exemple1.txt");
       
        	f=new File(url.toURI());
        	f2=new File(f.getAbsoluteFile().getParentFile(),"exemple1_1.txt");
        	System.out.println("Lecture du fichier "+f.getPath()+" :");
            lecture=new ReadWriteAscii<FieldsListChamps1>(f,FieldsListChamps1.class);
            fichier=lecture.readFile();
            assertTrue(fichier!=null);
            fichier.show();
            lecture.WriteFile(f2, fichier);
            assertTrue(ToolBox.compare(f,f2));
	}

	@Test
	public void test3() throws URISyntaxException, FileNotFoundException, IOException, ParseException
	{
		ReadWriteAscii<FieldsListChamps1> lecture;
        FileContentAscii<FieldsListChamps1> fichier;
        File f,f2;
        URL url = getClass().getResource("/data/exemple1.txt");
        	f=new File(url.toURI());
        	f2=new File(f.getAbsoluteFile().getParentFile(),"exemple1_2.txt");
        	System.out.println("Lecture du fichier "+f.getPath()+" :");
            lecture=new ReadWriteAscii<FieldsListChamps1>(f,FieldsListChamps1.class);
            lecture.setNewLineSeparator();
            fichier=lecture.readFile();
            assertTrue(fichier!=null);
            fichier.show();
            lecture.WriteFile(f2, fichier);
            assertTrue(ToolBox.compare(f,f2));
	}

	@Test
	public void test4() throws URISyntaxException, FileNotFoundException, IOException, ParseException
	{
		ReadWriteAscii<FieldsListChamps1> lecture;
        FileContentAscii<FieldsListChamps1> fichier;
        File f,f2;
        URL url = getClass().getResource("/data/exemple2.txt");
        	f=new File(url.toURI());
        	f2=new File(f.getAbsoluteFile().getParentFile(),"exemple2_1.txt");
        	System.out.println("Lecture du fichier "+f.getPath()+" :");
            lecture=new ReadWriteAscii<FieldsListChamps1>(f,FieldsListChamps1.class);
            lecture.setNoSeparator();
            fichier=lecture.readFile();
            assertTrue(fichier!=null);
            fichier.show();
            lecture.WriteFile(f2, fichier);
            assertTrue(ToolBox.compare(f,f2));
	}
}
