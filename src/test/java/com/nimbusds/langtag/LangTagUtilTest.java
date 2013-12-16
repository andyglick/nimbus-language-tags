package com.nimbusds.langtag;


import java.util.*;

import junit.framework.TestCase;


/**
 * Tests the language tag utility class.
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


	public void testStripSet() {

		Set<String> set = new HashSet<String>();
		set.add("name");
		set.add("name#en-US");

		Set<String> out = LangTagUtils.strip(set);

		assertTrue(out.contains("name"));
		assertEquals(1, out.size());
	}


	public void testStripList() {

		List<String> list = new ArrayList<String>();
		list.add("name");
		list.add("name#en-US");

		List<String> out = LangTagUtils.strip(list);

		assertEquals("name", out.get(0));
		assertEquals("name", out.get(1));
		assertEquals(2, out.size());
	}


	public void testStripWithNullArg() {

		assertNull(LangTagUtils.strip((String)null));
		assertNull(LangTagUtils.strip((Set)null));
		assertNull(LangTagUtils.strip((List)null));
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
