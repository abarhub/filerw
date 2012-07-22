package org.abarhub.filerw.ascii;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.List;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.Tools;

public class StructAsciiReader<T extends Field> extends Reader {

	protected Reader reader;
	protected List<T> fieldsList;
	
	public StructAsciiReader(Reader reader,Class<T> clazz) {
		if(reader==null)
		{
			throw new IllegalArgumentException();
		}
		if(clazz==null)
		{
			throw new IllegalArgumentException();
		}
		this.reader=reader;
		this.fieldsList=Tools.convClassEnum(clazz);
	}
	
	public StructAsciiReader(Reader reader,List<T> fieldsList) {
		if(reader==null)
		{
			throw new IllegalArgumentException();
		}
		if(fieldsList==null||fieldsList.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.reader=reader;
		this.fieldsList=fieldsList;
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return reader.read(cbuf,off,len);
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

	@Override
	public int read() throws IOException {
		return reader.read();
	}
	
	@Override
	public int read(char[] cbuf) throws IOException {
		return reader.read(cbuf);
	}
	
	@Override
	public int read(CharBuffer target) throws IOException {
		return reader.read(target);
	}
	
	@Override
	public boolean ready() throws IOException {
		return reader.ready();
	}
	
	@Override
	public void reset() throws IOException {
		reader.reset();
	}
	
	@Override
	public boolean markSupported() {
		return reader.markSupported();
	}
	
	@Override
	public void mark(int readAheadLimit) throws IOException {
		reader.mark(readAheadLimit);
	}
	
	@Override
	public long skip(long n) throws IOException {
		return reader.skip(n);
	}
	
	public LineContentAscii<T> readStruct() throws IOException
	{
		char buf[]=new char[Tools.getSize(fieldsList)];
    	int len;
    	LineContentAscii<T> res=null;
    	String ligne;
    	
    	len=reader.read(buf);
    	if(len!=-1)
        {
            if(len>0)
            {
            	ligne=new String(buf);
            	res=new LineContentAscii<T>(fieldsList, ligne);
            }
        }
    	
    	return res;
	}
}
