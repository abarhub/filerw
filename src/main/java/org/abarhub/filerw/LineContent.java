package org.abarhub.filerw;

import java.io.PrintStream;
import java.util.List;

public abstract class LineContent<T extends Field> {

	protected final List<T> fieldsList;
	
	public LineContent(List<T> fieldsList) {
		if(fieldsList==null||fieldsList.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.fieldsList=fieldsList;
	}

	public LineContent(Class<T> clazz) {
		if(clazz==null||!clazz.isEnum())
		{
			throw new IllegalArgumentException();
		}
		this.fieldsList=Tools.convClassEnum(clazz);
	}

	public List<T> getFieldsList() {
		return fieldsList;
	}
	
	public abstract void show(PrintStream out);
	
}
