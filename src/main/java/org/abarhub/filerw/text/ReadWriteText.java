/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abarhub.filerw.text;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.Tools;

/**
 *
 * @author abarhub
 */
public class ReadWriteText<T extends Field> {
	//<T extends Enum<T>&Field>
    // le enum n'est pas necessaire, et peut être enlevé
    private File file;
    private final List<T> fieldsList;
    private enum Separator{NewLine,NoSeparator,String};
    private Separator separator=Separator.NewLine;
    private String stringSeparator;
    
    public ReadWriteText(File file,List<T> liste_champs){
        this.file=file;
        this.fieldsList=liste_champs;
    }
    
    public ReadWriteText(File file,Class<T> clazz){
        this.file=file;
        this.fieldsList=Tools.convClassEnum(clazz);
    }

	public FileContentText<T> readFile() throws FileNotFoundException, IOException, ParseException
    {
        FileContentText<T> res;
        LineContentText<T> ligne;
        StructTextReader<T> buf=null;
        int i;
        res=new FileContentText<T>();
        try{
        	buf=new StructTextReader<T>(new BufferedReader(new FileReader(file)),fieldsList);
        	loop:{
            while((ligne=buf.readLn())!=null)
            {
            	res.add(ligne);
            	if(separator==Separator.NewLine)
                {
            		i=buf.read();
            		switch(i)
            		{
            		case -1:// EOF
            			break loop;
            		case '\n':
            			break;
            		case '\r':
            			i=buf.read();
            			switch(i)
            			{
            			case -1: // EOF
            				break loop;
            			case '\n':
            				break;
            			default:
                			throw new IOException("Bad format");
            			}
            			break;
            		default:
            			throw new IOException("Bad format");
            		}
                }
            	else if(separator==Separator.NoSeparator)
                {// nothing to do
            		
                }
                else
                {
                	assert(false);
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

    public void writeFile(File fileName,FileContentText<T> contenu_fichier) throws IOException
    {
    	StructTextWriter<T> out=null;
    	try{
    		out=new StructTextWriter<T>(new BufferedWriter(new FileWriter(fileName)),fieldsList);
    		if(contenu_fichier!=null)
    		{
    			if(contenu_fichier.getListe()!=null)
    			{
    				for(LineContentText<T> ligne:contenu_fichier.getListe())
    				{
    					out.writeLine(ligne);
    					if(separator==Separator.NewLine)
    					{
    						out.println();
    					}
    					else if(separator==Separator.NoSeparator)
    					{
    						
    					}
    					else if(separator==Separator.String)
    					{
    						if(stringSeparator!=null&&stringSeparator.length()>0)
    							out.print(stringSeparator);
    					}
    					else
    					{
    						assert(false);
    					}
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
}
