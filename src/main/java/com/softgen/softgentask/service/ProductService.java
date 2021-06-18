package com.softgen.softgentask.service;

import com.softgen.softgentask.entity.Product;
import com.softgen.softgentask.exception.ProductNotFoundException;
import com.softgen.softgentask.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) throws ProductNotFoundException {
        if (productRepository.findById(id).isPresent())
            return productRepository.findById(id).get();
        else
            throw new ProductNotFoundException();
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product getFirstProductByName(String name) {
        return productRepository.findFirstByName(name);
    }


    public String deleteProduct(int id) {
        productRepository.deleteById(id);
        return "Product removed " + id;
    }

    public Product updateProduct(Product product) throws ProductNotFoundException {
        Product existingProduct;
        if (productRepository.findById(product.getId()).isPresent()) {
            existingProduct = productRepository.findById(product.getId()).get();
            existingProduct.setName(product.getName());
            existingProduct.setAmount(product.getAmount());
            existingProduct.setWarehouseId(product.getWarehouseId());
            if (product.getAmount() > existingProduct.getAmount())
                existingProduct.setLastSuppliedDate();
            return productRepository.save(existingProduct);
        } else {
            throw new ProductNotFoundException();
        }

    }

    public void transferProduct(Product product) throws ProductNotFoundException {
        Product existingProduct;
        if (productRepository.findById(product.getId()).isPresent())
            existingProduct = productRepository.findById(product.getId()).get();
        else
            throw new ProductNotFoundException();

        List<Product> warehouseStock = productRepository.findAllByWarehouseId(product.getWarehouseId());
        Optional<Product> productInWarehouse = warehouseStock.stream().filter(
                p -> p.getName().equals((existingProduct.getName()))).findFirst();
        if (productInWarehouse.isPresent()) {
            Product p = productInWarehouse.get();
            p.setAmount(p.getAmount() + product.getAmount());
        } else {
            Product newProduct = new Product();
            newProduct.setAmount(product.getAmount());
            newProduct.setSupplierId(product.getSupplierId());
            newProduct.setName(product.getName());
            newProduct.setWarehouseId(product.getWarehouseId());
            saveProduct(newProduct);
        }
    }

}
