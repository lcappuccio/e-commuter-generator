package org.systemexception.ecommuter.generator.pojo;

import org.apache.commons.io.IOUtils;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author leo
 * @date 23/09/2016 23:52
 */
public class HttpConnector implements Runnable {

	private final String person;

	public HttpConnector(String person) {

		this.person = person;
	}

	public static String getAddressObjectAsStringFor(final String formattedAddress) {

		try {
			URL url = new URL("http://localhost:8080/ecommuter/address/togeo");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("PUT");
			urlConnection.setDoOutput(true);
			urlConnection.setConnectTimeout(1000);
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
			osw.write("{\n" +
					"  \"streetNumber\": \"\",\n" +
					"  \"route\": \"\",\n" +
					"  \"locality\": \"\",\n" +
					"  \"administrativeAreaLevel2\": \"\",\n" +
					"  \"administrativeAreaLevel1\": \"\",\n" +
					"  \"country\": \"\",\n" +
					"  \"postalCode\": \"\",\n" +
					"  \"formattedAddress\": \"" + formattedAddress + "\",\n" +
					"  \"latitude\": 0,\n" +
					"  \"longitude\": 0\n" +
					"}");
			osw.flush();
			osw.close();
			return String.valueOf(IOUtils.toString(urlConnection.getInputStream(), Charset.defaultCharset()));
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public void run() {

		try {
			URL url = new URL("http://localhost:8080/ecommuter/person/add");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoOutput(true);
			urlConnection.setConnectTimeout(1000);
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
			osw.write(person);
			osw.flush();
			osw.close();
			urlConnection.getResponseCode();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
