/*
 * To change this template,  choose Tools | Templates
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
public class Fichier<T extends Field> {
    
    private List<Map<T,String>> liste;
    
    public Fichier()
    {
        liste=new ArrayList<Map<T, String>>();
    }
    
    public void add(Map<T, String> map)
    {
        liste.add(map);
    }

    public List<Map<T, String>> getListe() {
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
                Map<T,String> map=liste.get(i);
                out.println("Line no "+i);
                for(Field champs:map.keySet())
                {
                    out.println(champs.name()+"="+map.get(champs));
                }
            }
        }
    }
}
