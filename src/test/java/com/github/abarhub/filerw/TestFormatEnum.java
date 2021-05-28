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

import com.github.abarhub.filerw.text.FieldsListChamps1;

import com.github.abarhub.filerw.binary.FieldsListChamps2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestFormatEnum {

	@Test
	public void test1() {
		TestTools<FieldsListChamps1> test;
		test = new TestTools<>(FieldsListChamps1.class);
		assertTrue(test.testAll());
	}

	@Test
	public void test2() {
		TestTools<FieldsListChamps2> test;
		test = new TestTools<>(FieldsListChamps2.class);
		assertTrue(test.testAll());
	}

}
