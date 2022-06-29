package com.sipios.refactoring.enums;

public enum ItemType {
	
	TSHIRT("TSHIRT"),
	DRESS("DRESS"),
	JACKET("JACKET");

    private final String label;

    ItemType(final String label) {
        this.label = label;
    }
    
    public String getlabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return label;
    }
}
