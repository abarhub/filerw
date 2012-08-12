package org.abarhub.filerw.binary;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.Tools;

public class StructBinaryInputStream<T extends Field> extends FilterInputStream {

	protected List<T> fieldsList;
	
	public StructBinaryInputStream(InputStream in,Class<T> clazz) {
		super(in);
		if(in==null)
		{
			throw new IllegalArgumentException();
		}
		if(clazz==null||!clazz.isEnum())
		{
			throw new IllegalArgumentException();
		}
		this.fieldsList=Tools.convClassEnum(clazz);
	}

	public StructBinaryInputStream(InputStream in,List<T> fieldsList) {
		super(in);
		if(in==null)
		{
			throw new IllegalArgumentException();
		}
		if(fieldsList==null||fieldsList.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.fieldsList=fieldsList;
	}
	

	public LineContentBinary<T> readLn() throws IOException, ParseException
	{
		byte buf[]=new byte[Tools.getSize(fieldsList)];
    	int len;
    	LineContentBinary<T> res=null;
    	
    	len=read(buf);
    	if(len!=-1)
        {
            if(len>0)
            {
            	if(len!=Tools.getSize(fieldsList))
            	{
            		throw new ParseException("Invalid Size ("+len+"!="+Tools.getSize(fieldsList)+")",len);
            	}
            	res=new LineContentBinary<T>(fieldsList, buf);
            }
        }
    	
    	return res;
	}
}
