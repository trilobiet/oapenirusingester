package org.oapen.irusuk.dataingestion;

import java.util.List;

/**
 * Service to save Items
 * @author acdhirr
 *
 * @param <T> The type of Event objects for this service
 */
public interface ItemService<T extends Item> {

	T save(T entity);
	List<T> saveAll(List<T> entities); 
}
