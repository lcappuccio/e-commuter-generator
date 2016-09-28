package org.systemexception.ecommuter.generator.pojo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author leo
 * @date 23/09/2016 21:16
 */
public class FileReader {

	public List<String> readFileToLines(final String fileName) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return FileUtils.readLines(file, "UTF-8");
	}
}
