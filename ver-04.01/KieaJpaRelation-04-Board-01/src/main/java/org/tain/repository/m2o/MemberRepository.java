package org.tain.repository.m2o;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tain.domain.m2o.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

	@Query("select m.uid, m.uname, count(p) from Member m left outer join Profile p on m.uid = p.member group by m")
	public List<Object[]> getMemberWithProfileCount();
	
	@Query("select m.uid, m.uname, p.id, p.fname from Member m left outer join Profile p on m.uid = p.member")
	public List<Object[]> getMemberWithProfile();
	
	@Query("select m.uid, m.uname, p.id, p.fname from Member m left outer join Profile p on m.uid = p.member where m.uid = ?1")
	public List<Object[]> getMemberWithProfileByUid(String uid);
}
