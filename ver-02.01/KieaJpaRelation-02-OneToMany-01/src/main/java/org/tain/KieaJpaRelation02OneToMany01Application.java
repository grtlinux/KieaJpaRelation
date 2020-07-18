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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.domain.PdsBoard;
import org.tain.domain.PdsFile;
import org.tain.repository.PdsBoardRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaJpaRelation02OneToMany01Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaJpaRelation02OneToMany01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		if (Flag.flag) job01();
		if (Flag.flag) job02();
		if (!Flag.flag) job03();
		if (Flag.flag) job04();
		if (Flag.flag) job05();
		if (Flag.flag) job06();
		if (Flag.flag) job07();
		if (Flag.flag) job08();
		if (Flag.flag) job09();
		if (Flag.flag) job10();
	}

	@Autowired
	private PdsBoardRepository pdsBoardRepository;

	private void job01() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		PdsFile file1 = new PdsFile();
		file1.setFname("file1.doc");
		PdsFile file2 = new PdsFile();
		file2.setFname("file2.doc");
		
		PdsBoard pdsBoard = new PdsBoard();
		pdsBoard.setBname("Document");
		pdsBoard.setBwriter("kiea");
		pdsBoard.setFiles(Arrays.asList(file1, file2));
		
		this.pdsBoardRepository.save(pdsBoard);
	}

	private void job02() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		int count = this.pdsBoardRepository.updatePdsFile(2L, "updateFile.doc");
		System.out.println(">>>>> count = " + count);
	}

	private void job03() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		String newFname = "updateFile2.doc";
		
		Optional<PdsBoard> result = this.pdsBoardRepository.findById(1L);
		result.ifPresent(board -> {
			PdsFile file = new PdsFile();
			file.setFid(2L);
			file.setFname(newFname);
			
			int idx = board.getFiles().indexOf(file); // TODO: ERROR
			if (idx > -1) {
				List<PdsFile> list = board.getFiles();
				list.remove(idx);
				list.add(file);
				board.setFiles(list);
				this.pdsBoardRepository.save(board);
			}
		});
	}

	private void job04() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		int count = this.pdsBoardRepository.deletePdsFile(2L);
		System.out.println(">>>>> count = " + count);
	}

	private void job05() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		List<PdsBoard> list = new ArrayList<>();
		
		IntStream.rangeClosed(1, 10).forEach(index -> {
			PdsBoard board = new PdsBoard();
			board.setBname("자료-" + index);
			board.setBwriter("작가-" + index);
			
			PdsFile file1 = new PdsFile();
			file1.setFname("file1-" + index + ".doc");
			PdsFile file2 = new PdsFile();
			file2.setFname("file2-" + index + ".doc");
			board.setFiles(Arrays.asList(file1, file2));
			
			list.add(board);
		});
		
		this.pdsBoardRepository.saveAll(list);
	}

	private void job06() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		this.pdsBoardRepository.getSummary().forEach(arr -> {
			System.out.println("6 >>>>> " + Arrays.toString(arr));
		});
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
