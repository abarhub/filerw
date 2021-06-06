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
import java.util.Arrays;
import java.util.List;

/**
 * @author abarhub
 */
public class ReadWriteText<T extends Field> {
    // le enum n'est pas necessaire, et peut être enlevé
    private final File file;
    private final List<T> fieldsList;

    private enum Separator {
        NEW_LINE, SEPARATOR, STRING
    }

    private Separator separator = Separator.NEW_LINE;
    private String stringSeparator;

    public ReadWriteText(File file, List<T> listFields) {
        this.file = file;
        this.fieldsList = listFields;
    }

    public ReadWriteText(File file, Class<T> clazz) {
        this(file, Tools.convClassEnum(clazz));
    }

    public FileContentText<T> readFile() throws IOException, ParseException {
        LineContentText<T> line;
        FileContentText<T> res = new FileContentText<>();
        try (StructTextReader<T> buf = new StructTextReader<>(new BufferedReader(new FileReader(
                file)), fieldsList)) {
            loop:
            {
                while ((line = buf.readLn()) != null) {
                    res.add(line);
                    if (separator == Separator.NEW_LINE) {
                        if (readNewLine(buf)) {
                            break loop;
                        }
                    } else if (separator == Separator.SEPARATOR) {// nothing
                        // to do
                    } else if (separator == Separator.STRING) {
                        if (readLineSeparator(buf)) {
                            break loop;
                        }
                    } else {
                        throw new IOException("Type de separateur inconnu");
                    }
                }
            }
        }
        return res;
    }

    private boolean readLineSeparator(StructTextReader<T> buf) throws IOException {
        char[] buf2 = new char[stringSeparator.length()];
        int nb = buf.read(buf2);
        if (nb == -1) {
            return true;
        } else {
            if (nb < stringSeparator.length()) {
                throw new IOException("Le separateur n'a pas la bonne taille");
            } else if (!Arrays.equals(buf2, stringSeparator.toCharArray())) {
                throw new IOException("Le séparateur n'est pas correcte (attendu='" + stringSeparator + "',reel='" + new String(buf2) + "')");
            }
        }
        return false;
    }

    private boolean readNewLine(StructTextReader<T> buf) throws IOException {
        int i = buf.read();
        switch (i) {
            case -1:// EOF
                return true;
            case '\n':
                break;
            case '\r':
                i = buf.read();
                switch (i) {
                    case -1: // EOF
                        return true;
                    case '\n':
                        break;
                    default:
                        throw new IOException("Bad format");
                }
                break;
            default:
                throw new IOException("Bad format");
        }
        return false;
    }

    public void writeFile(File fileName, FileContentText<T> fileContent)
            throws IOException {
        try (StructTextWriter<T> out = new StructTextWriter<>(new BufferedWriter(new FileWriter(
                fileName)), fieldsList)) {
            if (fileContent != null && fileContent.getListe() != null) {
                for (LineContentText<T> ligne : fileContent.getListe()) {
                    out.writeLine(ligne);
                    if (separator == Separator.NEW_LINE) {
                        out.println();
                    } else if (separator == Separator.SEPARATOR) {

                    } else if (separator == Separator.STRING) {
                        if (stringSeparator != null
                                && stringSeparator.length() > 0) {
                            out.print(stringSeparator);
                        }
                    } else {
                        throw new IOException("Type de separateur inconnu");
                    }
                }
            }
            out.flush();
        }
    }

    public void setNewLineSeparator() {
        separator = Separator.NEW_LINE;
        stringSeparator = null;
    }

    public boolean isNewLineSeparator() {
        return separator == Separator.NEW_LINE;
    }

    public void setNoSeparator() {
        separator = Separator.SEPARATOR;
        stringSeparator = null;
    }

    public boolean isNoSeparator() {
        return separator == Separator.SEPARATOR;
    }

    public void setStringSeparator(String sep) {
        if (sep == null) {
            throw new IllegalArgumentException();
        }
        separator = Separator.STRING;
        stringSeparator = sep;
    }

    public boolean isStringSeparator() {
        return separator == Separator.STRING;
    }
}
