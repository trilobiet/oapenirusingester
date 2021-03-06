package org.oapen.irusuk.harvester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.YearMonth;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * These tests test the IRUS-UK API response 
 * by making a real time request over http with Irus' server.
 * 
 * @author acdhirr
 *
 */

@SpringBootTest
public class QueryStringTests {
	
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
	
}
