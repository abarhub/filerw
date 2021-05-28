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

package com.github.abarhub.filerw.text;

import com.github.abarhub.filerw.Field;
import com.github.abarhub.filerw.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.List;

public class StructTextReader<T extends Field> extends BufferedReader {

    private static final int SIZEBUFFER = 512;
    private final List<T> fieldsList;

    public List<T> getFieldsList() {
        return fieldsList;
    }

    public StructTextReader(Reader reader, Class<T> clazz) {
        this(reader, clazz, defaultSize(Tools.convClassEnum(clazz)));
    }

    public StructTextReader(Reader reader, List<T> fieldsList) {
        this(reader, fieldsList, defaultSize(fieldsList));
    }

    private static <T extends Field> int defaultSize(List<T> fieldsList2) {
        int res = SIZEBUFFER, i;
        if (fieldsList2 != null && !fieldsList2.isEmpty()) {
            i = Tools.getSize(fieldsList2);
            if (i > 0) {
                res = i;
            }
        }
        return res;
    }

    public StructTextReader(Reader reader, Class<T> clazz, int sz) {
        super(reader, sz);
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        if (clazz == null || !clazz.isEnum()) {
            throw new IllegalArgumentException();
        }
        this.fieldsList = Tools.convClassEnum(clazz);
    }

    public StructTextReader(Reader reader, List<T> fieldsList, int sz) {
        super(reader, sz);
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        if (fieldsList == null || fieldsList.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.fieldsList = fieldsList;
    }

    public LineContentText<T> readLn() throws IOException, ParseException {
        char[] buf = new char[Tools.getSize(fieldsList)];
        int len;
        LineContentText<T> res = null;
        String ligne;

        len = read(buf);
        if (len > 0) {
            if (len != Tools.getSize(fieldsList)) {
                throw new ParseException("Invalid Size (" + len + "!="
                        + Tools.getSize(fieldsList) + ")", len);
            }
            ligne = new String(buf);
            res = new LineContentText<>(fieldsList, ligne);
        }

        return res;
    }
}
