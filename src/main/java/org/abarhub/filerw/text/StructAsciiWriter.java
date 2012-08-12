package org.abarhub.filerw.text;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.Tools;

public class StructAsciiWriter<T extends Field> extends PrintWriter {

	protected List<T> fieldsList;
	
	public StructAsciiWriter(Writer out,Class<T> clazz) {
		super(out);
		if(out==null)
		{
			throw new IllegalArgumentException();
		}
		if(clazz==null||!clazz.isEnum())
		{
			throw new IllegalArgumentException();
		}
		this.fieldsList=Tools.convClassEnum(clazz);
	}
	
	public StructAsciiWriter(Writer out,List<T> fieldsList) {
		super(out);
		if(out==null)
		{
			throw new IllegalArgumentException();
		}
		if(fieldsList==null||fieldsList.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.fieldsList=fieldsList;
	}
	
	public void writeLine(LineContentAscii<T> line){
		print(line.getLine());
	}

}
