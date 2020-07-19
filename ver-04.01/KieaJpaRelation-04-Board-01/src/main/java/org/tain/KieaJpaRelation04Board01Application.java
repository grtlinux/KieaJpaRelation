package org.tain;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.domain.Board;
import org.tain.domain.bidic.KBoard;
import org.tain.domain.bidic.KReply;
import org.tain.domain.m2o.Member;
import org.tain.domain.m2o.Profile;
import org.tain.domain.o2m.PdsBoard;
import org.tain.domain.o2m.PdsFile;
import org.tain.repository.BoardRepository;
import org.tain.repository.bidic.KBoardRepository;
import org.tain.repository.bidic.KReplyRepository;
import org.tain.repository.m2o.MemberRepository;
import org.tain.repository.m2o.ProfileRepository;
import org.tain.repository.o2m.PdsBoardRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaJpaRelation04Board01Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaJpaRelation04Board01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
		if (!Flag.flag) job01();
		
		if (Flag.flag) job02();
		if (Flag.flag) job03();
		if (Flag.flag) job04();
		if (Flag.flag) job05();
		if (Flag.flag) job06();
		if (Flag.flag) job07();
		if (Flag.flag) job08();
		if (Flag.flag) job09();
		if (Flag.flag) job10();
	}

	@Autowired
	private BoardRepository boardRepository;
	
	private void job01() {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
		
		IntStream.rangeClosed(1, 200).forEach(index -> {
			this.boardRepository.save(Board.builder()
					.title("제목-" + index)
					.subTitle("부제목-" + index)
					.content("안녕하세요.\n내용입니다.\n감사합니다.")
					.userId("kiea")
					.build());
		});
	}

	/*
	 * @ManyToOne
	 */
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	private void job02() {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			List<Member> listMember = new ArrayList<>();
			IntStream.rangeClosed(1, 11).forEach(index -> {
				Member member = new Member();
				if (index == 11)
					member.setUid("uid1");
				else
					member.setUid("uid" + index);
				member.setUname("사용자-" + index);
				listMember.add(member);
			});
			this.memberRepository.saveAll(listMember);
		}
		
		if (Flag.flag) {
			Random random = new Random();
			this.memberRepository.findAll().forEach(member -> {
				System.out.println(">>>>> " + member);
				IntStream.rangeClosed(1, 1 + random.nextInt(5)).forEach(idx -> {
					Profile profile = new Profile();
					profile.setFname("filename" + idx + ".doc");
					profile.setMember(member);
					this.profileRepository.save(profile);
				});
			});
		}
		
		if (Flag.flag) {
			System.out.println(">>>>> member.count() = " + this.memberRepository.count());
			System.out.println(">>>>> profile.count() = " + this.profileRepository.count());
		}
		
		if (Flag.flag) {
			List<Object[]> result = this.memberRepository.getMemberWithProfileCount();
			result.forEach(arr -> {
				System.out.println("2-1 >>>>> " + Arrays.toString(arr));
			});
		}
		
		if (Flag.flag) {
			List<Object[]> result = this.memberRepository.getMemberWithProfile();
			result.forEach(arr -> {
				System.out.println("2-2 >>>>> " + Arrays.toString(arr));
			});
		}
		
		if (Flag.flag) {
			List<Object[]> result = this.memberRepository.getMemberWithProfileByUid("uid10");
			result.forEach(arr -> {
				System.out.println("2-3 >>>>> " + Arrays.toString(arr));
			});
		}
	}

	/*
	 * @OneToMany
	 */
	@Autowired
	private PdsBoardRepository pdsBoardRepository;

	private void job03() {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			PdsFile file1 = new PdsFile();
			file1.setFname("file1.doc");
			PdsFile file2 = new PdsFile();
			file2.setFname("file2.doc");
			
			PdsBoard pdsBoard = new PdsBoard();
			pdsBoard.setTitle("Document");
			pdsBoard.setFiles(Arrays.asList(file1, file2));
			
			this.pdsBoardRepository.save(pdsBoard);
		}
		
		if (Flag.flag) {
			int count = this.pdsBoardRepository.updatePdsFile(2L, "updateFile.doc");
			System.out.println(">>>>> count = " + count);
		}
		
		if (!Flag.flag) {
			String newFname = "updateFile2.doc";
			
			Optional<PdsBoard> result = this.pdsBoardRepository.findById(1L);
			result.ifPresent(board -> {
				PdsFile file = new PdsFile();
				file.setId(2L);
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
		
		if (Flag.flag) {
			int count = this.pdsBoardRepository.deletePdsFile(2L);
			System.out.println(">>>>> count = " + count);
		}
		
		if (Flag.flag) {
			List<PdsBoard> list = new ArrayList<>();
			
			IntStream.rangeClosed(1, 10).forEach(index -> {
				PdsBoard board = new PdsBoard();
				board.setTitle("자료-" + index);
				
				PdsFile file1 = new PdsFile();
				file1.setFname("file1-" + index + ".doc");
				PdsFile file2 = new PdsFile();
				file2.setFname("file2-" + index + ".doc");
				board.setFiles(Arrays.asList(file1, file2));
				
				list.add(board);
			});
			
			this.pdsBoardRepository.saveAll(list);
		}
		
		if (Flag.flag) {
			this.pdsBoardRepository.getSummary().forEach(arr -> {
				System.out.println("6 >>>>> " + Arrays.toString(arr));
			});
		}
		
		if (Flag.flag) {
		}
		
		if (Flag.flag) {
		}
		
		if (Flag.flag) {
		}
	}

	/*
	 * BiDirectional
	 */
	
	@Autowired
	private KBoardRepository kboardRepository;
	
	@Autowired
	private KReplyRepository kreplyRepository;

	private void job04() {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			List<KBoard> listKBoard = new ArrayList<>();
			
			IntStream.rangeClosed(1, 10).forEach(index -> {
				KBoard kboard = new KBoard();
				kboard.setTitle("Free board..." + index);
				kboard.setContent("Free content....." + index);
				kboard.setWriter("user" + (index % 3));
				
				listKBoard.add(kboard);
			});
			this.kboardRepository.saveAll(listKBoard);
		}
		
		if (Flag.flag) {
			KBoard kboard = new KBoard();
			kboard.setId(10L);
			
			KReply kreply = new KReply();
			kreply.setReply("REPLY for 10...............");
			kreply.setReplyer("replyer10");
			kreply.setKboard(kboard);
			
			this.kreplyRepository.save(kreply);
		}
		
		if (!Flag.flag) {
			Optional<KBoard> result = this.kboardRepository.findById(10L);
			result.ifPresent(board -> {
				KReply kreply = new KReply();
				kreply.setReply("REPLY ......................");
				kreply.setReplyer("replyer00");
				kreply.setKboard(board);
				
				List<KReply> kreplies = board.getKreplies();
				kreplies.add(kreply);                     // TODO: ERROR
				
				board.setKreplies(kreplies);
				this.kboardRepository.save(board);
			});
		}
		
		if (!Flag.flag) {
			Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
			this.kboardRepository.findByIdGreaterThan(0L, pageable).forEach(board -> {
				System.out.println("4 >>>>> " + board + " : " + board.getKreplies().size());
				//System.out.println("4 >>>>> " + board);
			});
		}
		
		if (Flag.flag) {
			Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
			this.kboardRepository.getPage(pageable).forEach(arr -> {
				System.out.println("4 >>>>> " + Arrays.asList(arr));
			});
		}
		
		if (Flag.flag) {
		}
		
		if (Flag.flag) {
		}
	}

	private void job05() {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
	}

	private void job06() {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
	}

	private void job07() {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
	}

	private void job08() {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
	}

	private void job09() {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
	}

	private void job10() {
		log.info("KANG-20200719 >>>>> {} {}", CurrentInfo.get());
		
		Runtime runtime = Runtime.getRuntime();
		final NumberFormat format = NumberFormat.getInstance();
		
		final long maxMemory = runtime.maxMemory();
		final long allocatedMemory = runtime.totalMemory();
		final long freeMemory = runtime.freeMemory();
		final long mb = 1024 * 1024;
		final String mega = " MB";
		
		log.info("========================== Memory Info (MB) ==========================");
		log.info("Free memory       : " + format.format(freeMemory / mb) + mega);
		log.info("Allocated memory  : " + format.format(allocatedMemory / mb) + mega);
		log.info("Max memory        : " + format.format(maxMemory / mb) + mega);
		log.info("Total free memory : " + format.format((freeMemory + (maxMemory - allocatedMemory)) / mb) + mega);
		log.info("=================================================================");
		log.info(" -Xms128m -Xmx128m");
		log.info(" -Xms512m -Xmx512m");
		log.info(" -Xms1024m -Xmx1024m");
	}
}
