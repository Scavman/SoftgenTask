package com.softgen.softgentask.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Getter
    @GeneratedValue
    private int id;
    @NonNull
    @Getter
    @Setter
    private String name;
    @NonNull
    @Getter
    @Setter
    private int supplierId;
    @NonNull
    @Getter
    @Setter
    private int warehouseId;
    @NonNull
    @Getter
    @Setter
    private int amount;
    @GeneratedValue
    @Getter
    private Date lastSuppliedDate = new Date();


    public void setLastSuppliedDate() {
        this.lastSuppliedDate = new Date();
    }

}