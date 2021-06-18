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
@Table(name = "FREIGHT")
public class Freight {

    @Id
    @GeneratedValue
    @Getter
    private int id;
    @NonNull
    @Getter
    @Setter
    private int productId;
    @NonNull
    @Getter
    @Setter
    private int fromWarehouseId;
    @NonNull
    @Getter
    @Setter
    private int toWarehouseId;
    @NonNull
    @Getter
    @Setter
    private int amount;
    @NonNull
    @Getter
    private final Date freightDate = new Date();
}