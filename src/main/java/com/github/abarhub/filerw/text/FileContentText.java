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

/*
 * To change this template,   choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.abarhub.filerw.text;

import com.github.abarhub.filerw.Field;
import com.github.abarhub.filerw.LineContent;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abarhub
 */
public class FileContentText<T extends Field> {

    private final List<LineContentText<T>> liste;

    public FileContentText() {
        liste = new ArrayList<>();
    }

    public void add(LineContentText<T> line) {
        liste.add(line);
    }

    public List<LineContentText<T>> getListe() {
        return liste;
    }

    public void show() {
        show(System.out);
    }

    public void show(PrintStream out) {
        if (!liste.isEmpty()) {
            for (int i = 0; i < liste.size(); i++) {
                LineContent<T> line = liste.get(i);
                out.println("Line no " + i);
                line.show(out);
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
        int result = 1;
        result = prime * result + liste.hashCode();
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
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FileContentText)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        FileContentText<T> other = (FileContentText<T>) obj;
        return liste.equals(other.liste);
    }
}
