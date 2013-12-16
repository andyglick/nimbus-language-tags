package com.nimbusds.langtag;


import junit.framework.TestCase;


/**
 * Tests the language tag class.
 */
public class LangTagTest extends TestCase {
	
	
	public void testConstructorSimple()
		throws LangTagException{
	
		LangTag lt = new LangTag("en");
		assertEquals("en", lt.getPrimaryLanguage());
		assertNull(lt.getExtendedLanguageSubtags());
		assertEquals("en", lt.getLanguage());
		assertEquals("en", lt.toString());
	}
	
	
	public void testConstructorSimpleCanonicalFormat()
		throws LangTagException {
	
		LangTag lt = new LangTag("EN");
		assertEquals("en", lt.getPrimaryLanguage());
		assertNull(lt.getExtendedLanguageSubtags());
		assertEquals("en", lt.getLanguage());
		assertEquals("en", lt.toString());
	}
	
	
	public void testConstructorNull() {
	
		try {
			new LangTag(null);
			fail();

		} catch (LangTagException e) {
			// ok
		}
	}
	
	
	public void testConstructorExtended()
		throws LangTagException {
	
		LangTag lt = new LangTag("zh", "cmn");
		assertEquals("zh", lt.getPrimaryLanguage());
		assertNotNull(lt.getExtendedLanguageSubtags());
		assertEquals("cmn", lt.getExtendedLanguageSubtags()[0]);
		assertEquals("zh-cmn", lt.getLanguage());
		assertEquals("zh-cmn", lt.toString());
	}
	
	public void testConstructorExtendedNull() {
	
		try {
			new LangTag(null, new String[]{});
			fail();
			
		} catch (LangTagException e) {
			// ok
		}
	}
	
	
	public void testConstructorExtendedMultiple()
		throws Exception {
	
		LangTag lt = new LangTag("zh", "cmn", "xyz");
		assertEquals("zh", lt.getPrimaryLanguage());
		assertNotNull(lt.getExtendedLanguageSubtags());
		assertEquals(2, lt.getExtendedLanguageSubtags().length);
		assertEquals("cmn", lt.getExtendedLanguageSubtags()[0]);
		assertEquals("xyz", lt.getExtendedLanguageSubtags()[1]);
		assertEquals("zh-cmn-xyz", lt.getLanguage());
		assertEquals("zh-cmn-xyz", lt.toString());
	}
	
	
	public void testConstructorExtendedSubtagOnly()
		throws LangTagException {
	
		LangTag lt = new LangTag(null, "cmn");
		assertNull(lt.getPrimaryLanguage());
		assertNotNull(lt.getExtendedLanguageSubtags());
		assertEquals("cmn", lt.getExtendedLanguageSubtags()[0]);
		assertEquals("cmn", lt.getLanguage());
		assertEquals("cmn", lt.toString());
	}
	
	
	public void testEquality()
		throws LangTagException {
	
		LangTag lt1 = new LangTag("en");
		lt1.setRegion("us");
		
		LangTag lt2 = new LangTag("EN");
		lt2.setRegion("US");
		
		assertTrue(lt1.equals(lt2));
	}
	
	
	public void testScript()
		throws Exception {
	
		LangTag lt = new LangTag("sr");
		lt.setScript("Cyrl");
		
		assertEquals("sr", lt.getPrimaryLanguage());
		assertEquals("sr", lt.getLanguage());
		assertEquals("Cyrl", lt.getScript());
		assertEquals("sr-Cyrl", lt.toString());
	}
	
	
	public void testScriptNull()
		throws LangTagException {
	
		LangTag lt = new LangTag("sr");
		lt.setScript(null);

		assertEquals("sr", lt.getPrimaryLanguage());
		assertEquals("sr", lt.getLanguage());
		assertNull(lt.getScript());
		assertEquals("sr", lt.toString());
	}
	
	
	public void testRegionISO3166()
		throws LangTagException {
	
		LangTag lt = new LangTag("en");
		lt.setRegion("US");
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertEquals("US", lt.getRegion());
		assertEquals("en-US", lt.toString());
	}
	
	
	public void testRegionUNM49()
		throws LangTagException {
	
		LangTag lt = new LangTag("en");
		lt.setRegion("123");
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertEquals("123", lt.getRegion());
		assertEquals("en-123", lt.toString());
	}
	
	
	public void testRegionNull()
		throws LangTagException {
	
		LangTag lt = new LangTag("en");
		lt.setRegion(null);
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertNull(lt.getRegion());
		assertEquals("en", lt.toString());
	}
	
	
	public void testVariants()
		throws LangTagException {
	
		LangTag lt = new LangTag("en");
		lt.setVariants("2012");
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertEquals(1, lt.getVariants().length);
		assertEquals("2012", lt.getVariants()[0]);
		assertEquals("en-2012", lt.toString());
	}
	
	
	public void testVariantsEmpty()
		throws LangTagException {
	
		LangTag lt = new LangTag("en");
		lt.setVariants();
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertNull(lt.getVariants());
		assertEquals("en", lt.toString());
	}
	
	
	public void testExtensions()
		throws LangTagException {
	
		LangTag lt = new LangTag("en");
		lt.setExtensions("a-cal");
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertEquals(1, lt.getExtensions().length);
		assertEquals("a-cal", lt.getExtensions()[0]);
		assertEquals("en-a-cal", lt.toString());
	}
	
	
	public void testExtensionsEmpty()
		throws LangTagException {
	
		LangTag lt = new LangTag("en");
		lt.setExtensions();
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertNull(lt.getExtensions());
		assertEquals("en", lt.toString());
	}
	
	
	public void testPrivateUse()
		throws LangTagException {
	
		LangTag lt = new LangTag("en");
		lt.setPrivateUse("x-private");
		
		assertEquals("en", lt.getPrimaryLanguage());
		assertEquals("en", lt.getLanguage());
		assertEquals("x-private", lt.getPrivateUse());
		assertEquals("en-x-private", lt.toString());
	}
	
	
	public void testParse1()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("de");
	
