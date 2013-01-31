package com.nimbusds.langtag;


import junit.framework.TestCase;


/**
 * Tests the language tag class.
 *
 * @author Vladimir Dzhuvinov
 * @version 1.1 (2012-04-06)
 */
public class LangTagTest extends TestCase {
	
	
	public void testConstructorSimple() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("en");

		} catch (LangTagException e) {		
		
			fail(e.getMessage());
		}
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertNull(lt.getExtendedLanguageSubtags());
		assertEquals("en", lt.getLanguage());
		assertEquals("en", lt.toString());
	}
	
	
	public void testConstructorSimpleCanonicalFormat() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("EN");

		} catch (LangTagException e) {		
		
			fail(e.getMessage());
		}
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertNull(lt.getExtendedLanguageSubtags());
		assertEquals("en", lt.getLanguage());
		assertEquals("en", lt.toString());
	}
	
	
	public void testConstructorNull() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag(null);

			fail("Failed to raise exception");

		} catch (LangTagException e) {		
		
			// ok
		}
	}
	
	
	public void testConstructorExtended() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("zh", "cmn");
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertEquals("zh", lt.getPrimaryLanguage());
		assertNotNull(lt.getExtendedLanguageSubtags());
		assertEquals("cmn", lt.getExtendedLanguageSubtags()[0]);
		assertEquals("zh-cmn", lt.getLanguage());
		assertEquals("zh-cmn", lt.toString());
	}
	
	public void testConstructorExtendedNull() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag(null, new String[]{});
			
			fail("Failed to raise exception");
			
		} catch (LangTagException e) {
		
			// ok
		}
	}
	
	
	public void testConstructorExtendedMultiple() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("zh", "cmn", "xyz");
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertEquals("zh", lt.getPrimaryLanguage());
		assertNotNull(lt.getExtendedLanguageSubtags());
		assertEquals(2, lt.getExtendedLanguageSubtags().length);
		assertEquals("cmn", lt.getExtendedLanguageSubtags()[0]);
		assertEquals("xyz", lt.getExtendedLanguageSubtags()[1]);
		assertEquals("zh-cmn-xyz", lt.getLanguage());
		assertEquals("zh-cmn-xyz", lt.toString());
	}
	
	
	public void testConstructorExtendedSubtagOnly() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag(null, "cmn");
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertNull(lt.getPrimaryLanguage());
		assertNotNull(lt.getExtendedLanguageSubtags());
		assertEquals("cmn", lt.getExtendedLanguageSubtags()[0]);
		assertEquals("cmn", lt.getLanguage());
		assertEquals("cmn", lt.toString());
	}
	
	
	public void testEquality() {
	
		LangTag lt1 = null;
		
		try {
			lt1 = new LangTag("en");
			lt1.setRegion("us");
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		
		LangTag lt2 = null;
		
		try {
			lt2 = new LangTag("EN");
			lt2.setRegion("US");
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertTrue(lt1.equals(lt2));
	}
	
	
	public void testScript() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("sr");
			lt.setScript("Cyrl");
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}	
		
		assertEquals("sr", lt.getPrimaryLanguage());
		assertEquals("sr", lt.getLanguage());
		assertEquals("Cyrl", lt.getScript());
		assertEquals("sr-Cyrl", lt.toString());
	}
	
	
	public void testScriptNull() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("sr");
			lt.setScript(null);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}	
		
		assertEquals("sr", lt.getPrimaryLanguage());
		assertEquals("sr", lt.getLanguage());
		assertNull(lt.getScript());
		assertEquals("sr", lt.toString());
	}
	
	
	public void testRegionISO3166() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("en");
			lt.setRegion("US");
		
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertEquals("US", lt.getRegion());
		assertEquals("en-US", lt.toString());
	}
	
	
	public void testRegionUNM49() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("en");
			lt.setRegion("123");
		
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertEquals("123", lt.getRegion());
		assertEquals("en-123", lt.toString());
	}
	
	
	public void testRegionNull() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("en");
			lt.setRegion(null);
		
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertNull(lt.getRegion());
		assertEquals("en", lt.toString());
	}
	
	
	public void testVariants() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("en");
			lt.setVariants(new String[]{"2012"});
		
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertEquals(1, lt.getVariants().length);
		assertEquals("2012", lt.getVariants()[0]);
		assertEquals("en-2012", lt.toString());
	}
	
	
	public void testVariantsEmpty() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("en");
			lt.setVariants(new String[]{});
		
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertNull(lt.getVariants());
		assertEquals("en", lt.toString());
	}
	
	
	public void testExtensions() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("en");
			lt.setExtensions(new String[]{"a-cal"});
		
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertEquals(1, lt.getExtensions().length);
		assertEquals("a-cal", lt.getExtensions()[0]);
		assertEquals("en-a-cal", lt.toString());
	}
	
	
	public void testExtensionsEmpty() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("en");
			lt.setExtensions(new String[]{});
		
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertNull(lt.getExtensions());
		assertEquals("en", lt.toString());
	}
	
	
	public void testPrivateUse() {
	
		LangTag lt = null;
		
		try {
			lt = new LangTag("en");
			lt.setPrivateUse("x-private");
		
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertEquals("x-private", lt.getPrivateUse());
		assertEquals("en-x-private", lt.toString());
	}
	
	
	public void testParse1() {
	
		String s = "de";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
	
		assertEquals("de", lt.getPrimaryLanguage());
		assertEquals("de", lt.getLanguage());
		assertEquals("de", lt.toString());
	}
	
	
	public void testParse2() {
	
		String s = "zh-Hant";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
	
		assertEquals("zh", lt.getPrimaryLanguage());
		assertEquals("zh", lt.getLanguage());
		assertEquals("Hant", lt.getScript());
		assertEquals("zh-Hant", lt.toString());
	}
	
	
	public void testParse3() {
	
		String s = "zh-cmn-Hans-CN";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
	
		assertEquals("zh", lt.getPrimaryLanguage());
		assertEquals("cmn", lt.getExtendedLanguageSubtags()[0]);
		assertEquals("zh-cmn", lt.getLanguage());
		assertEquals("Hans", lt.getScript());
		assertEquals("CN", lt.getRegion());
		assertEquals("zh-cmn-Hans-CN", lt.toString());
	}
	
	
	public void testParse4() {
	
		String s = "zh-yue-HK";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
	
		assertEquals("zh", lt.getPrimaryLanguage());
		assertEquals("yue", lt.getExtendedLanguageSubtags()[0]);
		assertEquals("zh-yue", lt.getLanguage());
		assertEquals("HK", lt.getRegion());
		assertEquals("zh-yue-HK", lt.toString());
	}
	
	
	public void testParse5() {
	
		String s = "yue-HK";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}

		assertEquals("yue", lt.getPrimaryLanguage());
		assertNull(lt.getExtendedLanguageSubtags());
		assertEquals("yue", lt.getLanguage());
		assertEquals("HK", lt.getRegion());
		assertEquals("yue-HK", lt.toString());
	}
	
	
	public void testParse6() {
	
		String s = "sr-Latn-RS";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}

		assertEquals("sr", lt.getPrimaryLanguage());
		assertNull(lt.getExtendedLanguageSubtags());
		assertEquals("sr", lt.getLanguage());
		assertEquals("Latn", lt.getScript());
		assertEquals("RS", lt.getRegion());
		assertEquals("sr-Latn-RS", lt.toString());
	}
	
	
	public void testParse7() {
	
		String s = "sl-rozaj";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}

		assertEquals("sl", lt.getPrimaryLanguage()); 
		assertNull(lt.getExtendedLanguageSubtags()); 
		assertEquals("sl", lt.getLanguage());
		assertNull(lt.getScript());
		assertNull(lt.getRegion());
		
		assertNotNull(lt.getVariants());
		assertEquals(1, lt.getVariants().length); 
		assertEquals("rozaj", lt.getVariants()[0]);
		assertEquals("sl-rozaj", lt.toString());
	}
	
	
	public void testParse8() {
	
		String s = "de-CH-1901";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}

		assertEquals("de", lt.getPrimaryLanguage()); 
		assertNull(lt.getExtendedLanguageSubtags()); 
		assertEquals("de", lt.getLanguage());
		assertNull(lt.getScript());
		assertEquals("CH", lt.getRegion());
		
		assertNotNull(lt.getVariants());
		assertEquals(1, lt.getVariants().length); 
		assertEquals("1901", lt.getVariants()[0]);
		assertEquals("de-CH-1901", lt.toString());
	}
	
	
	public void testParse9() {
	
		String s = "hy-Latn-IT-arevela";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}

		assertEquals("hy", lt.getPrimaryLanguage()); 
		assertNull(lt.getExtendedLanguageSubtags()); 
		assertEquals("hy", lt.getLanguage());
		assertEquals("Latn", lt.getScript());
		assertEquals("IT", lt.getRegion());
		
		assertNotNull(lt.getVariants());
		assertEquals(1, lt.getVariants().length); 
		assertEquals("arevela", lt.getVariants()[0]);
		assertEquals("hy-Latn-IT-arevela", lt.toString());
	}
	
	
	public void testParse10() {
	
		String s = "en-US-u-islamcal";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}

		assertEquals("en", lt.getPrimaryLanguage()); 
		assertNull(lt.getExtendedLanguageSubtags()); 
		assertEquals("en", lt.getLanguage());
		assertNull(lt.getScript());
		assertEquals("US", lt.getRegion());
		
		assertNotNull(lt.getExtensions());
		assertEquals(1, lt.getExtensions().length); 
		assertEquals("u-islamcal", lt.getExtensions()[0]);
		assertEquals("en-US-u-islamcal", lt.toString());
	}
	
	
	public void testParse11() {
	
		String s = "en-a-myext-b-another";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}

		assertEquals("en", lt.getPrimaryLanguage()); 
		assertNull(lt.getExtendedLanguageSubtags()); 
		assertEquals("en", lt.getLanguage());
		assertNull(lt.getScript());
		assertNull(lt.getRegion());
		
		assertNotNull(lt.getExtensions());
		assertEquals(2, lt.getExtensions().length); 
		assertEquals("a-myext", lt.getExtensions()[0]);
		assertEquals("b-another", lt.getExtensions()[1]);
		assertEquals("en-a-myext-b-another", lt.toString());
	}
	
	
	public void testParse12() {
	
		String s = "zh-CN-a-myext-x-private";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}

		assertEquals("zh", lt.getPrimaryLanguage()); 
		assertNull(lt.getExtendedLanguageSubtags()); 
		assertEquals("zh", lt.getLanguage());
		assertNull(lt.getScript());
		assertEquals("CN", lt.getRegion());
		
		assertNotNull(lt.getExtensions());
		assertEquals(1, lt.getExtensions().length); 
		assertEquals("a-myext", lt.getExtensions()[0]);
		
		assertEquals("x-private", lt.getPrivateUse());
	}
	
	
	public void testParse13() {
	
		String s = "invalid-tag";
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(s);
			
			fail("Failed to raise exception");
			
		} catch (LangTagException e) {
		
			// ok
		}
	}
	
	
	public void testParse14() {
		
		LangTag lt = null;
		
		try {
			lt = LangTag.parse(null);
			
		} catch (LangTagException e) {
		
			fail(e.getMessage());
		}
		
		assertNull(lt);
	}

}
