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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestTools<T extends Field> {

    private final List<T> listFields;

    private String messageError;

    public TestTools(List<T> listfields) {
        this.listFields = listfields;
    }

    public TestTools(Class<T> clazz) {
        this.listFields = new ArrayList<>(Tools.convClassEnum(clazz));
    }

    public boolean testBasic() {
        T first = null;
        for (T tmp : listFields) {
            if (tmp.getPosition() < 0) {
                messageError = "La position du champs " + tmp
                        + " est incorrecte";
                return false;
            }
            if (tmp.getLength() <= 0) {
                messageError = "La longueur du champs " + tmp
                        + " est trop petite";
                return false;
            }
            if (tmp.getPosition() == 0) {
                first = tmp;
            }
        }
        if (first == null) {
            messageError = "Il n'y a aucun champs pour la colonne no 0";
            return false;
        }
        return true;
    }

    public boolean testDuplicate() {
        List<T> tab;
        int len;
        tab = new ArrayList<>();
        len = getSize();
        if (len <= 0) {
            messageError = "La taille n'est pas correcte";
            return false;
        }
        for (int i = 0; i < len; i++) {
            tab.add(null);
        }

        for (T tmp : listFields) {
            for (int i = tmp.getPosition(); i < tmp.getPosition()
                    + tmp.getLength(); i++) {
                if (tab.get(i) != null) {
                    messageError = "La case n°" + i
                            + " est associé a deux champs différents :" + tmp
                            + " et " + tab.get(i);
                    return false;
                }
                tab.set(i, tmp);
            }
        }
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i) == null) {
                messageError = "La case n°" + i
                        + " n'est associée a aucun champs";
                return false;
            }
        }
        return true;
    }

    private int getSize() {
        int res = 0;
        for (T champs : listFields) {
            res = Math.max(res, champs.getPosition() + champs.getLength());
        }
        return res;
    }

    public boolean testAll() {
        return testBasic() && testDuplicate();
    }

    public String getMessageError() {
        return messageError;
    }
}
