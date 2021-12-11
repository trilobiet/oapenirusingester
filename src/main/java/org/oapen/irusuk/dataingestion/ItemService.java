package org.oapen.irusuk.dataingestion;

import java.util.List;

public interface ItemService<T extends Item> {

	T save(T entity);
	List<T> saveAll(List<T> entities); 
}
