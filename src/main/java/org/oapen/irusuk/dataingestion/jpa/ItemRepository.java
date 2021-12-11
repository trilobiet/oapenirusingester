package org.oapen.irusuk.dataingestion.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

interface ItemRepository extends JpaRepository<ItemDTO, String> {

}
