package org.oapen.irusuk.dataingestion;

public interface EntityDTOMapper<S,T> {
	
	public T map(S inputEntity);
}
