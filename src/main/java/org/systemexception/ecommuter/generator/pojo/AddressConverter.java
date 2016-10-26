package org.systemexception.ecommuter.generator.pojo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.systemexception.ecommuter.model.Address;
import org.systemexception.ecommuter.model.Territory;

/**
 * @author leo
 * @date 24/09/2016 10:45
 */
public class AddressConverter {

	public static Address fromJson(final String jsonAddress) {

		JsonParser jsonParser = new JsonParser();
		JsonObject asJsonObject = jsonParser.parse(jsonAddress).getAsJsonObject();
		return addressFromJson(asJsonObject);

	}

	private static Address addressFromJson(final JsonObject jsonObject) {
		Gson gson = new Gson();
		Address address = new Address();
		Territory territory = new Territory();
		if (jsonObject.isJsonObject()) {
			address = gson.fromJson(jsonObject, Address.class);
			territory.setCountry(jsonObject.get("country").getAsString());
			territory.setPostalCode(jsonObject.get("postalCode").getAsString());
			territory.setPlaceName(jsonObject.get("locality").getAsString());
			address.setTerritory(territory);
		}

		return address;
	}
}
