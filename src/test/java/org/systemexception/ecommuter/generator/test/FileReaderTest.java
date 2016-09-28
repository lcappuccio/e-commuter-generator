package org.systemexception.ecommuter.generator.test;

import org.junit.Test;
import org.systemexception.ecommuter.generator.pojo.FileReader;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author leo
 * @date 23/09/2016 21:18
 */
public class FileReaderTest {

	private FileReader sut = new FileReader();

	@Test
	public void should_read_sample_file() throws IOException {

		List<String> strings = sut.readFileToLines("test.txt");

		assertEquals(3, strings.size());
		assertEquals("ABC", strings.get(0));
	}

}