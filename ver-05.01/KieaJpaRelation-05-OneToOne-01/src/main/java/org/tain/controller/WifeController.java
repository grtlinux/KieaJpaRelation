package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tain.info.RemodelingWifeInfo;
import org.tain.info.WifeInfo;
import org.tain.repository.WifeRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WifeController {

	// http://localhost:8080/v0.1/api
	// http://localhost:8080/v0.1/api/wifes
	// http://localhost:8080/v0.1/api/husbands
	
	@Autowired
	private WifeRepository wifeRepository;
	
	// http://localhost:8080/v0.1/findwife?firstname=Mary
	@GetMapping(value = {"/findwife"})
	public WifeInfo getWifeInfo(@RequestParam("firstname") String firstName) {
		WifeInfo wifeInfo = this.wifeRepository.findWifeByFirstName(firstName);
		log.info(">>>>>> WifeInfo: {}, {}, {}"
				, wifeInfo.getFirstName()
				, wifeInfo.getLastName()
				, wifeInfo.getAge());
		return wifeInfo;
	}
	
	// http://localhost:8080/v0.1/findremodelwife?firstname=Lauren
	@GetMapping(value = {"/findremodelwife"})
	public RemodelingWifeInfo getRemodelingWifeInfo(@RequestParam("firstname") String firstName) {
		RemodelingWifeInfo wifeInfo = this.wifeRepository.findRemodelingWifeByFirstName(firstName);
		log.info(">>>>>> RemodelingWifeInfo: {}, {}, {}"
				, wifeInfo.getName()
				, wifeInfo.getAge()
				, wifeInfo.getHusband());
		return wifeInfo;
	}
}
