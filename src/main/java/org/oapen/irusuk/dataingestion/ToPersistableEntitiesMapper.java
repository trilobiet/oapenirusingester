package org.oapen.irusuk.dataingestion;

import java.util.List;

/**
 * Describes a contract for a class that provides an Item
 * and its associated Events
 * 
 * @author acdhirr
 *
 * @param <T> The type of Item objects for this class
 * @param <U> The type of Event objects for this class
 */
public interface ToPersistableEntitiesMapper<T extends Item, U extends Event> {
	
	/**
	 * 
	 * @return persistable {@link org.oapen.irusuk.dataingestion.Item}
	 */
	public T item();
	/**
	 * 
	 * @return persistable List of {@link org.oapen.irusuk.dataingestion.Event}s
	 */
	public List<U> events();
}

