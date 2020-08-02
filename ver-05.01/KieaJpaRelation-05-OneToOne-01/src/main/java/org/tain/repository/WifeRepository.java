package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.Wife;
import org.tain.info.RemodelingWifeInfo;
import org.tain.info.WifeInfo;

@RepositoryRestResource
public interface WifeRepository extends JpaRepository<Wife, Long>{

	WifeInfo findWifeByFirstName(String firstName);
	
	@Query("select w from Wife w where w.firstName = ?1")
	RemodelingWifeInfo findRemodelingWifeByFirstName(String firstName);
}
