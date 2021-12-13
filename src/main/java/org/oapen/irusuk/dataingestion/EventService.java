package org.oapen.irusuk.dataingestion;

import java.util.List;

/**
 * Service to save Events
 * @author acdhirr
 *
 * @param <T> The type of Event objects for this service
 */
public interface EventService<T extends Event> {

	T save(T entity);
	List<T> saveAll(List<T> entities); 
}
