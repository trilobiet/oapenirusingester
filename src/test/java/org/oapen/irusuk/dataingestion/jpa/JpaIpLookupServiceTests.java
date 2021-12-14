package org.oapen.irusuk.dataingestion.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.oapen.irusuk.entities.IpAddress;
import org.oapen.irusuk.iplookup.IpLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaIpLookupServiceTests {
	
	@Autowired
	private IpLookupService<IpLocationDTO> ipService;
	
	@Test
	void testFindIpLocation() {
		
		IpAddress ip = new IpAddress("123.124.125.126");
		IpLocationDTO loc = ipService.findByIp(ip);
		// System.out.println("Location: " + loc);
		assertNotNull(loc);
	}
	
}
