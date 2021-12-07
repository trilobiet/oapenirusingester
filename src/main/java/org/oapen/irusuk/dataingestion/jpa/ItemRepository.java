package org.oapen.irusuk.dataingestion.jpa;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<ItemDTO, String> {

}
