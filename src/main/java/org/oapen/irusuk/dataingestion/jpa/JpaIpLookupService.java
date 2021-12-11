package org.oapen.irusuk.dataingestion.jpa;

import java.util.TreeMap;

import org.oapen.irusuk.dataingestion.IRReportParser;
import org.oapen.irusuk.entities.IpAddress;
import org.oapen.irusuk.iplookup.IpLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class JpaIpLookupService implements IpLookupService<IpLocationDTO> {

	@Autowired
	private IpLocationRepository repo;
	private final TreeMap<Long, IpLocationDTO> ipMap = new TreeMap<>();
	
	private static final Logger logger = 
		LoggerFactory.getLogger(IRReportParser.class);
	
	private boolean init() {
		
		if (ipMap.isEmpty()) try {
			logger.info("Start loading ip addresses on first request.");
			repo.findAll().stream().forEach(ip -> ipMap.put(ip.getIp(), ip));
			logger.info("Loaded {} ip addresses in memory.", ipMap.size());
		}
		catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public IpLocationDTO findByIp(IpAddress ip) {
		
		// Some laziness
		if(!ipMap.isEmpty() || init()) {
			return ipMap.floorEntry(ip.toIp4Number()).getValue();
		}
		return null;
	} 
	
}
