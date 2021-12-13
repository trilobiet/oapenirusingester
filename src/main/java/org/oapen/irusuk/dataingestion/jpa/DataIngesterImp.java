package org.oapen.irusuk.dataingestion.jpa;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.oapen.irusuk.dataingestion.DataIngester;
import org.oapen.irusuk.dataingestion.EventService;
import org.oapen.irusuk.dataingestion.IRReportParser;
import org.oapen.irusuk.dataingestion.ItemService;
import org.oapen.irusuk.entities.HeaderException;
import org.oapen.irusuk.iplookup.IpLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ingester implementation that uses separate services
 * for looking up Locations and saving ItemDTOs and EventDTOs.
 *  
 * @author acdhirr
 *
 */
public class DataIngesterImp implements DataIngester {

	private final ItemService<ItemDTO> itemService;
	private final EventService<EventDTO> eventService;
	private final IpLookupService<IpLocationDTO> ipLookupService;
		
	private static final Logger logger = 
		LoggerFactory.getLogger(IRReportParser.class);

	public DataIngesterImp(ItemService<ItemDTO> is, EventService<EventDTO> es, IpLookupService<IpLocationDTO> ils) {
		this.itemService = is;
		this.eventService = es;
		this.ipLookupService = ils;
	} 

	@Override
	public List<File> ingest(List<File> files)  {
		
		List<File> ingestedFiles = new ArrayList<>();
		
		List<HeaderException> headerExceptions = new ArrayList<>();
		
		for (File file:files) {	
			
			logger.info("Ingesting " + file.getAbsolutePath());
			
			IRReportParser parser = new IRReportParser(file);
			
			parser.parseHeader(header -> {
				headerExceptions.addAll(header.getExceptions());
			});
			
			boolean success = headerExceptions.isEmpty() 
				&& parser.parseItems(item -> {
				
				ReportItemToDTOMapper mapper = new ReportItemToDTOMapper(item,ipLookupService);
				ItemDTO itm = mapper.item();
				List<EventDTO> eventDTOs = mapper.events();
				itemService.save(itm);
				eventService.saveAll(eventDTOs); 
				logger.debug("Saved item {} and {} events.", 
					itm.getId(), eventDTOs.size() );
			});
			 
			if (success) ingestedFiles.add(file);
			else logger.warn("Could not ingest " + file.getAbsolutePath());
			headerExceptions.forEach(e -> logger.warn(e.toString()));
		}	
		
		return ingestedFiles; 
	}
	

}
