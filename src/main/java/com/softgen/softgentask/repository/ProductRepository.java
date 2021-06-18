package com.softgen.softgentask.repository;

import com.softgen.softgentask.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    Product findFirstByName(String name);

    List<Product> findAllBySupplierId(int id);

    List<Product> findAllByWarehouseId(int id);
}