/*
 * To change this template,   choose Tools | Templates
 * and open the template in the editor.
 */
package org.abarhub.filerw;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.abarhub.filerw.ascii.LineContentAscii;

/**
 *
 * @author abarhub
 */
public class FileContentAscii<T extends Field> {
    
    private List<LineContentAscii<T>> liste;
    
    public FileContentAscii()
    {
        liste=new ArrayList<LineContentAscii<T>>();
    }
    
    public void add(LineContentAscii<T> line)
    {
        liste.add(line);
    }

    public List<LineContentAscii<T>> getListe() {
        return liste;
    }
    
    
    public void show()
    {
        show(System.out);
    }
    
    public void show(PrintStream out)
    {
        if(liste!=null&&!liste.isEmpty())
        {
            for(int i=0;i<liste.size();i++)
            {
            	LineContent<T> line=liste.get(i);
                out.println("Line no "+i);
                line.show(out);
            }
        }
    }
}
