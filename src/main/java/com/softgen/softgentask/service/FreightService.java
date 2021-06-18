package com.softgen.softgentask.service;

import com.softgen.softgentask.entity.Freight;
import com.softgen.softgentask.entity.Product;
import com.softgen.softgentask.exception.FreightException;
import com.softgen.softgentask.exception.FreightNotFoundException;
import com.softgen.softgentask.exception.ProductNotFoundException;
import com.softgen.softgentask.repository.FreightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreightService {
    @Autowired
    private FreightRepository freightRepository;
    @Autowired
    private ProductService productService;

    public Freight saveFreight(Freight freight) throws FreightException, ProductNotFoundException {
        Product product = productService.getProductById(freight.getProductId());
        if (freight.getAmount() < product.getAmount()) {
            int tempAmount = product.getAmount();
            int tempWId = product.getWarehouseId();
            product.setAmount(freight.getAmount());
            product.setWarehouseId(freight.getToWarehouseId());
            productService.transferProduct(product);
            product.setAmount(tempAmount - freight.getAmount());
            product.setWarehouseId(tempWId);
            productService.updateProduct(product);
        } else if (freight.getAmount() == product.getAmount()) {
            product.setAmount(freight.getAmount());
            product.setWarehouseId(freight.getToWarehouseId());
            productService.transferProduct(product);
            productService.deleteProduct(product.getId());
        } else {
            throw new FreightException();
        }
        return freightRepository.save(freight);
    }

    public List<Freight> saveFreights(List<Freight> freights) {
        return freightRepository.saveAll(freights);
    }

    public List<Freight> getFreights() {
        return freightRepository.findAll();
    }

    public Freight getFreightById(int id) throws FreightNotFoundException {
        if (freightRepository.findById(id).isPresent())
            return freightRepository.findById(id).get();
        else
            throw new FreightNotFoundException();
    }

    public String deleteFreight(int id) {
        freightRepository.deleteById(id);
        return "Freight removed " + id;
    }

    public Freight updateFreight(Freight freight) throws FreightNotFoundException {
        Freight existingFreight;
        if (freightRepository.findById(freight.getId()).isPresent()) {
            existingFreight = freightRepository.findById(freight.getId()).get();
            existingFreight.setProductId(freight.getProductId());
            existingFreight.setAmount(freight.getAmount());
            existingFreight.setFromWarehouseId(freight.getFromWarehouseId());
            existingFreight.setToWarehouseId(freight.getToWarehouseId());
        } else {
            throw new FreightNotFoundException();
        }
        return freightRepository.save(existingFreight);
    }
}
