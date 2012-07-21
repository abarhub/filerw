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
    private enum Separator{NewLine,NoSeparator,String};
    private Separator separator=Separator.NewLine;
    private String stringSeparator;
    
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
            if(separator==Separator.NewLine)
            {
	            while((ligne2=buf.readLine())!=null)
	            {
	                if(ligne2!=null&&ligne2.length()>0)
	                {
	                	ligne=new LineContentAscii<T>(fieldsList, ligne2);
	                	res.add(ligne);
	                }
	            }
            }
            else
            {
            	char buf2[]=new char[getSize()];
            	int len;
            	while((len=buf.read(buf2))!=-1)
	            {
	                if(len>0)
	                {
	                	ligne2=new String(buf2);
	                	ligne=new LineContentAscii<T>(fieldsList, ligne2);
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
    
    public void setNewLineSeparator()
    {
    	separator=Separator.NewLine;
    	stringSeparator=null;
    }
    
    public boolean isNewLineException()
    {
    	return separator==Separator.NewLine;
    }
    
    public void setNoSeparator()
    {
    	separator=Separator.NoSeparator;
    	stringSeparator=null;
    }

    public boolean isNoSeparator()
    {
    	return separator==Separator.NoSeparator;
    }
    
    public void setStringSeparator(String sep)
    {
    	if(sep==null)
    		throw new IllegalArgumentException();
    	separator=Separator.String;
    	stringSeparator=sep;
    }
    
    public boolean isStringSeparator()
    {
    	return separator==Separator.String;
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
