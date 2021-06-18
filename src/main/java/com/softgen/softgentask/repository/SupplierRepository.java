package com.softgen.softgentask.repository;

import com.softgen.softgentask.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findSupplierByName(String name);

    Supplier findFirstByName(String name);
}