		assertEquals("de", lt.getPrimaryLanguage());
		assertEquals("de", lt.getLanguage());
		assertEquals("de", lt.toString());
	}
	
	
	public void testParse2()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("zh-Hant");
	
		assertEquals("zh", lt.getPrimaryLanguage());
		assertEquals("zh", lt.getLanguage());
		assertEquals("Hant", lt.getScript());
		assertEquals("zh-Hant", lt.toString());
	}
	
	
	public void testParse3()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("zh-cmn-Hans-CN");
	
		assertEquals("zh", lt.getPrimaryLanguage());
		assertEquals("cmn", lt.getExtendedLanguageSubtags()[0]);
		assertEquals("zh-cmn", lt.getLanguage());
		assertEquals("Hans", lt.getScript());
		assertEquals("CN", lt.getRegion());
		assertEquals("zh-cmn-Hans-CN", lt.toString());
	}
	
	
	public void testParse4()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("zh-yue-HK");
	
		assertEquals("zh", lt.getPrimaryLanguage());
		assertEquals("yue", lt.getExtendedLanguageSubtags()[0]);
		assertEquals("zh-yue", lt.getLanguage());
		assertEquals("HK", lt.getRegion());
		assertEquals("zh-yue-HK", lt.toString());
	}
	
	
	public void testParse5()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("yue-HK");

		assertEquals("yue", lt.getPrimaryLanguage());
		assertNull(lt.getExtendedLanguageSubtags());
		assertEquals("yue", lt.getLanguage());
		assertEquals("HK", lt.getRegion());
		assertEquals("yue-HK", lt.toString());
	}
	
	
	public void testParse6()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("sr-Latn-RS");

		assertEquals("sr", lt.getPrimaryLanguage());
		assertNull(lt.getExtendedLanguageSubtags());
		assertEquals("sr", lt.getLanguage());
		assertEquals("Latn", lt.getScript());
		assertEquals("RS", lt.getRegion());
		assertEquals("sr-Latn-RS", lt.toString());
	}
	
	
	public void testParse7()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("sl-rozaj");

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
	
	
	public void testParse8()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("de-CH-1901");

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
	
	
	public void testParse9()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("hy-Latn-IT-arevela");

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
	
	
	public void testParse10()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("en-US-u-islamcal");

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
	
	
	public void testParse11()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("en-a-myext-b-another");

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
	
	
	public void testParse12()
		throws LangTagException {
	
		LangTag lt = LangTag.parse("zh-CN-a-myext-x-private");

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
	
		try {
			LangTag.parse("invalid-tag");
			fail();
			
		} catch (LangTagException e) {
			// ok
		}
	}
	
	
	public void testParse14()
		throws LangTagException {
		
		assertNull(LangTag.parse(null));
	}
}
