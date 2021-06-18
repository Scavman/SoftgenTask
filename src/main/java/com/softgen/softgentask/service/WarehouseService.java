package com.softgen.softgentask.service;

import com.softgen.softgentask.entity.Product;
import com.softgen.softgentask.entity.Warehouse;
import com.softgen.softgentask.exception.WarehouseNotFoundException;
import com.softgen.softgentask.repository.ProductRepository;
import com.softgen.softgentask.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> saveWarehouses(List<Warehouse> warehouses) {
        return warehouseRepository.saveAll(warehouses);
    }

    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(int id) throws WarehouseNotFoundException {
        if (warehouseRepository.findById(id).isPresent())
            return warehouseRepository.findById(id).get();
        else
            throw new WarehouseNotFoundException();
    }

    public Warehouse getWarehouseByName(String name) {
        return warehouseRepository.findWarehouseByName(name);
    }

    public Warehouse getFirstWarehouseByName(String name) {
        return warehouseRepository.findFirstByName(name);
    }

    public String deleteWarehouse(int id) {
        List<Product> existingProducts = productRepository.findAllByWarehouseId(id);
        if (!existingProducts.isEmpty()) {
            for (Product product :
                    existingProducts) {
                productService.deleteProduct(product.getId());
            }
        }
        warehouseRepository.deleteById(id);
        return "Warehouse removed " + id;
    }

    public Warehouse updateWarehouse(Warehouse warehouse) throws WarehouseNotFoundException {
        Warehouse existingWarehouse;
        if (warehouseRepository.findById(warehouse.getId()).isPresent()) {
            existingWarehouse = warehouseRepository.findById(warehouse.getId()).get();
            existingWarehouse.setName(warehouse.getName());
        } else {
            throw new WarehouseNotFoundException();
        }

        return warehouseRepository.save(existingWarehouse);
    }


}
