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
import com.github.abarhub.filerw.Tools;

import java.io.*;
import java.text.ParseException;
import java.util.List;

public class ReadWriteBinary<T extends Field> {

    private File file;
    private final List<T> fieldsList;

    public ReadWriteBinary(File file, List<T> listFields) {
        this.file = file;
        this.fieldsList = listFields;
    }

    public ReadWriteBinary(File file, Class<T> clazz) {
        this.file = file;
        this.fieldsList = Tools.convClassEnum(clazz);
    }

    public FileContentBinary<T> readFile() throws IOException, ParseException {
        FileContentBinary<T> res;
        LineContentBinary<T> line;
        StructBinaryInputStream<T> buf = null;
        res = new FileContentBinary<T>();
        try {
            buf = new StructBinaryInputStream<T>(new BufferedInputStream(
                    new FileInputStream(file)), fieldsList);
            while ((line = buf.readLn()) != null) {
                res.add(line);
            }
        } finally {
            if (buf != null) {
                buf.close();
                buf = null;
            }
        }
        return res;
    }

    public void writeFile(File fileName, FileContentBinary<T> fileContent)
            throws IOException {
        StructBinaryOutpoutStream<T> out = null;
        try {
            out = new StructBinaryOutpoutStream<T>(new BufferedOutputStream(
                    new FileOutputStream(fileName)), fieldsList);
            if (fileContent != null && fileContent.getListe() != null) {
                for (LineContentBinary<T> ligne : fileContent.getListe()) {
                    out.writeLine(ligne);
                }
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

}
