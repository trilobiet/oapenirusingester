package org.oapen.irusuk.dataingestion.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IpLocationRepository extends JpaRepository<IpLocationDTO, Long> {

	@Query(value = ""
		+ "SELECT * FROM iplocation "
		+ "WHERE IP_FROM <= ?1 AND IP_TO >= ?1", nativeQuery = true)
	IpLocationDTO findByIp(Long ipNumber);
}
