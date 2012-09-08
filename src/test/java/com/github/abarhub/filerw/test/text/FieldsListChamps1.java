/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.abarhub.filerw.test.text;

import com.github.abarhub.filerw.Field;

/**
 * 
 * @author abarhub
 */
public enum FieldsListChamps1 implements Field {
	Nom(0, 20), Prenom(20, 20), DateNaissance(40, 8);

	private FieldsListChamps1(int position, int length) {
		this.position = position;
		this.length = length;
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
