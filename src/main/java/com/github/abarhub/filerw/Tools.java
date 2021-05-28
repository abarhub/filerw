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

public final class Tools {

    private Tools() {
        // constructeur privé
    }

    public static <T extends Field> int getSize(Class<T> clazz) {
        int res = 0;
        List<T> listFields;
        listFields = convClassEnum(clazz);
        for (T champs : listFields) {
            res = Math.max(res, champs.getPosition() + champs.getLength());
        }
        return res;
    }

    public static <T extends Field> int getSize(List<T> listFields) {
        int res = 0;
        for (T field : listFields) {
            res = Math.max(res, field.getPosition() + field.getLength());
        }
        return res;
    }

    public static <T> List<T> convClassEnum(Class<T> clazz) {
        List<T> fieldsList;
        fieldsList = new ArrayList<>();
        Collections.addAll(fieldsList, clazz.getEnumConstants());
        return fieldsList;
    }

}
