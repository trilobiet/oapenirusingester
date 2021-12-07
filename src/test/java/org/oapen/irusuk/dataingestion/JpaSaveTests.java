package org.oapen.irusuk.dataingestion;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.oapen.irusuk.dataingestion.jpa.EventDTO;
import org.oapen.irusuk.dataingestion.jpa.EventRepository;
import org.oapen.irusuk.dataingestion.jpa.FunderDTO;
import org.oapen.irusuk.dataingestion.jpa.ItemDTO;
import org.oapen.irusuk.dataingestion.jpa.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.EnabledIf;

/***
 * You have to explicitely ENABLE these tests in application.properties
 * 
 * @author acdhirr
 *
 */


@SpringBootTest
// Actual DB inserts. Only to be run on a test database.
@EnabledIf(expression = "${dbtests.enabled}", loadContext = true)
public class JpaSaveTests {
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Test
	void testSaveItem() {
		
		ItemDTO i = new ItemDTO();
		i.title = "And now for something completely different";
		i.id = "1234567890/1";
		itemRepo.save(i);
		
		Optional<ItemDTO> j = itemRepo.findById(i.id);
		assertTrue(j.isPresent());
	}

	@Test
	void testSaveThenUpdateItem() {
		
		ItemDTO i = new ItemDTO();
		i.title = "A dead parrot";
		i.id = "1234567890/2";
		ItemDTO q = itemRepo.save(i);
		
		ItemDTO j = new ItemDTO();
		j.id = "1234567890/2";
		j.title = "The spanish inquisition";
		ItemDTO r = itemRepo.save(j);
		assertTrue(q.id == r.id);

		Optional<ItemDTO> k = itemRepo.findById(i.id);
		assertTrue(k.isPresent());
		assertTrue(k.get().title.equals(j.title));
	}
	
	@Test
	void testSaveItemWithFunder() {
		
		ItemDTO i = new ItemDTO();
		i.title = "And now for something completely different";
		i.id = "1234567890/1";
		
		FunderDTO f = new FunderDTO();
		f.id = "d859fbd3-d884-4090-a0ec-baf821c9abfd";
		f.name = "Mega-Funder";
		i.addFunder(f);
		
		itemRepo.save(i);
		
		Optional<ItemDTO> j = itemRepo.findById(i.id);
		assertTrue(j.isPresent());
		assertTrue(j.get().funders.size()==1);
	}

	@Test
	void testSaveItemWithEvents() {
		
		ItemDTO i = new ItemDTO();
		i.title = "The meaning of life";
		i.id = "1234567890/3";
		
		EventDTO e1 = new EventDTO();
		e1.itemId = i.id;
		e1.date = YearMonth.of(2021, 11).atEndOfMonth();
		e1.ip = "83.163.15.48";
		
		EventDTO e2 = new EventDTO();
		e2.itemId = i.id;
		e2.date = YearMonth.of(2021, 11).atEndOfMonth();
		e2.ip = "83.163.15.49";
		e2.country = "Afghanistan";

		itemRepo.save(i);
		eventRepo.save(e1);
		eventRepo.save(e2);
		
		Optional<ItemDTO> j = itemRepo.findById(i.id);
		assertTrue(j.isPresent());
		
		List<EventDTO> events = eventRepo.findByItemId(j.get().id);
		assertTrue(events.size()>=2);
	}

	@Test
	void testItemMandatoryFieldsMissing() {
		
		ItemDTO i1 = new ItemDTO();
		i1.title = "The meaning of life";
		
		assertThrows(DataAccessException.class, () -> {
			itemRepo.save(i1);
		});
	}
	
	@Test
	void testEventMandatoryFieldsMissing() {
		
		EventDTO e1 = new EventDTO();
		e1.ip = "83.163.15.48";
		
		assertThrows(DataAccessException.class, () -> {
			eventRepo.save(e1);
		});
	}
	
}
