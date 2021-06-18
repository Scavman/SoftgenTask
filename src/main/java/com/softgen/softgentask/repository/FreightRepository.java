package com.softgen.softgentask.repository;

import com.softgen.softgentask.entity.Freight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreightRepository extends JpaRepository<Freight, Integer> {
    Freight findFreightByProductId(int id);
}
