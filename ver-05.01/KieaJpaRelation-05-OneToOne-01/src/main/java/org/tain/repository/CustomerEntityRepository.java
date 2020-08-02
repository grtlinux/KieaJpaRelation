package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.CustomerEntity;

@RepositoryRestResource
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long>{

}
