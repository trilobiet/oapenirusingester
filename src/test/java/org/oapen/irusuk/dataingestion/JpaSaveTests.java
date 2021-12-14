package org.oapen.irusuk.dataingestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.oapen.irusuk.dataingestion.jpa.EventDTO;
import org.oapen.irusuk.dataingestion.jpa.FunderDTO;
import org.oapen.irusuk.dataingestion.jpa.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.EnabledIf;

/***
 * You have to explicitly ENABLE these tests in application.properties
 * 
 * @author acdhirr
 *
 */


@SpringBootTest
// Actual DB inserts. Only to be run on a test database.
@EnabledIf(expression = "${dbtests.enabled}", loadContext = true)
public class JpaSaveTests {
	
	@Autowired
	private ItemService<ItemDTO> itemService;
	
	@Autowired
	private EventService<EventDTO> eventRepo;
	
	@Test
	void testSaveItem() {
		
		ItemDTO i = new ItemDTO();
		i.setTitle("And now for something completely different");
		i.setId("1234567890/1");
		ItemDTO saved = itemService.save(i); 
		
		System.out.println("Saved Item: " + saved);
		
		assertEquals(saved.getId(), i.getId());
	}

	@Test
	void testSaveThenUpdateItem() {
		
		ItemDTO i = new ItemDTO();
		i.setTitle("A dead parrot");
		i.setId("1234567890/2");
		Item q = itemService.save(i);
		
		ItemDTO j = new ItemDTO();
		j.setId("1234567890/2");
		j.setTitle("The spanish inquisition");
		Item r = itemService.save(j);
		
		assertEquals(q.getId(),r.getId());
	}
	
	@Test
	void testSaveItemWithFunder() {
		
		ItemDTO i = new ItemDTO();
		i.setTitle("And now for something completely different");
		i.setId("1234567890/1");
		
		FunderDTO f = new FunderDTO();
		f.id = "d859fbd3-d884-4090-a0ec-baf821c9abfd";
		f.name = "Mega-Funder";
		i.addFunder(f);
		
		ItemDTO saved = itemService.save(i);
		assertTrue(saved.getFunders().size()==1);
	}

	@Test
	void testSaveItemWithEvents() {
		
		ItemDTO i = new ItemDTO();
		i.setTitle("The meaning of life");
		i.setId("1234567890/3");
		
		EventDTO e1 = new EventDTO();
		e1.setItemId(i.getId());
		e1.setDate(YearMonth.of(2021, 11).atEndOfMonth());
		e1.setIp("83.163.15.48");
		
		itemService.save(i);
		EventDTO savedEvent = eventRepo.save(e1);

		assertEquals(e1.getIp(),savedEvent.getIp());
	}

	@Test
	void testItemMandatoryFieldsMissing() {
		
		ItemDTO i1 = new ItemDTO();
		i1.setTitle("The meaning of life");
		
		assertThrows(DataAccessException.class, () -> {
			itemService.save(i1);
		});
	}
	
	@Test
	void testEventMandatoryFieldsMissing() {
		
		EventDTO e1 = new EventDTO();
		e1.setIp("83.163.15.48");
		
		assertThrows(DataAccessException.class, () -> {
			eventRepo.save(e1);
		});
	}
	
}
