package org.oapen.irusuk.dataingestion.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * JpaRepository for EventDTO objects.
 * (Spring Boot instantiates objects implementing this interface.) 
 * 
 * @author acdhirr
 *
 */
interface EventRepository extends JpaRepository<EventDTO, Long> {

	/**
	 * List EventDTOs belonging to an ItemDTO 
	 * @param itemId
	 * @return List of EventDTOs for this Item id.
	 */
	@Query(value = ""
		+ "SELECT * FROM event "
		+ "WHERE item_id = ?1", nativeQuery = true)
	List<EventDTO> findByItemId(String itemId);
	
}
