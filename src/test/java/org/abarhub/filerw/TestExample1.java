package org.abarhub.filerw;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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

	public void test2()
	{
		ReadWriteAscii<FieldsListChamps1> lecture;
        FileContentAscii<FieldsListChamps1> fichier;
        File f,f2;
        URL url = getClass().getResource("/data/exemple1.txt");
        try {
        	f=new File(url.toURI());
        	f2=new File(f.getAbsoluteFile().getParentFile(),"exemple1_1.txt");
        	System.out.println("Lecture du fichier "+f.getPath()+" :");
            lecture=new ReadWriteAscii<FieldsListChamps1>(f,FieldsListChamps1.class);
            fichier=lecture.readFile();
            assertTrue(fichier!=null);
            fichier.show();
            lecture.WriteFile(f2, fichier);
            assertTrue(compare(f,f2));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
        	 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void test3()
	{
		ReadWriteAscii<FieldsListChamps1> lecture;
        FileContentAscii<FieldsListChamps1> fichier;
        File f,f2;
        URL url = getClass().getResource("/data/exemple1.txt");
        try {
        	f=new File(url.toURI());
        	f2=new File(f.getAbsoluteFile().getParentFile(),"exemple1_2.txt");
        	System.out.println("Lecture du fichier "+f.getPath()+" :");
            lecture=new ReadWriteAscii<FieldsListChamps1>(f,FieldsListChamps1.class);
            lecture.setNewLineSeparator();
            fichier=lecture.readFile();
            assertTrue(fichier!=null);
            fichier.show();
            lecture.WriteFile(f2, fichier);
            assertTrue(compare(f,f2));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
        	 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void test4()
	{
		ReadWriteAscii<FieldsListChamps1> lecture;
        FileContentAscii<FieldsListChamps1> fichier;
        File f,f2;
        URL url = getClass().getResource("/data/exemple2.txt");
        try {
        	f=new File(url.toURI());
        	f2=new File(f.getAbsoluteFile().getParentFile(),"exemple2_1.txt");
        	System.out.println("Lecture du fichier "+f.getPath()+" :");
            lecture=new ReadWriteAscii<FieldsListChamps1>(f,FieldsListChamps1.class);
            lecture.setNoSeparator();
            fichier=lecture.readFile();
            assertTrue(fichier!=null);
            fichier.show();
            lecture.WriteFile(f2, fichier);
            assertTrue(compare(f,f2));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
        	 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private boolean compare(File f, File f2) throws IOException {
		String s,s2;
		s=lecture(f);
		assertNotNull(s);
		assertTrue(s.length()>0);
		s2=lecture(f2);
		assertNotNull(s2);
		assertTrue(s2.length()>0);
		assertEquals(s, s2);
		return s.equals(s2);
	}

	private String lecture(File f) throws IOException {
		BufferedInputStream in=null;
		int len;
		byte buf[];
		String res="";
		try{
			in=new BufferedInputStream(new FileInputStream(f));
			buf=new byte[512];
			while((len=in.read(buf))!=-1)
			{
				res+=new String(buf,0,len);
			}
		}finally{
			if(in!=null)
				in.close();
		}
		return res;
	}
}
