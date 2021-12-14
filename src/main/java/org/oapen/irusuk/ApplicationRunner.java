package org.oapen.irusuk;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;

import org.oapen.irusuk.dataingestion.DataIngester;
import org.oapen.irusuk.dataingestion.EventService;
import org.oapen.irusuk.dataingestion.IRReportParser;
import org.oapen.irusuk.dataingestion.IngestableFileFinder;
import org.oapen.irusuk.dataingestion.ItemService;
import org.oapen.irusuk.dataingestion.jpa.DataIngesterImp;
import org.oapen.irusuk.dataingestion.jpa.EventDTO;
import org.oapen.irusuk.dataingestion.jpa.IpLocationDTO;
import org.oapen.irusuk.dataingestion.jpa.ItemDTO;
import org.oapen.irusuk.harvester.Harvester;
import org.oapen.irusuk.harvester.QueryString;
import org.oapen.irusuk.iplookup.IpLookupService;
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
 * Main Controller for this implementation.
 * <br> 
 * See  https://www.appsdeveloperblog.com/spring-boot-console-application/
 * 
 * @author acdhirr
 *
 */

@Component
@Order(value=1)
public class ApplicationRunner implements CommandLineRunner {
	
	@Autowired
	private ItemService<ItemDTO> itemService;
	@Autowired
	private EventService<EventDTO> eventService;
	@Autowired
	private IpLookupService<IpLocationDTO> iplocService;	
	@Autowired 
	private AppStatus appStatus;
	@Autowired
	private String irusUkUrl;	
	
	@Value("${app.path.reports}")
	private String reportsPath;
	
	private static final Logger logger = 
			LoggerFactory.getLogger(IRReportParser.class);

	
	/**
	 * Run as either:
	 * <ul>
	 * 	<li> 
	 * 		[harvest]: download new data from IRUS UK 
	 * 	</li>
	 * 	<li> 
	 * 		[ingest]: process previously downloaded unprocessed data
	 * 	</li>
	 * </ul> 
	 */
	@Override
	public void run(String... args) throws Exception {
		
		String action = "";
		
		if (args.length > 0) action = args[0];

		if (action.equals("harvest")) harvest();
		else if (action.equals("ingest")) ingest();
		else {
			logger.warn("Run without proper argument. " 
					+ "\nChoose 'harvest' or 'ingest'.");
			System.out.println("Proper run arguments: [harvest|ingest]");
		}
	}
	
	/**
	 * Download a new month of data from IRUS-UK.
	 * <br><br> 
	 * The latest downloaded AND succesfully ingested month will be read
	 * from the application status service {@link AppStatus}. 
	 * <br><br>
	 * N.B. Downloads will always succeed (given a valid connection) but may 
	 * return a Report containing only a ReportHeader with Exceptions ('no data
	 * available for this month'), in which case ingestion will be aborted and 
	 * application status will not increase the month number. 
	 */
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
	 
	/**
	 * Ingest the latest downloaded report containing a month of data 
	 * from IRUS-UK. Only when this month report contains data (no header 
	 * exceptions) ingestion will succeed and the month number will be 
	 * increased in application status.
	 */
	public void ingest() {
		
		Path path = Path.of(reportsPath.trim());
		YearMonth ym = appStatus.getLastIngestedMonth().plusMonths(1);
		logger.info("Ingester looking for month {} and later in {}",
			ym, path.toString());
		
		IngestableFileFinder ds = new IngestableFileFinder(path,ym);
		List<File> jsonFiles = ds.collect();
		logger.info("Found {} files", jsonFiles.size());

		DataIngester di = new DataIngesterImp(itemService, eventService, iplocService);
		List<File> ingestedFiles = di.ingest(jsonFiles);
		ingestedFiles.forEach(f -> 
			logger.info("Ingested {}", f.getAbsolutePath())
		);
		
		// On success update appStatus with highest month in file list
		if(!ingestedFiles.isEmpty()) {
			String lastFileName = IngestableFileFinder.getFileNameWithoutExtension(
				ingestedFiles.get(ingestedFiles.size()-1).getName()
			);
			appStatus.setLastIngestedMonth(YearMonth.parse(lastFileName));
		}
	}

}
