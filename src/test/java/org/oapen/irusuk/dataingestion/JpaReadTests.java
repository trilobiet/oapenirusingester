package org.oapen.irusuk.dataingestion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.oapen.irusuk.dataingestion.jpa.IpLocationDTO;
import org.oapen.irusuk.dataingestion.jpa.IpLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JpaReadTests {
	
	@Autowired
	private IpLocationRepository ipRepo;
	
	@Test
	void testFindIpLocation() {
		
		IpLocationDTO loc = ipRepo.findByIp(16801280L);
		System.out.println(loc);
		assertNotNull(loc);
	}
	
}
