package org.abarhub.filerw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

public class TestExample1 extends TestCase {

	public void test1()
	{
		ReadWrite lecture;
        FileContent fichier;
        File f=new File("./data/exemple1.txt");
        try {
        	System.out.println("Lecture du fichier "+f.getPath()+" :");
            lecture=new ReadWrite(f,ListeChamps1.class);
            fichier=lecture.readFile();
            assertTrue(fichier!=null);
            fichier.show();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
