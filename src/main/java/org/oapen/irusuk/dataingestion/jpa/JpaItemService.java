package org.oapen.irusuk.dataingestion.jpa;

import java.util.List;

import org.oapen.irusuk.dataingestion.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaItemService implements ItemService<ItemDTO> {
	
	@Autowired
	private ItemRepository repo;

	@Override
	public ItemDTO save(ItemDTO entity) {
		return repo.save(entity);
	}

	@Override
	public List<ItemDTO> saveAll(List<ItemDTO> entities) {
		return repo.saveAll(entities);
	}

}
