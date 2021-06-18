package com.softgen.softgentask.exception;

public class FreightException extends Exception {
    public FreightException() {
        super("Product amount is insufficient. Please, check the product amount in the " +
                "warehouse from which you wish to freight and enter a lower amount.");
    }
}
