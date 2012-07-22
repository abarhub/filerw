package org.abarhub.filerw;

import java.util.ArrayList;
import java.util.List;

public class Tools {

	private Tools() {
		// TODO Auto-generated constructor stub
	}


	public static <T extends Field> int getSize(Class<T> clazz) {
		int res=0;
		List<T> liste_champs;
		liste_champs=convClassEnum(clazz);
    	for(T champs:liste_champs)
    	{
    		res=Math.max(res, champs.getPosition()+champs.getLength());
    	}
    	return res;
	}

	public static <T extends Field> int getSize(List<T> liste_champs) {
		int res=0;
    	for(T champs:liste_champs)
    	{
    		res=Math.max(res, champs.getPosition()+champs.getLength());
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
}
