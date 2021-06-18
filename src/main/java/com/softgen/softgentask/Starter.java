package com.softgen.softgentask;

import com.softgen.softgentask.controller.FreightController;
import com.softgen.softgentask.controller.ProductController;
import com.softgen.softgentask.controller.SupplierController;
import com.softgen.softgentask.controller.WarehouseController;
import com.softgen.softgentask.entity.Freight;
import com.softgen.softgentask.entity.Product;
import com.softgen.softgentask.entity.Supplier;
import com.softgen.softgentask.entity.Warehouse;
import com.softgen.softgentask.exception.FreightException;
import com.softgen.softgentask.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Starter {

    @Autowired
    private ProductController productController;
    @Autowired
    private SupplierController supplierController;
    @Autowired
    private WarehouseController warehouseController;
    @Autowired
    private FreightController freightController;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void start() {
        Supplier supplier1 = new Supplier();
        Supplier supplier2 = new Supplier();
        Supplier supplier3 = new Supplier();
        Supplier supplier4 = new Supplier();
        List<Supplier> suppliers = new ArrayList<>();
        supplier1.setName("BookSupplier");
        suppliers.add(supplier1);
        supplier2.setName("MeatSupplier");
        suppliers.add(supplier2);
        supplier3.setName("FurnitureSupplier");
        suppliers.add(supplier3);
        supplier4.setName("BestSupplier");
        supplierController.addSuppliers(suppliers);
        supplierController.addSupplier(supplier4);

        Warehouse warehouse1 = new Warehouse();
        Warehouse warehouse2 = new Warehouse();
        Warehouse warehouse3 = new Warehouse();
        Warehouse warehouse4 = new Warehouse();
        List<Warehouse> warehouses = new ArrayList<>();
        warehouse1.setName("BookWarehouse");
        warehouses.add(warehouse1);
        warehouse2.setName("MeatWarehouse");
        warehouses.add(warehouse2);
        warehouse3.setName("FurnitureWarehouse");
        warehouses.add(warehouse3);
        warehouse4.setName("BestWarehouse");
        warehouseController.addWarehouses(warehouses);
        warehouseController.addWarehouse(warehouse4);

        Product product1 = new Product();
        product1.setName("Beef");
        product1.setAmount(50);
        product1.setWarehouseId(warehouseController.findFirstWarehouseByName("MeatWarehouse").getId());
        product1.setSupplierId(supplierController.findFirstSupplierByName("MeatSupplier").getId());
        productController.addProduct(product1);

        Product product2 = new Product();
        product2.setName("Process");
        product2.setAmount(50);
        product2.setWarehouseId(warehouseController.findFirstWarehouseByName("BookWarehouse").getId());
        product2.setSupplierId(supplierController.findFirstSupplierByName("BookSupplier").getId());
        productController.addProduct(product2);

        Product product3 = new Product();
        product3.setName("Chair");
        product3.setAmount(50);
        product3.setWarehouseId(warehouseController.findFirstWarehouseByName("FurnitureWarehouse").getId());
        product3.setSupplierId(supplierController.findFirstSupplierByName("FurnitureSupplier").getId());
        productController.addProduct(product3);

        Freight freight1 = new Freight();
        freight1.setAmount(49);
        freight1.setFromWarehouseId(warehouseController.findFirstWarehouseByName("MeatWarehouse").getId());
        freight1.setToWarehouseId(warehouseController.findFirstWarehouseByName("BestWarehouse").getId());
        freight1.setProductId(productController.findFirstProductByName("Beef").getId());
        try {
            freightController.addFreight(freight1);
        } catch (FreightException | ProductNotFoundException e) {
            logger.error(e.getMessage());
        }

        Freight freight2 = new Freight();
        freight2.setAmount(50);
        freight2.setFromWarehouseId(warehouseController.findFirstWarehouseByName("BookWarehouse").getId());
        freight2.setToWarehouseId(warehouseController.findFirstWarehouseByName("BestWarehouse").getId());
        freight2.setProductId(productController.findFirstProductByName("Process").getId());
        try {
            freightController.addFreight(freight2);
        } catch (FreightException | ProductNotFoundException e) {
            logger.error(e.getMessage());
        }

        Freight freight3 = new Freight();
        freight3.setAmount(51);
        freight3.setFromWarehouseId(warehouseController.findFirstWarehouseByName("FurnitureWarehouse").getId());
        freight3.setToWarehouseId(warehouseController.findFirstWarehouseByName("BestWarehouse").getId());
        freight3.setProductId(productController.findFirstProductByName("Chair").getId());
        try {
            freightController.addFreight(freight3);
        } catch (FreightException | ProductNotFoundException e) {
            logger.error(e.getMessage());
        }
    }
}
