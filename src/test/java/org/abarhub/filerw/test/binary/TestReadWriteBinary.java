package org.abarhub.filerw.test.binary;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;

import org.abarhub.filerw.Tools;
import org.abarhub.filerw.binary.FileContentBinary;
import org.abarhub.filerw.binary.ReadWriteBinary;
import org.abarhub.filerw.test.ToolBox;
import org.junit.Test;

public class TestReadWriteBinary {

	@Test
	public void testReadFile() throws URISyntaxException, FileNotFoundException, IOException, ParseException {
		ReadWriteBinary<FieldsListChamps2> lecture;
        FileContentBinary<FieldsListChamps2> fichier;
        File f;
        f=getFile1();
    	System.out.println("Lecture du fichier "+f.getPath()+" :");
        lecture=new ReadWriteBinary<FieldsListChamps2>(f,FieldsListChamps2.class);
        fichier=lecture.readFile();
        assertTrue(fichier!=null);
        fichier.show();
        
        assertTrue(fichier.getListe()!=null);
        assertFalse(fichier.getListe().isEmpty());
        assertTrue(fichier.getListe().size()==1);
        assertTrue(Tools.equals(fichier.getListe().get(0).getLine(),
        		new byte[]{49,50,51,52,53,54,55,56,57,48,49,50,51,52,53,54,55}));
	}

	@Test
	public void testWriteFile() throws URISyntaxException, FileNotFoundException, IOException, ParseException {
		ReadWriteBinary<FieldsListChamps2> lecture;
        FileContentBinary<FieldsListChamps2> fichier;
        File f,f2;
        f=getFile2();
    	System.out.println("Lecture du fichier "+f.getPath()+" :");
        lecture=new ReadWriteBinary<FieldsListChamps2>(f,FieldsListChamps2.class);
        
        fichier=lecture.readFile();
        assertTrue(fichier!=null);
        fichier.show();
        
        assertTrue(fichier.getListe()!=null);
        assertFalse(fichier.getListe().isEmpty());
        assertEquals(2,fichier.getListe().size());
        assertTrue(Tools.equals(fichier.getListe().get(0).getLine(),
        		new byte[]{49,50,51,52,53,54,55,56,57,48,49,50,51,52,53,54,55}));
        assertTrue(Tools.equals(fichier.getListe().get(1).getLine(),
        		new byte[]{56,57,48,49,50,51,52,53,54,55,56,57,48,49,50,51,52}));
        
        f2=new File(f.getAbsoluteFile().getParentFile(),"exemplebin2_1.txt");
        lecture.writeFile(f2, fichier);
        
        assertTrue(ToolBox.compare(f,f2));
	}

	public File getFile1() throws URISyntaxException
	{
		 File f;
	     URL url = getClass().getResource("/data/exemplebin1.txt");
	     f=new File(url.toURI());
	     return f;
	}


	public File getFile2() throws URISyntaxException
	{
		 File f;
	     URL url = getClass().getResource("/data/exemplebin2.txt");
	     f=new File(url.toURI());
	     return f;
	}

}
