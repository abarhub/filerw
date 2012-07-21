/*
 * To change this template,   choose Tools | Templates
 * and open the template in the editor.
 */
package org.abarhub.filerw;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            	LineContentAscii<T> line=liste.get(i);
                out.println("Line no "+i);
                line.show(out);
                /*for(Field champs:map.keySet())
                {
                    out.println(champs.name()+"="+map.get(champs));
                }*/
            }
        }
    }
}
