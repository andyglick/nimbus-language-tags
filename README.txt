Nimbus-LangTag

README

Java implementation of "Tags for Identifying Languages", RFC-5646.

Supports normal language tags. Special private language tags beginning with "x" 
and grandfathered tags beginning with "i" are not supported.

See com.nimbusds.langtag.LangTag class for details.

This package has no dependencies.


Change log:

version 1.0 (2012-05-26)
	* First official release.
	
version 1.1 (2013-01-31)
	* Adds ReadOnlyLangTag interface.
	* Fixes LangTag.equals(Object) handling of null arguments.
	* Adds LangTagUtil class.

version 1.1.1 (2013-04-05)
	* Switches Ant build script to Maven.
	* Publishes library to Maven Central.
