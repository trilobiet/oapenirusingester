package org.oapen.irusuk.harvester;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.YearMonth;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.oapen.irusuk.entities.HeaderException;
import org.oapen.irusuk.entities.IRReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
public class HarvesterTests {

	@Autowired
	private String irusUkUrl;
	
	private final String harvestPath = "src/test/resources/harvested";
	
	@Test
	void downloadReportTest() throws URISyntaxException, IOException {
		
		// This will return a report, but always without item data. 
		QueryString qs = new QueryString
			.Builder(YearMonth.of(1980,04),YearMonth.of(1980,04))
			.build();
		
		URL url = UriComponentsBuilder.fromUriString(irusUkUrl)
			.query(qs.toString()).build().toUri().toURL();
		
		String filePath = 
			harvestPath + "/" + qs.getEndDate().toString() + ".json";
		
		Harvester h = new Harvester(url);
		h.downloadTo(filePath);

		// File must exist and be a file
		File file = new File(filePath);
		assertTrue(file.exists());
		assertTrue(file.isFile());

		// File must be downloaded just now (say less than 2 seconds ago) 
		long q = file.lastModified();
		Instant i = Instant.ofEpochMilli(q);
		Instant n = Instant.now();
		Duration res = Duration.between(i, n);
		
		assertTrue(res.getSeconds() < 2);
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
