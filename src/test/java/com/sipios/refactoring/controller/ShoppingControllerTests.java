package com.sipios.refactoring.controller;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.models.Body;
import com.sipios.refactoring.models.Item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class ShoppingControllerTests extends UnitTest {

    @InjectMocks
    private ShoppingController controller;

    @Test
    void should_not_throw() {
        Assertions.assertDoesNotThrow(
            () -> controller.getPrice(new Body(new Item[] {}, "STANDARD_CUSTOMER"))
        );
    }
    
    @Test
    void should_equal_mock_standard_customer() {
    	Item[] items = new Item[3];
    	items[0] = new Item("TSHIRT", 1);
    	items[1] = new Item("DRESS", 1);
    	items[2] = new Item("JACKET", 1);
        Assertions.assertEquals(controller.getPrice(new Body(items, "STANDARD_CUSTOMER")), "180.0");
    }
    
    @Test
    void should_equal_mock_premium_customer() {
    	Item[] items = new Item[3];
    	items[0] = new Item("TSHIRT", 2);
    	items[1] = new Item("DRESS", 2);
    	items[2] = new Item("JACKET", 2);
        Assertions.assertEquals(controller.getPrice(new Body(items, "PREMIUM_CUSTOMER")), "324.0");
    }
    
    @Test
    void should_equal_mock_platinum_customer() {
    	Item[] items = new Item[3];
    	items[0] = new Item("TSHIRT", 2);
    	items[1] = new Item("DRESS", 3);
    	items[2] = new Item("JACKET", 2);
        Assertions.assertEquals(controller.getPrice(new Body(items, "PLATINUM_CUSTOMER")), "205.0");
    }
}
