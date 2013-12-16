package com.nimbusds.langtag;


import java.util.*;


/**
 * Language tag utilities.
 */
public final class LangTagUtils {


	/**
	 * Strips the language tag, if any is found, from the specified string.
	 * This method is {@code null} safe.
	 *
	 * <p>Example:
	 *
	 * <pre>
	 * "name#bg-BG" => "name"
	 * "name"       => "name"
	 * </pre>
	 *
	 * @param s The string. May contain a language tag. May be
	 *          {@code null}.
	 *
	 * @return The string with no language tag.
	 */
	public static String strip(final String s) {

		if (s == null)
			return null;

		final int pos = s.indexOf('#');

		if (pos < 0)
			return s;

		return s.substring(0, pos);
	}


	/**
	 * Strips the language tags, if any are found, from the specified
	 * string set. This method is {@code null} safe.
	 *
	 * <p>Example:
	 *
	 * <pre>
	 * "name#bg-BG" => "name"
	 * "name"       => "name"
	 * </pre>
	 *
	 * @param set The string set. May contain strings with language tags.
	 *            May be {@code null}.
	 *
	 * @return The string set with no language tags.
	 */
	public static Set<String> strip(final Set<String> set) {

		if (set == null)
			return null;

		Set<String> out = new HashSet<String>();

		for (String s: set)
			out.add(strip(s));

		return out;
	}


	/**
	 * Strips the language tags, if any are found, from the specified
	 * string list. This method is {@code null} safe.
	 *
	 * <p>Example:
	 *
	 * <pre>
	 * "name#bg-BG" => "name"
	 * "name"       => "name"
	 * </pre>
	 *
	 * @param list The string list. May contain strings with language tags.
	 *             May be {@code null}.
	 *
	 * @return The string list with no language tags.
	 */
	public static List<String> strip(final List<String> list) {

		if (list == null)
			return null;

		List<String> out = new ArrayList<String>(list.size());

		for (String s: list)
			out.add(strip(s));

		return out;
	}


	/**
	 * Extracts the language tag, if any is found, from the specified
	 * string.
	 *
	 * <p>Example:
	 *
	 * <pre>
	 * "name#bg-BG" => "bg-BG"
	 * "name#"      => null
	 * "name"       => null
	 * </pre>
	 *
	 * @param s The string. May contain a language tag. May be
	 *          {@code null}.
	 *
	 * @return The extracted language tag, {@code null} if not found.
	 *
	 * @throws LangTagException If the language tag is invalid.
	 */
	public static LangTag extract(final String s)
		throws LangTagException {

		if (s == null)
			return null;

		final int pos = s.indexOf('#');

		if (pos < 0 || s.length() < pos + 1)
			return null;

		return LangTag.parse(s.substring(pos + 1));
	}


	/**
	 * Finds all language-tagged entries with the specified base name.
	 * Entries with invalid language tags will be skipped.
	 *
	 * <p>Example:
	 *
	 * <p>Map to search for base name "month":
	 *
	 * <pre>
	 * "month"    => "January"
	 * "month#de" => "Januar"
	 * "month#fr" => "janvier"
	 * "month#pt" => "janeiro"
	 * </pre>
	 *
	 * <p>Result:
	 *
	 * <pre>
	 * null => "January"
	 * "de" => "Januar"
	 * "fr" => "janvier"
	 * "pt" => "janeiro"
	 * </pre>
	 *
	 * @param baseName The base name to look for (without a language tag)
	 *                 in the map keys. Must not be {@code null}.
	 * @param map      The map to search. Must not be {@code null}.
	 *
	 * @return A map of all language-tagged entries with the specified
	 *         base name. A {@code null} keyed entry will indicate no
	 *         language tag (base name only).
	 */
	public static <T> Map<LangTag,T> find(final String baseName, final Map<String,T> map) {

		Map<LangTag,T> result = new HashMap<LangTag,T>();

		// Walk through each map entry, checking for entry keys that
		// start with "baseName"
		for (Map.Entry<String,T> entry: map.entrySet()) {

			T value;

			try {
				value = entry.getValue();

			} catch (ClassCastException e) {

				continue; // skip
			}
			
			if (entry.getKey().equals(baseName)) {

				// Claim name matches, no tag	
				result.put(null, value);
			}
			else if (entry.getKey().startsWith(baseName + '#')) {

				// Claim name matches, has tag
				String[] parts = entry.getKey().split("#", 2);

				LangTag langTag = null;

				if (parts.length == 2) {
					
					try {
						langTag = LangTag.parse(parts[1]);
						
					} catch (LangTagException e) {

						// ignore
					}
				}

				result.put(langTag, value);
			}
		}

		return result;
	}


	/**
	 * Returns a string list representation of the specified language tags
	 * collection.
	 *
	 * @param langTags The language tags list. May be {@code null}.
	 *
	 * @return The string list, or {@code null} if the original list is
	 *         {@code null}.
	 */
	public static List<String> toStringList(final Collection<LangTag> langTags) {

		if (langTags == null)
			return null;

		List<String> out = new ArrayList<String>(langTags.size());

		for (LangTag lt: langTags) {
			out.add(lt.toString());
		}

		return out;
	}


	/**
	 * Returns a string array representation of the specified language tags
	 * collection.
	 *
	 * @param langTags The language tags list. May be {@code null}.
	 *
	 * @return The string list, or {@code null} if the original list is
	 *         {@code null}.
	 */
	public static String[] toStringArray(final Collection<LangTag> langTags) {

		if (langTags == null)
			return null;

		String[] out = new String[langTags.size()];

		int i=0;

		for (LangTag lt: langTags) {
			out[i++] = lt.toString();
		}

		return out;
	}


	/**
	 * Prevents public instantiation.
	 */
	private LangTagUtils() { }
}