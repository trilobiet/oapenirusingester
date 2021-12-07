package org.oapen.irusuk.harvester;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
public class HarvesterTests {

	@Autowired
	private String irusUkUrl;
	
	private final String harvestPath = "src/test/resources/harvested";
	
	@Test
	void downloadReportTest() throws URISyntaxException, IOException {
		
		QueryString qs = new QueryString
			.Builder(YearMonth.of(2022,04),YearMonth.of(2022,04))
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
	
	
}
