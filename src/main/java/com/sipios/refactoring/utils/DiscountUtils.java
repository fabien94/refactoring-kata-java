package com.sipios.refactoring.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.sipios.refactoring.enums.CustomerType;

public class DiscountUtils {
	public static double computeCustomerDiscount(String customerType) {
		CustomerType ct = CustomerType.valueOf(customerType);
		double discount = 0;
		switch (ct) {
		case STANDARD_CUSTOMER: discount = 1;break;
		case PREMIUM_CUSTOMER: discount = 0.9;break;
		case PLATINUM_CUSTOMER: discount = 0.5;break;
		default: throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return discount;
	}
}
