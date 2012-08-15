package org.abarhub.filerw;

import java.util.ArrayList;
import java.util.List;

public class TestTools<T extends Enum<T>&Field> {

	private List<T> listFields;

	private String messageError;
	
	public TestTools(List<T> listfields)
	{
		this.listFields=listfields;
	}
	
	public TestTools(Class<T> clazz)
	{
		this.listFields=new ArrayList<T>();
        for (T option : clazz.getEnumConstants()) {
            listFields.add(option);
        }
	}
	
	public boolean testBasic()
	{
		T first=null;
		for(T tmp:listFields)
		{
			if(tmp.getPosition()<0)
			{
				messageError="La position du champs "+tmp+" est incorrecte";
				return false;
			}
			if(tmp.getLength()<=0)
			{
				messageError="la longueur du champs "+tmp+" est trop petite";
				return false;
			}
			if(tmp.getPosition()==0)
			{
				first=tmp;
			}
		}
		if(first==null)
		{
			messageError="il n'y a aucun champs pour la colonne no 0";
			return false;
		}
		return true;
	}

	public boolean testDuplicate()
	{
		List<T> tab;
		int len;
		tab=new ArrayList<T>();//[ChampsFixes.taille_ligne()];
		len=getSize();
		if(len<=0)
		{
			messageError="La taille n'est pas correcte";
			return false;
		}
		for(int i=0;i<len;i++)
		{
			tab.add(null);
		}
		
		for(T tmp:listFields)
		{
			for(int i=tmp.getPosition();i<tmp.getPosition()+tmp.getLength();i++)
			{
				if(tab.get(i)!=null)
				{
					messageError="La case n°"+i+" est associé a deux champs différents :"+tmp+" et "+tab.get(i);
					return false;
				}
				tab.set(i, tmp);
			}
		}
		for(int i=0;i<tab.size();i++)
		{
			if(tab.get(i)==null)
			{
				messageError="La case n°"+i+" n'est associée a aucun champs ";
				return false;
			}
		}
		return true;
	}
	
	private int getSize() {
		int res=0;
    	for(T champs:listFields)
    	{
    		res=Math.max(res, champs.getPosition()+champs.getLength());
    	}
    	return res;
	}

	public boolean testAll()
	{
		return testBasic() && testDuplicate();
	}
	
	public String getMessageError() {
		return messageError;
	}
}
