package org.systemexception.ecommuter.generator.main;

import org.systemexception.ecommuter.generator.pojo.AddressConverter;
import org.systemexception.ecommuter.generator.pojo.HttpConnector;
import org.systemexception.ecommuter.model.Address;
import org.systemexception.ecommuter.model.Person;
import org.systemexception.ecommuter.pojo.PersonJsonParser;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author leo
 * @date 24/09/2016 10:47
 */
public class ConvertObjectAndPost implements Callable<String> {

	private final List<String> addresses;
	private final List<String> names;
	private final List<String> lastnames;

	public ConvertObjectAndPost(List<String> addresses, List<String> names, List<String> lastnames) {
		this.addresses = addresses;
		this.names = names;
		this.lastnames = lastnames;
	}

	@Override
	public String call() throws Exception {
		for (int i = 0; i < 10; i++) {
			Person person = new Person();
			person.setId(UUID.randomUUID().toString());
			person.setName(getRandomNameFrom(names));
			person.setLastname(getRandomNameFrom(lastnames));
			person.setHomeAddress(getRandomAddressFrom(addresses));
			person.setWorkAddress(getRandomAddressFrom(addresses));
			String responseCode = HttpConnector.postPerson(PersonJsonParser.fromPerson(person).toString());
			System.out.println(i + ":\t" + person.getId() + ", " + person.getLastname() + ", " +
					responseCode);
		}
		return "OK";
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
