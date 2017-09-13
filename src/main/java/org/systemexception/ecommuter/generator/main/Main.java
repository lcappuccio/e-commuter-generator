package org.systemexception.ecommuter.generator.main;

import org.systemexception.ecommuter.generator.pojo.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	private static final List<ConvertObjectAndPost> TASK_LIST = new ArrayList<>();
	private static final int THREADS = 16;

	public static void main(String[] args) throws IOException, InterruptedException {

		long timeStart = System.currentTimeMillis();

		FileReader fileReader = new FileReader();

		List<String> addresses = fileReader.readFileToLines("milano_ecommuter_addresses.txt");
		List<String> italiaNames = listCapitalization(fileReader.readFileToLines("italia_nomi.txt"));
		List<String> italiaSurnames = listCapitalization(fileReader.readFileToLines("italia_cognomi.txt"));

		ExecutorService executor = Executors.newFixedThreadPool(THREADS);
		for (int i = 0; i < THREADS; i++) {
			addTaskToFutureTaskList(addresses, italiaNames, italiaSurnames);
		}

		executor.invokeAll(TASK_LIST);
		executor.shutdown();

		long timeStop = System.currentTimeMillis();

		System.out.println("Time elapsed: " + (timeStop - timeStart)/1000 + " s");

	}

	private static List<String> listCapitalization(List<String> names) {
		List<String> capitalizedNames = new ArrayList<>();
		for (String name : names) {
			capitalizedNames.add(name.substring(0, 1).toUpperCase() + name.substring(1));
		}
		return capitalizedNames;
	}

	private static void addTaskToFutureTaskList(List<String> addresses, List<String> italiaNames,
	                                            List<String> italiaSurnames) {

		ConvertObjectAndPost convertObjectAndPost = new ConvertObjectAndPost(addresses, italiaNames, italiaSurnames);
		TASK_LIST.add(convertObjectAndPost);
	}
}
