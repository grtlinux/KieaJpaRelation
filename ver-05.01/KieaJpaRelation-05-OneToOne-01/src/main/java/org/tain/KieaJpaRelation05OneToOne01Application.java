package org.tain;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tain.domain.Husband;
import org.tain.domain.Wife;
import org.tain.repository.HusbandRepository;
import org.tain.repository.WifeRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KieaJpaRelation05OneToOne01Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200802 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaJpaRelation05OneToOne01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200802 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) job01();
		if (Flag.flag) job02();
		if (Flag.flag) job03();
		if (Flag.flag) job04();
		if (Flag.flag) job05();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private void job01() {
		log.info("KANG-20200802 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			
		}
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	@Autowired
	private WifeRepository wifeRepository;
	
	@Autowired
	private HusbandRepository husbandRepository;
	
	@Transactional
	private void job02() {
		log.info("KANG-20200802 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.wifeRepository.deleteAll();
			this.husbandRepository.deleteAll();
		}
		
		if (Flag.flag) {
			Wife mary = Wife.builder()
					.firstName("Mary")
					.lastName("Smith")
					.age(24)
					.build();
			Wife lauren = Wife.builder()
					.firstName("Lauren")
					.lastName("Hill")
					.age(32)
					.build();
			
			Husband peter = Husband.builder()
					.firstName("Peter")
					.lastName("Johnson")
					.age(18)
					.build();
			Husband phillip = Husband.builder()
					.firstName("Phillip")
					.lastName("Davis")
					.age(27)
					.build();
			
			mary.setHusband(peter);
			lauren.setHusband(phillip);
			
			this.wifeRepository.save(mary);
			this.wifeRepository.save(lauren);
		}
		
		if (Flag.flag) {
			List<Wife> wifes = this.wifeRepository.findAll();
			List<Husband> husbands = this.husbandRepository.findAll();
			
			System.out.println("===================Wifes:==================");
			//wifes.forEach(System.out::println);
			wifes.forEach(entity -> {
				System.out.println(">>>>> " + entity.toPrettyJson());
			});
			
			System.out.println("===================Husband:==================");
			//husbands.forEach(System.out::println);
			husbands.forEach(entity -> {
				System.out.println(">>>>> " + entity.toPrettyJson());
			});
		}
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private void job03() {
		log.info("KANG-20200802 >>>>> {} {}", CurrentInfo.get());
		
	}

	private void job04() {
		log.info("KANG-20200802 >>>>> {} {}", CurrentInfo.get());
		
	}

	private void job05() {
		log.info("KANG-20200802 >>>>> {} {}", CurrentInfo.get());
		
	}
}
