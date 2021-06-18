package com.softgen.softgentask.service;

import com.softgen.softgentask.entity.Product;
import com.softgen.softgentask.entity.Supplier;
import com.softgen.softgentask.exception.ProductNotFoundException;
import com.softgen.softgentask.exception.SupplierNotFoundException;
import com.softgen.softgentask.repository.ProductRepository;
import com.softgen.softgentask.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> saveSuppliers(List<Supplier> suppliers) {
        return supplierRepository.saveAll(suppliers);
    }

    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(int id) throws SupplierNotFoundException {
        if (supplierRepository.findById(id).isPresent())
            return supplierRepository.findById(id).get();
        else
            throw new SupplierNotFoundException();
    }

    public Supplier getSupplierByName(String name) {
        return supplierRepository.findSupplierByName(name);
    }

    public Supplier getFirstSupplierByName(String name) {
        return supplierRepository.findFirstByName(name);
    }

    public String deleteSupplier(int id) throws ProductNotFoundException {
        List<Product> existingProducts = productRepository.findAllBySupplierId(id);
        if (!existingProducts.isEmpty()) {
            existingProducts.forEach(product -> product.setSupplierId(0));
            for (Product product :
                    existingProducts) {
                productService.updateProduct(product);
            }
        }
        supplierRepository.deleteById(id);
        return "Supplier removed " + id;
    }

    public Supplier updateSupplier(Supplier supplier) throws SupplierNotFoundException {
        Supplier existingSupplier;
        if (supplierRepository.findById(supplier.getId()).isPresent()) {
            existingSupplier = supplierRepository.findById(supplier.getId()).get();
            existingSupplier.setName(supplier.getName());
        } else {
            throw new SupplierNotFoundException();
        }

        return supplierRepository.save(existingSupplier);
    }
}
