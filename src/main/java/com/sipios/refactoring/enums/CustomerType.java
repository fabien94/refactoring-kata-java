package com.sipios.refactoring.enums;

public enum CustomerType {
	
	STANDARD_CUSTOMER("STANDARD_CUSTOMER"),
	PREMIUM_CUSTOMER("PREMIUM_CUSTOMER"),
	PLATINUM_CUSTOMER("PLATINUM_CUSTOMER");

    private final String label;

    CustomerType(final String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
