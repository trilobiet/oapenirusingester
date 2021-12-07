package org.oapen.irusuk;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;

import org.oapen.irusuk.dataingestion.DataIngester;
import org.oapen.irusuk.dataingestion.DirectoryScanner;
import org.oapen.irusuk.dataingestion.IRReportParser;
import org.oapen.irusuk.dataingestion.jpa.EventRepository;
import org.oapen.irusuk.dataingestion.jpa.IpLocationRepository;
import org.oapen.irusuk.dataingestion.jpa.ItemRepository;
import org.oapen.irusuk.harvester.Harvester;
import org.oapen.irusuk.harvester.QueryString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * See  https://www.appsdeveloperblog.com/spring-boot-console-application/
 * 
 * @author acdhirr
 *
 */

@Component
@Order(value=1)
public class ApplicationRunner implements CommandLineRunner {
	
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private IpLocationRepository iplocRepo;	
	@Autowired 
	private AppStatus appStatus;
	@Autowired
	private String irusUkUrl;	
	
	@Value("${app.path.reports}")
	private String reportsPath;
	
	
	private static final Logger logger = 
			LoggerFactory.getLogger(IRReportParser.class);

	@Override
	public void run(String... args) throws Exception {
		
		if (args.length > 0) {
			
			if (args[0].equals("ingest")) ingest();
			else if (args[0].equals("harvest")) harvest();
			else logger.warn("Run with invalid argument. "
				+ "Don't understand what to do with '{}'.", args[0]);
		}
		else logger.warn("Run without argument. "
			+ "Choose 'ingest' or 'harvest'.");
	}
	
	public void harvest() {
		
		YearMonth ymStart = appStatus.getLastIngestedMonth().plusMonths(1);
		YearMonth ymEnd = ymStart;
		QueryString qs = new QueryString.Builder(ymStart, ymEnd).build();
		String destFileName = reportsPath + "/" 
			+ qs.getEndDate().toString() + ".json";
		UriComponents uric = UriComponentsBuilder.fromUriString(irusUkUrl)
			.query(qs.toString()).build();
		
		try {
			URL url = uric.toUri().toURL();
			Harvester h = new Harvester(url);
			logger.info("Harvesting from {}", uric);
			h.downloadTo(destFileName);
			logger.info("Harvested data saved to {}", destFileName);
		} catch (MalformedURLException | IllegalArgumentException e) {
			logger.error("{} - {}. Could not harvest from {}", 
				e.getClass(), e.getMessage(), uric);
		} catch (IOException e) {
			logger.error("{} Could not save to {}", e.getMessage(), destFileName);
		}
			
	}
	
	public void ingest() {
		
		Path path = Path.of(reportsPath.trim());
		YearMonth ym = appStatus.getLastIngestedMonth().plusMonths(1);
		logger.info("Ingester looking for month {} and later in {}",
			ym, path.toString());
		
		DirectoryScanner ds = new DirectoryScanner(path,ym);
		List<File> jsonFiles = ds.collect();
		logger.info("Found {} files", jsonFiles.size());

		DataIngester di = new DataIngester(itemRepo, eventRepo, iplocRepo);
		List<File> ingestedFiles = di.ingest(jsonFiles);
		ingestedFiles.forEach(f -> 
			logger.info("Ingested {}", f.getAbsolutePath())
		);
		
		// On success update appStatus with highest month in file list
		if(!ingestedFiles.isEmpty()) {
			String lastFileName = DirectoryScanner.getFileNameWithoutExtension(
				ingestedFiles.get(ingestedFiles.size()-1).getName()
			);
			appStatus.setLastIngestedMonth(YearMonth.parse(lastFileName));
		}
	}

}
