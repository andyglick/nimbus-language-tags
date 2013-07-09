package com.nimbusds.langtag;


import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import net.minidev.json.JSONObject;


/**
 * Tests JSON serialisation.
 * 
 * @author Vladimir Dzhuvinov
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

