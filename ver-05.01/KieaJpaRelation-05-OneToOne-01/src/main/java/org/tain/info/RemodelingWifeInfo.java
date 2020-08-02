package org.tain.info;

import org.springframework.beans.factory.annotation.Value;

public interface RemodelingWifeInfo {

	@Value("#{target.firstName} #{target.lastName}")
	String getName();
	
	int getAge();
	
	@Value("#{target.husband.firstName} #{target.husband.lastName}")
	String getHusband();
}
