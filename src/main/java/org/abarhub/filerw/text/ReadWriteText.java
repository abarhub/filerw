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
    
    public ReadWriteText(File file,List<T> listFields){
        this.file=file;
        this.fieldsList=listFields;
    }
    
    public ReadWriteText(File file,Class<T> clazz){
    	this(file,Tools.convClassEnum(clazz));
    }

	public FileContentText<T> readFile() throws FileNotFoundException, IOException, ParseException
    {
        FileContentText<T> res;
        LineContentText<T> line;
        StructTextReader<T> buf=null;
        int i;
        res=new FileContentText<T>();
        try{
        	buf=new StructTextReader<T>(new BufferedReader(new FileReader(file)),fieldsList);
        	loop:{
            while((line=buf.readLn())!=null)
            {
            	res.add(line);
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

    public void writeFile(File fileName,FileContentText<T> fileContent) throws IOException
    {
    	StructTextWriter<T> out=null;
    	try{
    		out=new StructTextWriter<T>(new BufferedWriter(new FileWriter(fileName)),fieldsList);
    		if(fileContent!=null)
    		{
    			if(fileContent.getListe()!=null)
    			{
    				for(LineContentText<T> ligne:fileContent.getListe())
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
    						{
    							out.print(stringSeparator);
    						}
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
    
    public boolean isNewLineSeparator()
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
    	{
    		throw new IllegalArgumentException();
    	}
    	separator=Separator.String;
    	stringSeparator=sep;
    }
    
    public boolean isStringSeparator()
    {
    	return separator==Separator.String;
    }
}
