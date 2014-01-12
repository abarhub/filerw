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

import java.io.PrintStream;
import java.util.List;

public abstract class LineContent<T extends Field> {

	protected final List<T> fieldsList;

	public LineContent(List<T> fieldsList) {
		if (fieldsList == null || fieldsList.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.fieldsList = fieldsList;
	}

	public LineContent(Class<T> clazz) {
		if (clazz == null || !clazz.isEnum()) {
			throw new IllegalArgumentException();
		}
		this.fieldsList = Tools.convClassEnum(clazz);
	}

	public List<T> getFieldsList() {
		return fieldsList;
	}

	public abstract void show(PrintStream out);

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fieldsList == null) ? 0 : fieldsList.hashCode());
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
		if (!(obj instanceof LineContent)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		LineContent<T> other = (LineContent<T>) obj;
		if (fieldsList == null) {
			if (other.fieldsList != null) {
				return false;
			}
		} else if (!fieldsList.equals(other.fieldsList)) {
			return false;
		}
		return true;
	}

}
