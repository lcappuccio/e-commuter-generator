package org.systemexception.ecommuter.generator.parser;

/**
 * @author leo
 * @date 23/09/2016 21:35
 */
public interface Parser {

	/**
	 * Get the street type from this line
	 *
	 * @return
	 */
	String getStreetTypeFromLine(String stringToParse);

	/**
	 * Get the street name from this line
	 *
	 * @return
	 */
	String getStreetNameFromLine(String stringToParse);

	/**
	 * Get the postal code from this line
	 *
	 * @return
	 */
	String getPostalCodeFromLine(String stringToParse);

}
