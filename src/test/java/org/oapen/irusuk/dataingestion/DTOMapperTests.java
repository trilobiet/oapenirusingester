package org.oapen.irusuk.dataingestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.oapen.irusuk.dataingestion.jpa.EventDTO;
import org.oapen.irusuk.dataingestion.jpa.IpLocationDTO;
import org.oapen.irusuk.dataingestion.jpa.ItemDTO;
import org.oapen.irusuk.entities.ReportItem;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DTOMapperTests {
	
	TreeMap<Long, IpLocationDTO> ipLocationMap = new TreeMap<>();
	
	String path = "src/test/resources/report-with-items-with-funders.json";
	File file = new File(path);
	IRReportParser parser = new IRReportParser(file);
	List<ReportItem> reportItems = new ArrayList<>();
	Optional<Exception> exception = Optional.empty();
	
	{
		ipLocationMap.put(10L,new IpLocationDTO());
		parser.parseItems(reportItems::add);
	}
	
	@Test
	@Order(1)    
	void testParsingSucceeded() {

		assertTrue(exception.isEmpty());
		assertTrue(!reportItems.isEmpty());
	}	
	
	@Test
	@Order(2)
	void testItemMapping() {
		
		ReportItem item = reportItems.get(0);
		DTOMapper mapper = new DTOMapper(item,ipLocationMap);
		ItemDTO itemDTO = mapper.itemDTO();

		assertTrue(!reportItems.isEmpty());
		assertNotNull(itemDTO);
		assertEquals("20.500.12657/28390", itemDTO.id);
		System.out.println(itemDTO);
	}	
	
	@Test
	@Order(3)
	void testEventsMapping() {

		ReportItem item = reportItems.get(0);
		DTOMapper mapper = new DTOMapper(item,ipLocationMap);
		
		ItemDTO itemDTO = mapper.itemDTO();
		List<EventDTO> eventDTOs = mapper.eventDTOs();
		assertTrue(eventDTOs.size()==2);
		assertEquals("Italy", eventDTOs.get(0).country);
		assertEquals(itemDTO.id, eventDTOs.get(0).itemId);
		
		System.out.println(eventDTOs);
	}	
	
	
}
