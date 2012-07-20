/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abarhub.filerw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.abarhub.filerw.FileContent;
import org.abarhub.filerw.ReadWrite;

/**
 *
 * @author abarhub
 */
public class Main {
    public static void main(String[] arg)
    {
        ReadWrite lecture;
        FileContent fichier;
        File f=new File("./data/exemple1.txt");
        try {
        	System.out.println("Lecture du fichier "+f.getPath()+" :");
            lecture=new ReadWrite(f,ListeChamps1.class);
            fichier=lecture.readFile();
            fichier.show();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
