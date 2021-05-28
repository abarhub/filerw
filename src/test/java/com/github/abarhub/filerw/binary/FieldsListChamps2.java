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

public enum FieldsListChamps2 implements Field {
    Code1(0, 4), Code2(4, 6), Code3(10, 7);

    private FieldsListChamps2(int position, int length) {
        this.position = position;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public int getPosition() {
        return position;
    }

    private int position;
    private int length;

}
