package org.oapen.irusuk.dataingestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.oapen.irusuk.dataingestion.jpa.IpLocationDTO;
import org.oapen.irusuk.dataingestion.jpa.IpLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

@SpringBootTest
//Takes a few minutes to load an entire table into memory 
@EnabledIf(expression = "${iploadtests.enabled}", loadContext = true)
public class JpaIpLocationRepositoryTests {
	
	private TreeMap<Long, IpLocationDTO> ipMap = new TreeMap<>();
	
	@Autowired
	private IpLocationRepository ipRepo;
	
	@Test
	@Order(1)
	void mapTests() throws IOException {
		
		long mt = System.currentTimeMillis();

		ipRepo.findAll().stream().forEach(ipRecord -> {
			ipMap.put(ipRecord.ipFrom, ipRecord);
		});
		
		System.out.printf("Tree population time: %s milliseconds.%n",System.currentTimeMillis()-mt);
		
		/*
		// About 300-400 MB
		FileOutputStream fileOut = new FileOutputStream("/home/acdhirr/buuuuuuuuuuuuurp.obj");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(ipMap);
        objectOut.close();
        */
		
		// There should be more than 2.000.000 ip records in DB 
		assertTrue(ipMap.size() > 2_000_000);
		
		long nt = System.nanoTime();
		Entry<Long, IpLocationDTO> q = ipMap.floorEntry(16801300L);
		assertEquals(q.getValue().country, "Japan");
		System.out.printf("Lookup time: %s nanoseconds.%n",System.nanoTime()-nt);
	}

	
}
