package com.sipios.refactoring.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sipios.refactoring.models.Body;
import com.sipios.refactoring.models.Item;
import com.sipios.refactoring.utils.DiscountUtils;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    @PostMapping
    public String getPrice(@RequestBody Body b) {
    	
    	 if (b.getItems() == null) {
             return "0";
         }
    	 
        // 1. Compute discount for customer type
        final double d = DiscountUtils.computeCustomerDiscount(b.getType());

        // 2. Check if we are in in winter or summer discounts periods
        final boolean isDiscountPeriod = this.isDiscountPeriod();
        
        // 3. calculate the price
        double p = 0;
    	 for (int i = 0; i < b.getItems().length; i++) {
             Item it = b.getItems()[i];
          	p += DiscountUtils.computeItemPrice(it, d, isDiscountPeriod);       
    	 }
    	 
         // 4. Check if the calculate price is too high for a customer type
        checkHighPrice(b, p);

        return String.valueOf(p);
    }

	private void checkHighPrice(Body b, double p) {
		try {
            if (b.getType().equals("STANDARD_CUSTOMER")) {
                if (p > 200) {
                    throw new Exception("Price (" + p + ") is too high for standard customer");
                }
            } else if (b.getType().equals("PREMIUM_CUSTOMER")) {
                if (p > 800) {
                    throw new Exception("Price (" + p + ") is too high for premium customer");
                }
            } else if (b.getType().equals("PLATINUM_CUSTOMER")) {
                if (p > 2000) {
                    throw new Exception("Price (" + p + ") is too high for platinum customer");
                }
            } else {
                if (p > 200) {
                    throw new Exception("Price (" + p + ") is too high for standard customer");
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
	}
    
    /***
     * Return true if it's a discount period
     * else false
     * @return boolean
     */
	private boolean isDiscountPeriod() {
		 Date date = new Date();
	     Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
	     cal.setTime(date);
		return (
		    cal.get(Calendar.DAY_OF_MONTH) < 15 &&
		    cal.get(Calendar.DAY_OF_MONTH) > 5 &&
		    cal.get(Calendar.MONTH) == 5
		) ||
		(
		    cal.get(Calendar.DAY_OF_MONTH) < 15 &&
		    cal.get(Calendar.DAY_OF_MONTH) > 5 &&
		    cal.get(Calendar.MONTH) == 0
		);
	}
}
