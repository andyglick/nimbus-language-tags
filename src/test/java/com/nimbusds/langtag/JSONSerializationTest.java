/*
 * lang-tag
 *
 * Copyright 2012-2016, Connect2id Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.nimbusds.langtag;


import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import net.minidev.json.JSONObject;


/**
 * Tests JSON serialisation.
 */
public class JSONSerializationTest extends TestCase {
	
	
	public void testJSONArraySerialization()
		throws Exception {
	
		List<LangTag> langs = new ArrayList<LangTag>();
		
		langs.add(LangTag.parse("en-US"));
		langs.add(LangTag.parse("en-GB"));
		langs.add(LangTag.parse("de-DE"));
		langs.add(LangTag.parse("fr-FR"));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("languages", langs);
		
		jsonObject.put("defaultLanguage", LangTag.parse("en-US"));
		
		System.out.println(jsonObject);
	}
}

