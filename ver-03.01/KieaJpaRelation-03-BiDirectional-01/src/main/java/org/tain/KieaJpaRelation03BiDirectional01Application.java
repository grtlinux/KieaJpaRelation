package org.tain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;
import org.tain.domain.Board;
import org.tain.domain.Reply;
import org.tain.repository.BoardRepository;
import org.tain.repository.ReplyRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaJpaRelation03BiDirectional01Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaJpaRelation03BiDirectional01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		if (Flag.flag) job01();
		if (Flag.flag) job02();
		if (!Flag.flag) job03();
		if (!Flag.flag) job04();
		if (Flag.flag) job05();
		if (Flag.flag) job06();
		if (Flag.flag) job07();
		if (Flag.flag) job08();
		if (Flag.flag) job09();
		if (Flag.flag) job10();
	}

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;

	private void job01() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		List<Board> listBoard = new ArrayList<>();
		
		IntStream.rangeClosed(1, 10).forEach(index -> {
			Board board = new Board();
			board.setTitle("Free board..." + index);
			board.setContent("Free content....." + index);
			board.setWriter("user" + (index % 3));
			
			listBoard.add(board);
		});
		this.boardRepository.saveAll(listBoard);
	}

	private void job02() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		Board board = new Board();
		board.setId(10L);
		
		Reply reply = new Reply();
		reply.setReply("REPLY for 10...............");
		reply.setReplyer("replyer10");
		reply.setBoard(board);
		
		this.replyRepository.save(reply);
	}

	private void job03() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		Optional<Board> result = this.boardRepository.findById(10L);
		result.ifPresent(board -> {
			Reply reply = new Reply();
			reply.setReply("REPLY ......................");
			reply.setReplyer("replyer00");
			reply.setBoard(board);
			
			List<Reply> replies = board.getReplies();
			replies.add(reply);                     // TODO: ERROR
			
			board.setReplies(replies);
			this.boardRepository.save(board);
		});
	}

	@Transactional
	private void job04() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
		this.boardRepository.findByIdGreaterThan(0L, pageable).forEach(board -> {
			System.out.println("4 >>>>> " + board + " : " + board.getReplies().size());
			//System.out.println("4 >>>>> " + board);
		});
	}

	private void job05() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
		this.boardRepository.getPage(pageable).forEach(arr -> {
			System.out.println("4 >>>>> " + Arrays.asList(arr));
		});
	}

	private void job06() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
	}

	private void job07() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
	}

	private void job08() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
	}

	private void job09() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
	}

	private void job10() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
	}
}
