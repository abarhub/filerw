package org.abarhub.filerw.ascii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.text.ParseException;
import java.util.List;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.Tools;

public class StructAsciiReader<T extends Field> extends BufferedReader {

	//protected Reader reader;
	protected List<T> fieldsList;
	
	public StructAsciiReader(Reader reader,Class<T> clazz) {
		super(reader);
		if(reader==null)
		{
			throw new IllegalArgumentException();
		}
		if(clazz==null||!clazz.isEnum())
		{
			throw new IllegalArgumentException();
		}
		//this.reader=reader;
		this.fieldsList=Tools.convClassEnum(clazz);
	}
	
	public StructAsciiReader(Reader reader,List<T> fieldsList) {
		super(reader);
		if(reader==null)
		{
			throw new IllegalArgumentException();
		}
		if(fieldsList==null||fieldsList.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		//this.reader=reader;
		this.fieldsList=fieldsList;
	}
	
	public LineContentAscii<T> readLn() throws IOException, ParseException
	{
		char buf[]=new char[Tools.getSize(fieldsList)];
    	int len;
    	LineContentAscii<T> res=null;
    	String ligne;
    	
    	len=read(buf);
    	if(len!=-1)
        {
            if(len>0)
            {
            	if(len!=Tools.getSize(fieldsList))
            	{
            		throw new ParseException("Invalid Size ("+len+"!="+Tools.getSize(fieldsList)+")",len);
            	}
            	ligne=new String(buf);
            	res=new LineContentAscii<T>(fieldsList, ligne);
            }
        }
    	
    	return res;
	}
}
