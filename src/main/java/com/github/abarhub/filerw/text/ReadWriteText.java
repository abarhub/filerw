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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.abarhub.filerw.text;

import com.github.abarhub.filerw.Field;
import com.github.abarhub.filerw.Tools;

import java.io.*;
import java.text.ParseException;
import java.util.List;

/**
 * @author abarhub
 */
public class ReadWriteText<T extends Field> {
    // le enum n'est pas necessaire, et peut être enlevé
    private final File file;
    private final List<T> fieldsList;

    private enum Separator {
        NewLine, NoSeparator, String
    }

    private Separator separator = Separator.NewLine;
    private String stringSeparator;

    public ReadWriteText(File file, List<T> listFields) {
        this.file = file;
        this.fieldsList = listFields;
    }

    public ReadWriteText(File file, Class<T> clazz) {
        this(file, Tools.convClassEnum(clazz));
    }

    public FileContentText<T> readFile() throws IOException, ParseException {
        FileContentText<T> res;
        LineContentText<T> line;
        int i;
        res = new FileContentText<>();
        try (StructTextReader<T> buf = new StructTextReader<>(new BufferedReader(new FileReader(
                file)), fieldsList)) {
            loop:
            {
                while ((line = buf.readLn()) != null) {
                    res.add(line);
                    if (separator == Separator.NewLine) {
                        i = buf.read();
                        switch (i) {
                            case -1:// EOF
                                break loop;
                            case '\n':
                                break;
                            case '\r':
                                i = buf.read();
                                switch (i) {
                                    case -1: // EOF
                                        break loop;
                                    case '\n':
                                        break;
                                    default:
                                        throw new IOException("Bad format");
                                }
                                break;
                            default:
                                throw new IOException("Bad format");
                        }
                    } else if (separator == Separator.NoSeparator) {// nothing
                        // to do

                    } else {
                        assert (false);
                    }
                }
            }
        }
        return res;
    }

    public void writeFile(File fileName, FileContentText<T> fileContent)
            throws IOException {
        StructTextWriter<T> out = null;
        try {
            out = new StructTextWriter<>(new BufferedWriter(new FileWriter(
                    fileName)), fieldsList);
            if (fileContent != null && fileContent.getListe() != null) {
                for (LineContentText<T> ligne : fileContent.getListe()) {
                    out.writeLine(ligne);
                    if (separator == Separator.NewLine) {
                        out.println();
                    } else if (separator == Separator.NoSeparator) {

                    } else if (separator == Separator.String) {
                        if (stringSeparator != null
                                && stringSeparator.length() > 0) {
                            out.print(stringSeparator);
                        }
                    } else {
                        assert (false);
                    }
                }
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public void setNewLineSeparator() {
        separator = Separator.NewLine;
        stringSeparator = null;
    }

    public boolean isNewLineSeparator() {
        return separator == Separator.NewLine;
    }

    public void setNoSeparator() {
        separator = Separator.NoSeparator;
        stringSeparator = null;
    }

    public boolean isNoSeparator() {
        return separator == Separator.NoSeparator;
    }

    public void setStringSeparator(String sep) {
        if (sep == null) {
            throw new IllegalArgumentException();
        }
        separator = Separator.String;
        stringSeparator = sep;
    }

    public boolean isStringSeparator() {
        return separator == Separator.String;
    }
}
