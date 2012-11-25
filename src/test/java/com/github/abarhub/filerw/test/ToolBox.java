package com.github.abarhub.filerw.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static junit.framework.Assert.*;

public class ToolBox {

	private ToolBox() {

	}

	public static String lecture(File f) throws IOException {
		BufferedInputStream in = null;
		int len;
		byte buf[];
		String res = "";
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			buf = new byte[512];
			while ((len = in.read(buf)) != -1) {
				res += new String(buf, 0, len);
			}
		} finally {
			if (in != null)
				in.close();
		}
		return res;
	}

	public static boolean compare(File f, File f2) throws IOException {
		String s, s2;
		s = lecture(f);
		assertNotNull(s);
		assertTrue(s.length() > 0);
		s2 = lecture(f2);
		assertNotNull(s2);
		assertTrue(s2.length() > 0);
		s = s.replaceAll("\\r\\n", "\n");
		assertEquals(s, s2);
		return s.equals(s2);
	}

}
