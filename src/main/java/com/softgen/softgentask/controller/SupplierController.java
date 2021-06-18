package com.softgen.softgentask.controller;

import com.softgen.softgentask.entity.Supplier;
import com.softgen.softgentask.exception.ProductNotFoundException;
import com.softgen.softgentask.exception.SupplierNotFoundException;
import com.softgen.softgentask.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService service;

    @PostMapping("/addSupplier")
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return service.saveSupplier(supplier);
    }

    @PostMapping("/addSuppliers")
    public List<Supplier> addSuppliers(@RequestBody List<Supplier> suppliers) {
        return service.saveSuppliers(suppliers);
    }

    @GetMapping("/suppliers")
    public List<Supplier> findAllSuppliers() {
        return service.getSuppliers();
    }

    @GetMapping("/supplierById/{id}")
    public Supplier findSupplierById(@PathVariable int id) throws SupplierNotFoundException {
        return service.getSupplierById(id);
    }

    @GetMapping("/supplierByName/{name}")
    public Supplier findSupplierByName(@PathVariable String name) {
        return service.getSupplierByName(name);
    }

    @GetMapping("/firstSupplierByName/{name}")
    public Supplier findFirstSupplierByName(@PathVariable String name) {
        return service.getFirstSupplierByName(name);
    }

    @PutMapping("/updateSupplier")
    public Supplier updateSupplier(@RequestBody Supplier supplier) throws SupplierNotFoundException {
        return service.updateSupplier(supplier);
    }

    @DeleteMapping("/deleteSupplier/{id}")
    public String deleteSupplier(@PathVariable int id) throws ProductNotFoundException {
        return service.deleteSupplier(id);
    }
}

