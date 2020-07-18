package org.tain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tain.domain.Board;

//@Transactional
public interface BoardRepository extends JpaRepository<Board, Long>{

	public Page<Board> findByIdGreaterThan(Long id, Pageable pageable);
	
	@Query("select b.id, b.title, count(r) "
			+ "from Board b left outer join b.replies r "
			+ "where b.id > 0 "
			+ "group by b")
	public List<Object[]> getPage(Pageable pageable);
}
