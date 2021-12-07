package org.oapen.irusuk.dataingestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test the scanning of a directory for files named "abcd-xy.json"
 * between two yearMonths.
 * <br/<br/
 * For the tests to succeed, two reports must be available in directory
 * "src/test/resources/directory_scanner_tests":<br/
 * 2020-04.json<br/
 * 2020-05.json<br/
 * 
 * @author acdhirr
 *
 */
@SpringBootTest
public class DirectoryScannerTests {
	
	private final String reportsPath = "src/test/resources/downloaded_reports";
	private File file = new File(reportsPath);
	private Path p = file.toPath();
	
	@Test
	void testFindTwoWithinRange() {

		YearMonth first = YearMonth.of(2020, 4); 
		YearMonth last = YearMonth.of(2020, 5);
		DirectoryScanner ds = new DirectoryScanner(p,first, last);
		List<File> files = ds.collect();
		assertEquals(files.size(),2);
	}

	void testFindOnlyOne() {

		YearMonth first = YearMonth.of(2020, 5); 
		YearMonth last = YearMonth.of(2020, 6);
		DirectoryScanner ds = new DirectoryScanner(p,first, last);
		List<File> files = ds.collect();
		assertEquals(files.size(),1);
	}
	
	void testFindNone() {

		YearMonth first = YearMonth.of(1900, 1); 
		YearMonth last = YearMonth.of(1900, 12);
		DirectoryScanner ds = new DirectoryScanner(p,first, last);
		List<File> files = ds.collect();
		assertTrue(files.isEmpty());
	}
	
	void testFilesAreOrderedByName() {

		YearMonth first = YearMonth.of(2020, 4); 
		YearMonth last = YearMonth.of(2020, 7);
		DirectoryScanner ds = new DirectoryScanner(p,first, last);
		List<File> files = ds.collect();
		assertTrue(files.get(files.size()-1).getName().equals("2020-07"));
	}
	
	
}
