package com.sipios.refactoring.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.sipios.refactoring.enums.CustomerType;
import com.sipios.refactoring.enums.ItemType;
import com.sipios.refactoring.models.Item;

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
	
	public static double computeItemPrice(Item item, double discount, boolean isDiscountPeriod) {
		ItemType it = ItemType.valueOf(item.getType());
		double normalPrice = 0;
		double price = 0;
		switch (it) {
			case TSHIRT: price = 30 * item.getNb() * discount;break;
			case DRESS: 
				normalPrice = 50 * item.getNb() * discount;
				price = isDiscountPeriod ?  normalPrice * 0.8 : normalPrice;
	    	break;
			case JACKET: 
				normalPrice = 100 * item.getNb() * discount;
				price = isDiscountPeriod ?  normalPrice * 0.9 : normalPrice;
	    	break;
			default: price = 0;
			break;
		}
		return price;
	}
}
