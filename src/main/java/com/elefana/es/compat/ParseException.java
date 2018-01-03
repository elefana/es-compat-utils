/*******************************************************************************
 * Copyright 2018 Viridian Software Limited
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

public class ParseException extends RuntimeException {
	private static final long serialVersionUID = -6424158474382204584L;

	public ParseException(String message, String ...args) {
		super(formatArgs(message, args));
	}
	
	public ParseException(String message, char unit, String ...args) {
		super(formatArgs(message, args));
	}
	
	public ParseException(String message, Exception e, String ...args) {
		super(formatArgs(message, args));
		e.printStackTrace();
	}
	
	public static String formatArgs(final String originalMessage, String ... args) {
		String result = originalMessage;
		for(int i = 0; i < args.length; i++) {
			if(!result.contains("{}")) {
				break;
			}
			result = result.replaceFirst("\\{\\}", args[i]);
		}
		return result;
	}
}
