package org.oapen.irusuk.dataingestion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.oapen.irusuk.dataingestion.jpa.EventDTO;
import org.oapen.irusuk.dataingestion.jpa.EventRepository;
import org.oapen.irusuk.dataingestion.jpa.IpLocationDTO;
import org.oapen.irusuk.dataingestion.jpa.IpLocationRepository;
import org.oapen.irusuk.dataingestion.jpa.ItemDTO;
import org.oapen.irusuk.dataingestion.jpa.ItemRepository;
import org.oapen.irusuk.entities.HeaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

public class DataIngester {

	private final ItemRepository itemRepo;
	private final EventRepository eventRepo;
	private final IpLocationRepository iplocRepo;
		
	private static final Logger logger = 
		LoggerFactory.getLogger(IRReportParser.class);

	public DataIngester(
		ItemRepository itemRepo, 
		EventRepository eventRepo, 
		IpLocationRepository iplocRepo) {
		
		this.itemRepo = itemRepo;
		this.eventRepo = eventRepo;
		this.iplocRepo = iplocRepo;
	}

	public List<File> ingest(List<File> files)  {
		
		// Table to hold ip addresses in memory
		final TreeMap<Long, IpLocationDTO> ipMap = new TreeMap<>();
		
		List<File> ingestedFiles = new ArrayList<>();
		
		List<HeaderException> headerExceptions = new ArrayList<>();
		
		for (File file:files) {	
			
			logger.info("Ingesting " + file.getAbsolutePath());
			
			IRReportParser parser = new IRReportParser(file);
			
			parser.parseHeader(header -> {
				headerExceptions.addAll(header.getExceptions());
			});
			
			// Load ipMap on first encountered file having items 
			if (headerExceptions.isEmpty() && ipMap.isEmpty()) 
				loadIpLocationsInMap(iplocRepo, ipMap);
			
			boolean success = headerExceptions.isEmpty() 
				&& parser.parseItems(item -> {
				
				DTOMapper mapper = new DTOMapper(item,ipMap);
				ItemDTO itemDTO = mapper.itemDTO();
				List<EventDTO> eventDTOs = mapper.eventDTOs();
				itemRepo.save(itemDTO);
				eventRepo.saveAll(eventDTOs); 
				logger.debug("Saved item {} and {} events.", 
					itemDTO.id, eventDTOs.size() );
			});
			
			if (success) ingestedFiles.add(file);
			else logger.warn("Could not ingest " + file.getAbsolutePath());
			headerExceptions.forEach(e -> logger.warn(e.toString()));
		}	
		
		ipMap.clear();
		return ingestedFiles; 
	}
	
	
	private void loadIpLocationsInMap(
		IpLocationRepository ipRepo, TreeMap<Long, IpLocationDTO> ipMap) {
		
		logger.info("Loading ip location data from database in memory.");
		
		try {
			ipRepo.findAll().stream()
				.forEach(ip -> ipMap.put(ip.ipFrom, ip));
		}
		catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
	}

}
