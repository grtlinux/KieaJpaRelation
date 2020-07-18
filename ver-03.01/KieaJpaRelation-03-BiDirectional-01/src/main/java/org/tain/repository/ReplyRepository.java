package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tain.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

}
