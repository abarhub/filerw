package org.abarhub.filerw.binary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.abarhub.filerw.Field;
import org.abarhub.filerw.Tools;

public class ReadWriteBinary<T extends Field> {

	private File file;
    private final List<T> fieldsList;
    
	public ReadWriteBinary(File file,List<T> liste_champs) {
		this.file=file;
        this.fieldsList=liste_champs;
	}

	public ReadWriteBinary(File file,Class<T> clazz){
        this.file=file;
        this.fieldsList=Tools.convClassEnum(clazz);
    }
	

	public FileContentBinary<T> readFile() throws FileNotFoundException, IOException, ParseException
    {
		FileContentBinary<T> res;
        LineContentBinary<T> ligne;
        StructBinaryInputStream<T> buf=null;
        res=new FileContentBinary<T>();
        try{
        	buf=new StructBinaryInputStream<T>(new BufferedInputStream(new FileInputStream(file)),fieldsList);
        	while((ligne=buf.readLn())!=null)
            {
            	res.add(ligne);
            }
		}finally{
            if(buf!=null)
            {
                buf.close();
                buf=null;
            }
        }
        return res;
    }

    public void writeFile(File fileName,FileContentBinary<T> contenu_fichier) throws IOException
    {
    	StructBinaryOutpoutStream<T> out=null;
    	try{
    		out=new StructBinaryOutpoutStream<T>(new BufferedOutputStream(new FileOutputStream(fileName)),fieldsList);
    		if(contenu_fichier!=null)
    		{
    			if(contenu_fichier.getListe()!=null)
    			{
    				for(LineContentBinary<T> ligne:contenu_fichier.getListe())
    				{
    					out.writeLine(ligne);
    				}
    			}
    		}
    	}finally{
    		if(out!=null)
    		{
    			out.flush();
    			out.close();
    		}
    	}
    }
    
}
