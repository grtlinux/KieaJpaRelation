package org.tain.repository.m2o;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tain.domain.m2o.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
