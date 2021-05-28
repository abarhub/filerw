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

package com.github.abarhub.filerw.binary;

import com.github.abarhub.filerw.Field;
import com.github.abarhub.filerw.LineContent;
import com.github.abarhub.filerw.Tools;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class LineContentBinary<T extends Field> extends LineContent<T> {

    private byte[] line;

    public LineContentBinary(List<T> fieldsList, byte[] line) {
        super(fieldsList);
        init(line);
    }

    public LineContentBinary(List<T> fieldsList) {
        super(fieldsList);
        init(null);
    }

    private void init(byte[] line) {
        int len, len2;
        len = Tools.getSize(fieldsList);
        if (len <= 0) {
            throw new IllegalArgumentException();
        }
        if (line != null && line.length > len) {
            throw new IllegalArgumentException();
        }
        this.line = new byte[len];
        if (line == null) {
            len2 = 0;
        } else if (line.length < len) {
            len2 = line.length;
        } else {
            len2 = len;
        }
        if (line != null) {
            System.arraycopy(line, 0, this.line, 0, len2);
        }
    }

    public LineContentBinary(Class<T> clazz) {
        super(clazz);
        init(null);
    }

    public LineContentBinary(Class<T> clazz, byte[] line) {
        super(clazz);
        init(line);
    }

    @Override
    public void show(PrintStream out) {
        byte tab[];
        for (T champs : fieldsList) {
            tab = get(champs);
            out.print(champs.name() + "=");
            if (tab != null) {
                boolean start = true;
                for (byte b : tab) {
                    if (!start) {
                        out.print(',');
                    }
                    out.print(b);
                    start = false;
                }
            }
            out.println();
        }
    }

    public byte[] get(T field) {
        byte[] res;
        res = new byte[field.getLength()];
        if (line != null && line.length > field.getPosition()
                && line.length >= field.getPosition() + field.getLength()) {
            for (int i = field.getPosition(); i < field.getPosition()
                    + field.getLength(); i++) {
                res[i - field.getPosition()] = line[i];
            }
        }
        return res;
    }

    public void setString(T field, byte[] value) {
        for (int i = field.getPosition(), j = 0; i < field.getPosition()
                + field.getLength(); i++, j++) {
            if (value != null && j < value.length) {
                line[i] = value[j];
            } else {
                line[i] = 0;
            }
        }
    }

    public byte[] getLine() {
        return line;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(line);
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof LineContentBinary)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        LineContentBinary<T> other = (LineContentBinary<T>) obj;
        if (!Arrays.equals(line, other.line)) {
            return false;
        }
        return true;
    }
}
