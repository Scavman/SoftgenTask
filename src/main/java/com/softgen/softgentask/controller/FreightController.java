package com.softgen.softgentask.controller;

import com.softgen.softgentask.entity.Freight;
import com.softgen.softgentask.exception.FreightException;
import com.softgen.softgentask.exception.FreightNotFoundException;
import com.softgen.softgentask.exception.ProductNotFoundException;
import com.softgen.softgentask.service.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FreightController {

    @Autowired
    private FreightService service;

    @PostMapping("/addFreight")
    public Freight addFreight(@RequestBody Freight freight) throws FreightException, ProductNotFoundException {
        return service.saveFreight(freight);
    }

    @PostMapping("/addFreights")
    public List<Freight> addFreights(@RequestBody List<Freight> freights) {
        return service.saveFreights(freights);
    }

    @GetMapping("/freights")
    public List<Freight> findAllFreights() {
        return service.getFreights();
    }

    @GetMapping("/freightById/{id}")
    public Freight findFreightById(@PathVariable int id) throws FreightNotFoundException {
        return service.getFreightById(id);
    }

    @PutMapping("/updateFreight")
    public Freight updateFreight(@RequestBody Freight freight) throws FreightNotFoundException {
        return service.updateFreight(freight);
    }

    @DeleteMapping("/deleteFreight/{id}")
    public String deleteFreight(@PathVariable int id) {
        return service.deleteFreight(id);
    }
}

