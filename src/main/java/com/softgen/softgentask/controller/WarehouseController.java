package com.softgen.softgentask.controller;

import com.softgen.softgentask.entity.Warehouse;
import com.softgen.softgentask.exception.WarehouseNotFoundException;
import com.softgen.softgentask.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService service;

    @PostMapping("/addWarehouse")
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
        return service.saveWarehouse(warehouse);
    }

    @PostMapping("/addWarehouses")
    public List<Warehouse> addWarehouses(@RequestBody List<Warehouse> warehouses) {
        return service.saveWarehouses(warehouses);
    }

    @GetMapping("/warehouses")
    public List<Warehouse> findAllWarehouses() {
        return service.getWarehouses();
    }

    @GetMapping("/warehouseById/{id}")
    public Warehouse findWarehouseById(@PathVariable int id) throws WarehouseNotFoundException {
        return service.getWarehouseById(id);
    }

    @GetMapping("/warehouseByName/{name}")
    public Warehouse findWarehouseByName(@PathVariable String name) {
        return service.getWarehouseByName(name);
    }

    @GetMapping("/firstWarehouseByName/{name}")
    public Warehouse findFirstWarehouseByName(@PathVariable String name) {
        return service.getFirstWarehouseByName(name);
    }

    @PutMapping("/updateWarehouse")
    public Warehouse updateWarehouse(@RequestBody Warehouse warehouse) throws WarehouseNotFoundException {
        return service.updateWarehouse(warehouse);
    }

    @DeleteMapping("/deleteWarehouse/{id}")
    public String deleteWarehouse(@PathVariable int id) {
        return service.deleteWarehouse(id);
    }
}

