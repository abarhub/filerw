package org.abarhub.filerw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

public class TestExample1 extends TestCase {

	public void test1()
	{
		ReadWriteAscii<FieldsListChamps1> lecture;
        FileContentAscii<FieldsListChamps1> fichier;
        File f;
        URL url = getClass().getResource("/data/exemple1.txt");
        try {
        	f=new File(url.toURI());
        	System.out.println("Lecture du fichier "+f.getPath()+" :");
            lecture=new ReadWriteAscii<FieldsListChamps1>(f,FieldsListChamps1.class);
            fichier=lecture.readFile();
            assertTrue(fichier!=null);
            fichier.show();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
        	 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
