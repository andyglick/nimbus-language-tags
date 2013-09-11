package com.nimbusds.langtag;


import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;


/**
 * Tests the language tag utility class.
 *
 * @author Vladimir Dzhuvinov
 */
public class LangTagUtilTest extends TestCase {


	public void testStripWithLangTag() {

		assertEquals("name", LangTagUtils.strip("name#en-US"));

		assertEquals("profile", LangTagUtils.strip("profile#"));
	}


	public void testStripWithNoLangTag() {

		assertEquals("name", LangTagUtils.strip("name"));

		assertEquals("", LangTagUtils.strip(""));
	}


	public void testStripWithNullArg() {

		assertNull(LangTagUtils.strip(null));
	}


	public void testExtractWithNullArg()
		throws LangTagException {

		assertNull(LangTagUtils.extract(null));
	}


	public void testExtractWithNoLangTag()
		throws LangTagException {

		assertNull(LangTagUtils.extract("name"));
	}


	public void testExtractWithEmptyLangTag()
		throws LangTagException {

		assertNull(LangTagUtils.extract("name#"));
	}


	public void testExtractValid()
		throws LangTagException {

		assertEquals("bg-BG", LangTagUtils.extract("name#bg-BG").toString());
	}


	public void testExtractInvalid() {

		try {
			LangTagUtils.extract("name#nosuchlangtag");

			fail("Failed to raise exception");

		} catch (LangTagException e) {

			// ok
		}
	}
	
	
	public void testFind()
		throws Exception {

		Map<String,String> map = new HashMap<String,String>();

		map.put("month", "January");
		map.put("month#de", "Januar");
		map.put("month#fr", "janvier");
		map.put("month#pt", "janeiro");
		map.put("other key", "other value");

		Map<LangTag,String> result = LangTagUtils.find("month", map);

		assertEquals("January", result.get(null));
		assertEquals("Januar", result.get(new LangTag("de")));
		assertEquals("janvier", result.get(new LangTag("fr")));
		assertEquals("janeiro", result.get(new LangTag("pt")));

		assertEquals(4, result.size());
	}


	public void testFindNone() {

		Map<String,String> map = new HashMap<String,String>();

		Map<LangTag,String> result = LangTagUtils.find("month", map);

		assertTrue(result.isEmpty());
	}


	public void testFindNoneMatching() {

		Map<String,String> map = new HashMap<String,String>();

		map.put("month", "January");
		map.put("month#de", "Januar");
		map.put("month#fr", "janvier");
		map.put("month#pt", "janeiro");
		map.put("other key", "other value");

		Map<LangTag,String> result = LangTagUtils.find("day", map);

		assertTrue(result.isEmpty());
	}
}
