/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abarhub.filerw;

import java.io.*;
import java.util.*;

/**
 *
 * @author abarhub
 */
public class ReadWriteAscii<T extends Field> {
	//<T extends Enum<T>&Field>
    // le enum n'est pas necessaire, et peut être enlevé
    private File file;
    private final List<T> fieldsList;
    
    public ReadWriteAscii(File file,List<T> liste_champs){
        this.file=file;
        this.fieldsList=liste_champs;
    }
        
    public ReadWriteAscii(File file,Class<T> clazz){
        this.file=file;
        this.fieldsList=new ArrayList<T>();
        for (T option : clazz.getEnumConstants()) {
            fieldsList.add(option);
        }
    }
    
    public FileContentAscii<T> readFile() throws FileNotFoundException, IOException
    {
        FileContentAscii<T> res;
        BufferedReader buf=null;
        LineContentAscii<T> ligne;
        String ligne2;
        res=new FileContentAscii<T>();
        try{
            buf=new BufferedReader(new FileReader(file));
            while((ligne2=buf.readLine())!=null)
            {
                if(ligne2!=null&&ligne2.length()>0)
                {
                	ligne=new LineContentAscii<T>(fieldsList, ligne2);
                	res.add(ligne);
                }
            }
        }finally{
            if(buf!=null)
            {
                buf.close();
                buf=null;
            }
        }
        return res;
    }

    public void WriteFile(File fileName,FileContentAscii<T> contenu_fichier) throws IOException
    {
    	PrintWriter out=null;
    	try{
    		out=new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    		if(contenu_fichier!=null)
    		{
    			if(contenu_fichier.getListe()!=null)
    			{
    				for(LineContentAscii<T> ligne:contenu_fichier.getListe())
    				{
    					out.println(ligne.getLine());
    				}
    			}
    		}
    	}finally{
    		if(out!=null)
    		{
    			out.flush();
    			out.close();
    		}
    	}
    }
    
    private int getSize()
    {
    	int res=0;
    	for(T field:fieldsList)
    	{
    		res=Math.max(res, field.getPosition()+field.getLength());
    	}
    	return res;
    }
}
