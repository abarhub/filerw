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
import org.junit.Test;

public class TestReadWriteBinary {

	@Test
	public void testReadFile() throws URISyntaxException, FileNotFoundException, IOException, ParseException {
		/*ReadWriteBinary<FieldsListChamps2> lecture;
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
        		new byte[]{31,32,33,34,35,36,92,38,39,40,41,42,43,44,45,46,47}));*/
	}

	@Test
	public void testWriteFile() {
		//fail("Not yet implemented");
	}

	public File getFile1() throws URISyntaxException
	{
		 File f;
	     URL url = getClass().getResource("/data/exemplebin1.txt");
	     f=new File(url.toURI());
	     return f;
	}

}
