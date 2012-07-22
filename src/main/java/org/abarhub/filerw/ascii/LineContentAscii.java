package org.abarhub.filerw.ascii;

import java.io.PrintStream;
import java.util.List;

import org.abarhub.filerw.Field;

public class LineContentAscii<T extends Field> {

	private final List<T> fieldsList;
	private final StringBuilder line;
	
	public LineContentAscii(List<T> fieldsList, String line) {
		super();
		this.fieldsList = fieldsList;
		this.line = new StringBuilder(line);
	}

	public List<T> getFieldsList() {
		return fieldsList;
	}

	public String getLine() {
		return line.toString();
	}

    public void show(PrintStream out)
    {
    	for(T champs:fieldsList)
        {
            out.println(champs.name()+"="+getString(champs));
        }
    }
    
    public String getString(T field)
    {
    	String res=null;
    	if(line!=null&&line.length()>field.getPosition()
    		&&line.length()>=field.getPosition()+field.getLength())
    	{
    		res=line.substring(field.getPosition(), field.getPosition()+field.getLength());
    	}
    	return res;
    }
	
}
