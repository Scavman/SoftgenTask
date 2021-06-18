package com.softgen.softgentask.repository;

import com.softgen.softgentask.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Warehouse findWarehouseByName(String name);

    Warehouse findFirstByName(String name);
}
