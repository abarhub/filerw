package org.abarhub.filerw.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.text.ParseException;
import java.util.List;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.Tools;

public class StructTextReader<T extends Field> extends BufferedReader {

	protected List<T> fieldsList;
	
	public StructTextReader(Reader reader,Class<T> clazz) {
		super(reader,defaultSize(Tools.convClassEnum(clazz)));
		if(reader==null)
		{
			throw new IllegalArgumentException();
		}
		if(clazz==null||!clazz.isEnum())
		{
			throw new IllegalArgumentException();
		}
		this.fieldsList=Tools.convClassEnum(clazz);
	}
	
	public StructTextReader(Reader reader,List<T> fieldsList) {
		super(reader,defaultSize(fieldsList));
		if(reader==null)
		{
			throw new IllegalArgumentException();
		}
		if(fieldsList==null||fieldsList.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.fieldsList=fieldsList;
	}

	private static <T extends Field> int defaultSize(List<T> fieldsList2) {
		int res=512,i;
		if(fieldsList2!=null&&!fieldsList2.isEmpty())
		{
			i=Tools.getSize(fieldsList2);
			if(i>0)
				res=i;
		}
		return res;
	}

	public StructTextReader(Reader reader,Class<T> clazz,int sz) {
		super(reader,sz);
		if(reader==null)
		{
			throw new IllegalArgumentException();
		}
		if(clazz==null||!clazz.isEnum())
		{
			throw new IllegalArgumentException();
		}
		this.fieldsList=Tools.convClassEnum(clazz);
	}
	
	public StructTextReader(Reader reader,List<T> fieldsList,int sz) {
		super(reader,sz);
		if(reader==null)
		{
			throw new IllegalArgumentException();
		}
		if(fieldsList==null||fieldsList.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.fieldsList=fieldsList;
	}
	
	
	
	public LineContentText<T> readLn() throws IOException, ParseException
	{
		char buf[]=new char[Tools.getSize(fieldsList)];
    	int len;
    	LineContentText<T> res=null;
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
            	res=new LineContentText<T>(fieldsList, ligne);
            }
        }
    	
    	return res;
	}
}
