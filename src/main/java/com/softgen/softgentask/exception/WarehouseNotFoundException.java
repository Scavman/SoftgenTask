package com.softgen.softgentask.exception;

public class WarehouseNotFoundException extends Exception {
    public WarehouseNotFoundException() {
        super("Warehouse does not exist.");
    }
}
