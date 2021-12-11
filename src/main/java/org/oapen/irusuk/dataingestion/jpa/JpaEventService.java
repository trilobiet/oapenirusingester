package org.oapen.irusuk.dataingestion.jpa;

import java.util.List;

import org.oapen.irusuk.dataingestion.EventService;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaEventService implements EventService<EventDTO> {
	
	@Autowired
	private EventRepository repo;

	@Override
	public EventDTO save(EventDTO entity) {
		return repo.save(entity);
	}

	@Override
	public List<EventDTO> saveAll(List<EventDTO> entities) {
		return repo.saveAll(entities);
	}

}
