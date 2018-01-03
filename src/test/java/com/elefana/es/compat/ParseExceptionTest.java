/*******************************************************************************
 * Copyright 2017 Viridian Software Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.elefana.es.compat;

import org.junit.Test;

import junit.framework.Assert;

public class ParseExceptionTest {

	@Test
	public void testFormatArgs() {
		Assert.assertEquals("0", ParseException.formatArgs("{}", "0"));
		Assert.assertEquals("Exception with 1 and 2", ParseException.formatArgs("Exception with {} and {}", "1", "2"));
	}
}
