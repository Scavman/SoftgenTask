package com.softgen.softgentask.exception;

public class SupplierNotFoundException extends Exception {
    public SupplierNotFoundException() {
        super("Supplier does not exist.");
    }
}
