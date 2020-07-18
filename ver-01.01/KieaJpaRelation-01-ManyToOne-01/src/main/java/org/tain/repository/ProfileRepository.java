package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tain.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
