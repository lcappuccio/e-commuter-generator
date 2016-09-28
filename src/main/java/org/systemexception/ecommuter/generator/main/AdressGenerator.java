package org.systemexception.ecommuter.generator.main;

import org.systemexception.ecommuter.generator.parser.Parser;
import org.systemexception.ecommuter.generator.parser.ParserImpl;
import org.systemexception.ecommuter.generator.pojo.FileReader;
import org.systemexception.ecommuter.generator.pojo.HttpConnector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author leo
 * @date 23/09/2016 23:26
 */
public class AdressGenerator {

	public static void main(String[] args) throws IOException {

		Parser parser = new ParserImpl();
		FileReader fileReader = new FileReader();
		Random random = new Random(System.currentTimeMillis());

		File outputFile = new File("output.txt");
		FileWriter fileWriter = new FileWriter(outputFile);

		List<String> addresses = fileReader.readFileToLines("milano_addresses.txt");
		List<String> output = new ArrayList<>();

		for (String address: addresses) {
			String streetTypeFromLine = parser.getStreetTypeFromLine(address);
			String streetNameFromLine = parser.getStreetNameFromLine(address);
			String postalCodeFromLine = parser.getPostalCodeFromLine(address);

			String formattedAddress = streetTypeFromLine + ParserImpl.SPACE + streetNameFromLine + ParserImpl.SPACE
					+ random.nextInt(50) + "," + ParserImpl.SPACE + postalCodeFromLine + ", Milano";
			System.out.println(formattedAddress);
			String aFor = HttpConnector.getAddressObjectAsStringFor(formattedAddress);
			System.out.println(aFor);
			output.add(aFor);
			fileWriter.write(aFor + ",");
			fileWriter.write(System.lineSeparator());
		}
	}
}
