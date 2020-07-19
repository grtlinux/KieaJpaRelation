package org.tain.repository.o2m;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.tain.domain.o2m.PdsBoard;

public interface PdsBoardRepository extends JpaRepository<PdsBoard, Long>{

	@Transactional
	@Modifying
	//@Query(value = "update from TB_FILE f set f.fname = ?2 where f.fid = ?1", nativeQuery = true)  // ERROR
	@Query("update from PdsFile f set f.fname = ?2 where f.id = ?1")
	public int updatePdsFile(Long fid, String fName);
	
	@Transactional
	@Modifying
	@Query("delete from PdsFile f where f.id = ?1")
	public int deletePdsFile(Long fid);
	
	@Query("select b, count(f) "
			+ "from PdsBoard b left outer join b.files f "
			+ "where b.id > 0 "
			+ "group by b "
			+ "order by b.id desc")
	public List<Object[]> getSummary();
}
