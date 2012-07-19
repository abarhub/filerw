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
public class ReadWrite<T extends Field> {
	//<T extends Enum<T>&Field>
    // le enum n'est pas necessaire, et peut être enlevé
    private File file;
    private List<T> liste_champs;
    
    public ReadWrite(File file,List<T> liste_champs){
        this.file=file;
        this.liste_champs=liste_champs;
    }
        
    public ReadWrite(File file,Class<T> clazz){
        this.file=file;
        this.liste_champs=new ArrayList<T>();
        for (T option : clazz.getEnumConstants()) {
            liste_champs.add(option);
        }
    }
    
    public Fichier<T> readFile() throws FileNotFoundException, IOException
    {
        Fichier<T> res;
        BufferedReader buf=null;
        Map<T,String> ligne;
        String ligne2;
        res=new Fichier<T>();
        try{
            buf=new BufferedReader(new FileReader(file));
            while((ligne2=buf.readLine())!=null)
            {
                if(ligne2!=null&&ligne2.length()>0)
                {
                    ligne=decoupe(ligne2);
                    if(ligne!=null)
                    {
                        res.add(ligne);
                    }
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
    
    private Map<T, String> decoupe(String ligne) {
        Map<T, String> res;
        String s;
        //res=new EnumMap(ListeChamps.class);
        res=new HashMap<T,String>();
        for(T champs:liste_champs)
        {
            s=ligne.substring(champs.getPosition(), champs.getPosition()+champs.getLength());
            res.put(champs, s);
        }
        return res;
    }

    public void WriteFile(File nom_fichier,Fichier<T> contenu_fichier) throws IOException
    {
    	PrintWriter out=null;
    	try{
    		out=new PrintWriter(new BufferedWriter(new FileWriter(nom_fichier)));
    		if(contenu_fichier!=null)
    		{
    			if(contenu_fichier.getListe()!=null)
    			{
    				for(Map<T, String> ligne:contenu_fichier.getListe())
    				{
    					out.println(assemble(ligne));
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
    
    private String assemble(Map<T, String> ligne) {
        String valeur;
        StringBuilder buf;
        char c;
        buf=new StringBuilder();
        buf.setLength(getSize());
        for(T champs:ligne.keySet())
        {
        	valeur=ligne.get(champs);
        	if(valeur!=null&&valeur.length()>0)
        	{
        		for(int i=0;i<champs.getLength();i++)
        		{
        			c=valeur.charAt(i);
        			buf.setCharAt(champs.getPosition()+i, c);
        		}
        	}
        }
        return buf.toString();
    }
    
    private int getSize()
    {
    	int res=0;
    	for(T champs:liste_champs)
    	{
    		res=Math.max(res, champs.getPosition()+champs.getLength());
    	}
    	return res;
    }
}
