package org.systemexception.ecommuter.generator.test;

import org.junit.Test;
import org.systemexception.ecommuter.generator.parser.Parser;
import org.systemexception.ecommuter.generator.parser.ParserImpl;

import static org.junit.Assert.assertEquals;

/**
 * @author leo
 * @date 23/09/2016 21:43
 */
public class ParserTest {

	private Parser sut = new ParserImpl();
	private final static String TEST_STREET_POSTAL_CODE = "20100", TEST_STREET_NAME = "SomeStreetName",
	TEST_STREET_TYPE = "SomeStreetType";
	private final static String TEST_STREET_LINE = TEST_STREET_POSTAL_CODE + ParserImpl.SPACE + TEST_STREET_NAME
			+ ParserImpl.SPACE + ParserImpl.STREET_INITIAL_DELIMITER + TEST_STREET_TYPE
			+ ParserImpl.STREET_FINAL_DELIMITER;

	@Test
	public void should_return_string_between_parentheses() {

		String streetTypeFromLine = sut.getStreetTypeFromLine(TEST_STREET_LINE);

		assertEquals(TEST_STREET_TYPE, streetTypeFromLine);
	}

	@Test
	public void should_return_string_street_name() {

		String streetNameFromLine = sut.getStreetNameFromLine(TEST_STREET_LINE);

		assertEquals(TEST_STREET_NAME, streetNameFromLine);

	}

	@Test
	public void should_return_posta_code() {

		String postalCodeFromLine = sut.getPostalCodeFromLine(TEST_STREET_LINE);

		assertEquals(TEST_STREET_POSTAL_CODE, postalCodeFromLine);
	}

}