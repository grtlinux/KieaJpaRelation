package org.tain.repository.bidic;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tain.domain.bidic.KBoard;

//@Transactional
public interface KBoardRepository extends JpaRepository<KBoard, Long>{

	public Page<KBoard> findByIdGreaterThan(Long id, Pageable pageable);
	
	@Query("select b.id, b.title, count(r) "
			+ "from KBoard b left outer join b.kreplies r "
			+ "where b.id > 0 "
			+ "group by b")
	public List<Object[]> getPage(Pageable pageable);
}
