package org.abarhub.filerw;

import java.util.ArrayList;
import java.util.List;

public final class Tools {

	private Tools() {
		// TODO Auto-generated constructor stub
	}


	public static <T extends Field> int getSize(Class<T> clazz) {
		int res=0;
		List<T> listFields;
		listFields=convClassEnum(clazz);
    	for(T champs:listFields)
    	{
    		res=Math.max(res, champs.getPosition()+champs.getLength());
    	}
    	return res;
	}

	public static <T extends Field> int getSize(List<T> listFields) {
		int res=0;
    	for(T field:listFields)
    	{
    		res=Math.max(res, field.getPosition()+field.getLength());
    	}
    	return res;
	}


	public static <T> List<T> convClassEnum(Class<T> clazz) {
		List<T> fieldsList;
		fieldsList=new ArrayList<T>();
	    for (T option : clazz.getEnumConstants()) {
	        fieldsList.add(option);
	    }
	    return fieldsList;
	}
	

	public static boolean equals(byte[] tab,byte[] tab2)
	{
		if(tab==null)
		{
			return tab2==null;
		}
		else
		{
			if(tab2==null)
			{
				return false;
			}
			if(tab.length!=tab2.length)
			{
				return false;
			}
			for(int i=0;i<tab.length;i++)
			{
				if(tab[i]!=tab2[i])
				{
					return false;
				}
			}
			return true;
		}
	}
}
