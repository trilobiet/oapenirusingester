package org.oapen.irusuk.dataingestion.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository for ItemDTO objects.
 * (Spring Boot instantiates objects implementing this interface.) 

 * @author acdhirr
 *
 */
interface ItemRepository extends JpaRepository<ItemDTO, String> {

}
