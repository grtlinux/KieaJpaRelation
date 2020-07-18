package org.tain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.domain.Member;
import org.tain.domain.Profile;
import org.tain.repository.MemberRepository;
import org.tain.repository.ProfileRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaJpaRelation01ManyToOne01Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaJpaRelation01ManyToOne01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		if (Flag.flag) job01();
		if (Flag.flag) job02();
		if (Flag.flag) job03();
		if (Flag.flag) job04();
		if (Flag.flag) job05();
	}

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	private void job01() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		IntStream.range(1, 101).forEach(index -> {
			Member member = new Member();
			member.setUid("user" + index);
			member.setUname("사용자-" + index);
			
			this.memberRepository.save(member);
		});
	}

	private void job02() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		Member member = new Member();
		member.setUid("user1");
		
		for (int i=1; i < 5; i++) {
			Profile profile = new Profile();
			profile.setFname("fname" + i + ".jpg");
			if (i == 1)
				profile.setFlag(true);
			else
				profile.setFlag(false);
			profile.setMember(member);
			
			this.profileRepository.save(profile);
		}
	}

	private void job03() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
		
		List<Object[]> result = this.memberRepository.getMemberWithProfileCount("user1");
		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
		
		result = this.memberRepository.getMemberWithProfile("user1");
		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
	}

	private void job04() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
	}

	private void job05() {
		log.info("KANG-20200718 >>>>> {} {}", CurrentInfo.get());
	}
}
