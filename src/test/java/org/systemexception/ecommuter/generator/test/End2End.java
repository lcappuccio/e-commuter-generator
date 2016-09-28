package org.systemexception.ecommuter.generator.test;

import org.junit.Before;
import org.junit.Test;
import org.systemexception.ecommuter.generator.parser.Parser;
import org.systemexception.ecommuter.generator.parser.ParserImpl;
import org.systemexception.ecommuter.generator.pojo.FileReader;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 23/09/2016 23:16
 */
public class End2End {

	private final FileReader fileReader = new FileReader();
	private final Parser parser = new ParserImpl();
	private List<String> addressList;

	@Before
	public void setUp() throws IOException {
		addressList = fileReader.readFileToLines("test_addresses.txt");
		assertTrue(addressList.size() == 3);
	}

	@Test
	public void test_first_line() {
		String address = addressList.get(0);
		String postalCodeFromLine = parser.getPostalCodeFromLine(address);
		String streetNameFromLine = parser.getStreetNameFromLine(address);
		String streetTypeFromLine = parser.getStreetTypeFromLine(address);

		assertEquals("20152", postalCodeFromLine);
		assertEquals("KULISCIOFF A.", streetNameFromLine);
		assertEquals("Via", streetTypeFromLine);
	}

	@Test
	public void test_second_line() {
		String address = addressList.get(1);
		String postalCodeFromLine = parser.getPostalCodeFromLine(address);
		String streetNameFromLine = parser.getStreetNameFromLine(address);
		String streetTypeFromLine = parser.getStreetTypeFromLine(address);

		assertEquals("20146", postalCodeFromLine);
		assertEquals("VESPRI SICILIANI", streetNameFromLine);
		assertEquals("Via", streetTypeFromLine);
	}

	@Test
	public void test_third_line() {
		String address = addressList.get(2);
		String postalCodeFromLine = parser.getPostalCodeFromLine(address);
		String streetNameFromLine = parser.getStreetNameFromLine(address);
		String streetTypeFromLine = parser.getStreetTypeFromLine(address);

		assertEquals("20146", postalCodeFromLine);
		assertEquals("NAPOLI", streetNameFromLine);
		assertEquals("Piazza", streetTypeFromLine);
	}
}
