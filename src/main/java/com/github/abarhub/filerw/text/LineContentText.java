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
import com.github.abarhub.filerw.LineContent;
import com.github.abarhub.filerw.Tools;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;

public class LineContentText<T extends Field> extends LineContent<T> {

    private final StringBuilder line;

    public LineContentText(List<T> fieldsList, String line) {
        super(fieldsList);
        int len;
        len = Tools.getSize(fieldsList);
        if (line.length() > len) {
            throw new IllegalArgumentException();
        }
        if (len <= 0) {
            throw new IllegalArgumentException();
        }
        this.line = new StringBuilder(line);
        complete(len);
    }

    private void complete(int len) {
        if (this.line.length() < len) {
            int len2;
            len2 = this.line.length();
            this.line.setLength(len);
            for (int i = len2; i < len; i++) {
                this.line.setCharAt(i, ' ');
            }
        }
    }

    public LineContentText(Class<T> fieldsList, String line) {
        super(fieldsList);
        int len;
        len = Tools.getSize(fieldsList);
        if (line.length() > len) {
            throw new IllegalArgumentException();
        }
        if (len <= 0) {
            throw new IllegalArgumentException();
        }
        this.line = new StringBuilder(line);
        complete(len);
    }

    public LineContentText(List<T> fieldsList) {
        super(fieldsList);
        int len;
        len = Tools.getSize(fieldsList);
        if (len <= 0) {
            throw new IllegalArgumentException();
        }
        this.line = new StringBuilder();
        complete(len);
    }

    public LineContentText(Class<T> fieldsList) {
        super(fieldsList);
        int len;
        len = Tools.getSize(fieldsList);
        if (len <= 0) {
            throw new IllegalArgumentException();
        }
        this.line = new StringBuilder();
        complete(len);
    }

    public String getLine() {
        return line.toString();
    }

    @Override
    public void show(PrintStream out) {
        for (T champs : fieldsList) {
            out.println(champs.name() + "=" + getString(champs));
        }
    }

    public String getString(T field) {
        String res = null;
        if (line != null && line.length() > field.getPosition()
                && line.length() >= field.getPosition() + field.getLength()) {
            res = line.substring(field.getPosition(), field.getPosition()
                    + field.getLength());
        }
        return res;
    }

    public void setString(T field, String value) {
        for (int i = field.getPosition(), j = 0; i < field.getPosition()
                + field.getLength(); i++, j++) {
            if (value != null && j < value.length()) {
                line.setCharAt(i, value.charAt(j));
            } else {
                line.setCharAt(i, ' ');
            }
        }
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
        result = prime * result + getLine().hashCode();
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
        if (!(obj instanceof LineContentText)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        LineContentText<T> other = (LineContentText<T>) obj;
        return Objects.equals(getLine(),other.getLine());
    }

}
