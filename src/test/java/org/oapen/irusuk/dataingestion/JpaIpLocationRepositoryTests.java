package org.oapen.irusuk.dataingestion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.oapen.irusuk.dataingestion.jpa.IpLocationDTO;
import org.oapen.irusuk.entities.IpAddress;
import org.oapen.irusuk.iplookup.IpLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

@SpringBootTest
//Takes a few minutes to load an entire table into memory 
@EnabledIf(expression = "${iploadtests.enabled}", loadContext = true)
public class JpaIpLocationRepositoryTests {
	
	@Autowired
	private IpLookupService<IpLocationDTO> ipService;
	
	@Test
	void testFindIpLocation() {
		
		IpAddress ip = new IpAddress("123.124.125.126");
		IpLocationDTO loc = ipService.findByIp(ip);
		System.out.println("Location: " + loc);
		assertNotNull(loc);
	}
}
