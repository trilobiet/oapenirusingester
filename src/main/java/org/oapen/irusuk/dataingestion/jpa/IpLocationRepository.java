package org.oapen.irusuk.dataingestion.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * JpaRepository for IpLocationDTO objects.
 * (Spring Boot instantiates objects implementing this interface.) 

 * @author acdhirr
 *
 */
interface IpLocationRepository extends JpaRepository<IpLocationDTO, Long> {

	/**
	 * Get IpLocationDTO for this ip number (numerical ip representation)
	 * @param ipNumber
	 * @return
	 */
	@Query(value = ""
		+ "SELECT * FROM iplocation "
		+ "WHERE IP_FROM <= ?1 AND IP_TO >= ?1", nativeQuery = true)
	IpLocationDTO findByIp(Long ipNumber);
}
