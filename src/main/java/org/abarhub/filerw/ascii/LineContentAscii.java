package org.abarhub.filerw.ascii;

import java.io.PrintStream;
import java.util.List;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.LineContent;
import org.abarhub.filerw.Tools;

public class LineContentAscii<T extends Field> extends LineContent<T> {

	//private final List<T> fieldsList;
	private final StringBuilder line;
	
	public LineContentAscii(List<T> fieldsList, String line) {
		super(fieldsList);
		int len;
		len=Tools.getSize(fieldsList);
		/*if(fieldsList==null||fieldsList.isEmpty())
		{
			throw new IllegalArgumentException();
		}*/
		if(line.length()>len)
		{
			throw new IllegalArgumentException();
		}
		if(len<=0)
		{
			throw new IllegalArgumentException();
		}
		//this.fieldsList = fieldsList;
		this.line = new StringBuilder(line);
		complete(len);
	}

	private void complete(int len) {
		if(this.line.length()<len)
		{
			int len2;
			len2=this.line.length();
			this.line.setLength(len);
			for(int i=len2;i<len;i++)
			{
				this.line.setCharAt(i, ' ');
			}
		}
	}

	public LineContentAscii(Class<T> fieldsList, String line) {
		super(fieldsList);
		int len;
		len=Tools.getSize(fieldsList);
		if(line.length()>len)
		{
			throw new IllegalArgumentException();
		}
		/*if(fieldsList==null||!fieldsList.isEnum())
		{
			throw new IllegalArgumentException();
		}*/
		if(len<=0)
		{
			throw new IllegalArgumentException();
		}
		//this.fieldsList = Tools.convClassEnum(fieldsList);
		this.line = new StringBuilder(line);
		complete(len);
	}

	public LineContentAscii(List<T> fieldsList) {
		super(fieldsList);
		int len;
		len=Tools.getSize(fieldsList);
		/*if(fieldsList==null||fieldsList.isEmpty())
		{
			throw new IllegalArgumentException();
		}*/
		if(len<=0)
		{
			throw new IllegalArgumentException();
		}
		//this.fieldsList = fieldsList;
		this.line = new StringBuilder();
		complete(len);
	}

	public LineContentAscii(Class<T> fieldsList) {
		super(fieldsList);
		int len;
		len=Tools.getSize(fieldsList);
		/*if(fieldsList==null||!fieldsList.isEnum())
		{
			throw new IllegalArgumentException();
		}*/
		if(len<=0)
		{
			throw new IllegalArgumentException();
		}
		//this.fieldsList = Tools.convClassEnum(fieldsList);
		this.line = new StringBuilder();
		complete(len);
	}

	/*public List<T> getFieldsList() {
		return fieldsList;
	}*/

	public String getLine() {
		return line.toString();
	}

	@Override
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
    
    public void setString(T field,String value)
    {
    	for(int i=field.getPosition(),j=0;i<field.getPosition()+field.getLength();i++,j++)
    	{
    		if(value!=null&&j<value.length())
    		{
    			line.setCharAt(i, value.charAt(j));
    		}
    		else
    		{
    			line.setCharAt(i, ' ');
    		}
    	}
    }
	
}
