package org.oapen.irusuk.dataingestion.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.oapen.irusuk.dataingestion.IRReportParser;
import org.oapen.irusuk.dataingestion.ToPersistableEntitiesMapper;
import org.oapen.irusuk.entities.ReportItem;
import org.oapen.irusuk.iplookup.IpLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportItemToDTOMapperTests {
	
	@Autowired
	IpLookupService<IpLocationDTO> ipLookupService;
	
	String path = "src/test/resources/report-with-items-with-funders.json";
	File file = new File(path);
	IRReportParser parser = new IRReportParser(file);
	List<ReportItem> reportItems = new ArrayList<>();
	Optional<Exception> exception = Optional.empty();
	
	{
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
		
		ReportItem reportItem = reportItems.get(0);
		ToPersistableEntitiesMapper<ItemDTO, EventDTO> mapper = new ReportItemToDTOMapper(reportItem,ipLookupService);
		ItemDTO item = mapper.item();

		assertNotNull(item);
		assertEquals("20.500.12657/28390", item.getId());
	}	
	
	@Test
	@Order(3)
	void testEventsMapping() {

		ReportItem item = reportItems.get(0);
		ToPersistableEntitiesMapper<ItemDTO, EventDTO> mapper = new ReportItemToDTOMapper(item,ipLookupService);
		
		ItemDTO itemDTO = mapper.item();
		
		// System.out.println(itemDTO);
		
		List<EventDTO> eventDTOs = mapper.events();
		assertTrue(eventDTOs.size()==2);
		
		// System.out.println(eventDTOs.get(0));
		
		assertEquals("Australia", eventDTOs.get(0).getCountry());
		assertEquals(itemDTO.getId(), eventDTOs.get(0).getItemId());
		
		// System.out.println(eventDTOs);
	}	
	
	
}
