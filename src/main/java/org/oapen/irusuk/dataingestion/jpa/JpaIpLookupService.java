package org.oapen.irusuk.dataingestion.jpa;

import java.util.TreeMap;

import org.oapen.irusuk.entities.IpAddress;
import org.oapen.irusuk.iplookup.IpLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class JpaIpLookupService implements IpLookupService<IpLocationDTO> {
	
	private final int CHUNK_SIZE = 50_000;

	@Autowired
	private IpLocationRepository repo;
	private final TreeMap<Long, IpLocationDTO> ipMap = new TreeMap<>();
	
	private static final Logger logger = 
		LoggerFactory.getLogger(JpaIpLookupService.class);
	
	@Override
	public IpLocationDTO findByIp(IpAddress ip) {
		
		// Some laziness
		if(!ipMap.isEmpty() || init()) {
			return ipMap.floorEntry(ip.toIp4Number()).getValue();
		}
		return null;
	}

	
	private boolean init() {
		
		if (ipMap.isEmpty()) try {
			
			logger.info("Start loading ip addresses on first request.");
			
			long count = repo.count();
			int size = CHUNK_SIZE;
			long pages = (count+size-1)/size ;

			// Chunks of CHUNK_SIZE prevent OOM errors (jdbc/java)
			for (int page=0; page<pages; page++) {
				Pageable pg = PageRequest.of(page, size);
				logger.debug("Loading ip address page {} of {}.", page, pages);
				repo.findAll(pg).stream().forEach(ip -> ipMap.put(ip.getIp(), ip));
			}
			
			logger.info("Loaded {} ip addresses in memory.", ipMap.size());
		}
		catch (DataAccessException e) {
			return false;
		}
		return true;
	}
	
}
