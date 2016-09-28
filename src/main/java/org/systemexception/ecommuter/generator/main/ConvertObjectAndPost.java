package org.systemexception.ecommuter.generator.main;

import org.systemexception.ecommuter.generator.pojo.AddressConverter;
import org.systemexception.ecommuter.generator.pojo.FileReader;
import org.systemexception.ecommuter.generator.pojo.HttpConnector;
import org.systemexception.ecommuter.model.Address;
import org.systemexception.ecommuter.model.Person;
import org.systemexception.ecommuter.pojo.PersonJsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author leo
 * @date 24/09/2016 10:47
 */
public class ConvertObjectAndPost {

	public static void main(String[] args) throws IOException {

		FileReader fileReader = new FileReader();

		List<String> addresses = fileReader.readFileToLines("milano_ecommuter_addresses.txt");
		List<String> italiaNames = listCapitalization(fileReader.readFileToLines("italia_nomi.txt"));
		List<String> italiaSurnames = listCapitalization(fileReader.readFileToLines("italia_cognomi.txt"));

		for (int i = 0; i < 10000; i++) {
			Person person = new Person();
			person.setId(UUID.randomUUID().toString());
			person.setName(getRandomNameFrom(italiaNames));
			person.setSurname(getRandomNameFrom(italiaSurnames));
			person.setHomeAddress(getRandomAddressFrom(addresses));
			person.setWorkAddress(getRandomAddressFrom(addresses));
			String responseCode = HttpConnector.postPerson(PersonJsonParser.fromPerson(person).toString());
			System.out.println(person.getId() + ", " + responseCode);
		}

	}

	private static List<String> listCapitalization(List<String> names) {
		List<String> capitalizedNames = new ArrayList<>();
		for (String name: names) {
			capitalizedNames.add(name.substring(0, 1).toUpperCase() + name.substring(1));
		}
		return capitalizedNames;
	}

	private static Address getRandomAddressFrom(List<String> stringList) {
		Random random = new Random();
		int selection = random.nextInt(stringList.size());
		String addressString = stringList.get(selection);
		return AddressConverter.fromJson(addressString);
	}

	private static String getRandomNameFrom(List<String> stringList) {
		Random random = new Random();
		int selection = random.nextInt(stringList.size());
		return stringList.get(selection);
	}
}
