package org.oapen.irusuk.dataingestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.oapen.irusuk.entities.PerformanceInstance;
import org.oapen.irusuk.entities.ReportHeader;
import org.oapen.irusuk.entities.ReportItem;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * These tests test the parsing of downloaded IRUS-UK API responses.
 * 
 * @author acdhirr
 *
 */

@SpringBootTest
public class IRReportParserTests {
	
	@Test
	void testParseItems() {

		String path = "src/test/resources/report-with-header-and-items.json";
		File file = new File(path);
		IRReportParser parser = new IRReportParser(file);
		
		List<ReportItem> items = new ArrayList<>();
		parser.parseItems(items::add);
		
		assertFalse(items.isEmpty());
		List<PerformanceInstance> pis = items.get(0).getPerformanceInstances();
		assertFalse(pis.isEmpty());
		assertEquals(pis.get(0).getCountry().getName(),"Canada");
	}
	
	@Test
	void testParseHeader() {
		
		String path = "src/test/resources/report-with-header-and-items.json";
		File file = new File(path);
		IRReportParser parser = new IRReportParser(file);

		List<ReportHeader> header = new ArrayList<>();
		parser.parseHeader(header::add);
		
		assertFalse(header.isEmpty());
	}
	
	
	@Test
	void testParseEmptyItemsAndHeaderWithExceptions() {
		
		String path = "src/test/resources/report-with-header-exception-and-no-items.json";
		File file = new File(path);
		IRReportParser parser = new IRReportParser(file);

		List<ReportHeader> header = new ArrayList<>();
		parser.parseHeader(header::add);
		
		assertFalse(header.isEmpty());
		assertFalse(header.get(0).getExceptions().isEmpty());
	}
	

	
	@Test
	void testParseItemsWithFunders() {
		
		String path = "src/test/resources/report-with-items-with-funders.json";
		File file = new File(path);
		IRReportParser parser = new IRReportParser(file);

		List<ReportItem> items = new ArrayList<>();
		parser.parseItems(items::add);

		assertFalse(items.isEmpty());
		assertFalse(items.get(0).getFunders().isEmpty());
	}
	
	
	@Test
	void testParseItemsWithEventWithoutCountry() {

		String path = "src/test/resources/report-with-items-unknown-country.json";
		File file = new File(path);
		IRReportParser parser = new IRReportParser(file);
		
		List<ReportItem> items = new ArrayList<>();
		parser.parseItems(items::add);
		
		assertFalse(items.isEmpty());
	}
	
	
	
}
