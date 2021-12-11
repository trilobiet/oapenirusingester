package org.oapen.irusuk.dataingestion;

import java.util.List;

public interface JSONEntityToDTOMapper<T extends Item, U extends Event> {
	
	public T item();
	public List<U> events();
}

