package org.oapen.irusuk.harvester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.YearMonth;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.oapen.irusuk.entities.HeaderException;
import org.oapen.irusuk.entities.IRReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;


/**
 * These tests test the IRUS-UK API response 
 * by making a real time request over http with Irus' server.
 * 
 * @author acdhirr
 *
 */

@SpringBootTest
public class IRUSUKApiTests {
	
	@Autowired
	private String irusUkUrl;
	
	@Test 
	void testCountryCodesAvailable() {

		Set<String> countries = Locale.getISOCountries(Locale.IsoCountryCode.PART1_ALPHA2);
		assertTrue(countries.contains("AD"));
		assertTrue(countries.contains("NL"));
		assertTrue(countries.contains("ZW"));
	}

	@Test
	void testQueryStringIsCorrect() {
		
		String qs = new QueryString
			.Builder(YearMonth.of(2020, 4),YearMonth.of(2020, 4))
			.setFunder("not me")
			.setFunder("me neither")
			.setFunder("abacadabra")
			.setPublisher("Walt Disney")
			.build().toString();

		String expected = 
			"begin_date=2020-04&end_date=2020-04&funder=abacadabra&publisher=Walt Disney";
		
		assertEquals(qs, expected);
	}
	
	@Test
	void testReportHasHeaderAndItems () {
		
		String url = irusUkUrl 
				+ "&begin_date=2020-04&end_date=2020-04"
				+ "&attributes_to_show=Country|Client_IP"
				+ "&publisher=bf3aad86-19af-41e9-9504-d166b1caff10"
				;
		
		RestTemplate tpl = new RestTemplate();
		IRReport report = tpl.getForObject(url, IRReport.class);
		assertNotNull(report);
		assertNotNull(report.getHeader());
		assertNotNull(report.getItems());
	}
	
	@Test
	void testOutOfRangeReportShowsExceptions () {
		
		String url = irusUkUrl 
				+ "&begin_date=1900-01&end_date=1900-12"
				+ "&attributes_to_show=Country|Client_IP"
				+ "&publisher=bf3aad86-19af-41e9-9504-d166b1caff10"
				;
		
		RestTemplate tpl = new RestTemplate();
		IRReport report = tpl.getForObject(url, IRReport.class);
		List<HeaderException> exceptions = report.getHeader().getExceptions();
		assertTrue(exceptions.size() >= 1);
	}
	
}
