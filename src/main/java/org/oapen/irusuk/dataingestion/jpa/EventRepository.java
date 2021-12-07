package org.oapen.irusuk.dataingestion.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<EventDTO, Long> {

	@Query(value = ""
		+ "SELECT * FROM event "
		+ "WHERE item_id = ?1", nativeQuery = true)
	List<EventDTO> findByItemId(String itemId);
	
}
