package org.tain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tain.domain.PdsBoard;

@Repository
public interface PdsBoardRepository extends JpaRepository<PdsBoard, Long>{

	@Transactional
	@Modifying
	//@Query(value = "update from TB_FILE f set f.fname = ?2 where f.fid = ?1", nativeQuery = true)  // ERROR
	@Query("update from PdsFile f set f.fname = ?2 where f.fid = ?1")
	public int updatePdsFile(Long fid, String fName);
	
	@Transactional
	@Modifying
	@Query("delete from PdsFile f where f.fid = ?1")
	public int deletePdsFile(Long fid);
	
	@Query("select p, count(f) "
			+ "from PdsBoard p left outer join p.files f "
			+ "where p.bid > 0 "
			+ "group by p "
			+ "order by p.bid desc")
	public List<Object[]> getSummary();
}
