package org.tain.info;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerInfo {

	@Value("#{target.firstName} #{target.lastName}")
	String getName();
	
	String getCity();
	
	String getCountry();

	@Value("#{target.order.orderCode}")
	String getOrderCode();
}
