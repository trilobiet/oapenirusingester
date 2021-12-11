package org.oapen.irusuk.dataingestion;

import java.util.List;

public interface EventService<T extends Event> {

	T save(T entity);
	List<T> saveAll(List<T> entities); 
}
