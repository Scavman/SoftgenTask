package com.softgen.softgentask.exception;

public class FreightNotFoundException extends Exception {
    public FreightNotFoundException() {
        super("Freight does not exist.");
    }
}
