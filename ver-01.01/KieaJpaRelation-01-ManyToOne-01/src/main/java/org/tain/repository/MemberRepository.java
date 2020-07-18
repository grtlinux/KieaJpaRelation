package org.tain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tain.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

	@Query("select m.uid, count(p) from Member m left outer join Profile p "
			+ "on m.uid = p.member where m.uid = ?1 group by m")
	List<Object[]> getMemberWithProfileCount(String uid);
	
	@Query("select m, p from Member m left outer join Profile p "
			+ "on m.uid = p.member where m.uid = ?1 and p.flag = true")
	List<Object[]> getMemberWithProfile(String uid);
}
