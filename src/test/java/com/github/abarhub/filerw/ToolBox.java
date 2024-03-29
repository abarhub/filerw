/*
 * Copyright (c) 2012-2014. Alain Barret
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.abarhub.filerw;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class ToolBox {

    private ToolBox() {

    }

    public static String lecture(File f) throws IOException {
        BufferedInputStream in = null;
        int len;
        byte[] buf;
        StringBuilder res = new StringBuilder();
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            buf = new byte[512];
            while ((len = in.read(buf)) != -1) {
                res.append(new String(buf, 0, len));
            }
        } finally {
            if (in != null)
                in.close();
        }
        return res.toString();
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
        s2 = s2.replaceAll("\\r\\n", "\n");
        assertEquals(s, s2);
        return s.equals(s2);
    }

    public static String convertNewLine(String s) {
        if (s == null) {
            return s;
        } else {
            return s.replaceAll("\\r\\n", "\n");
        }
    }

}
