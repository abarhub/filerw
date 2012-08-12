package org.abarhub.filerw.binary;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.Tools;

public class StructBinaryOutpoutStream<T extends Field> extends FilterOutputStream {

	protected List<T> fieldsList;
	
	public StructBinaryOutpoutStream(OutputStream out,Class<T> clazz) {
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

	public StructBinaryOutpoutStream(OutputStream out,List<T> fieldsList) {
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
	
	public void writeLine(LineContentBinary<T> line) throws IOException{
		write(line.getLine());
	}
}
