package org.abarhub.filerw.test.binary;

import org.abarhub.filerw.Field;

public enum FieldsListChamps2 implements Field {
	Code1(0,4),Code2(4,6),Code3(10,7);

    private FieldsListChamps2(int position,int length)
    {
        this.position=position;
        this.length=length;
    }

    public int getLength() {
        return length;
    }

    public int getPosition() {
        return position;
    }
        
    private int position;
    private int length;

}
