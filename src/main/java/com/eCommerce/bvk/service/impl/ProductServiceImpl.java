package com.eCommerce.bvk.service.impl;

import com.eCommerce.bvk.entity.Product;
import com.eCommerce.bvk.repository.ProductRepository;
import com.eCommerce.bvk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void saveNewProduct(Product param) {
        param.setCreatedBy("admin");
        param.setIsDeleted(0);
        Date today = Date.from(Instant.now());
        param.setCreatedDate(today);
        productRepository.save(param);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(Long id, Product param) {
        Product product = productRepository.findById(id).get();

        // change value
        product.setIsDeleted(0);
        product.setName(param.getName());
        product.setBrand(param.getBrand());
        product.setQuantity(param.getQuantity());
        product.setCode(param.getCode());
        product.setImage(param.getImage());
        product.setExpDate(param.getExpDate());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
