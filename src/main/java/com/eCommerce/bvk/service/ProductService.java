package com.eCommerce.bvk.service;

import com.eCommerce.bvk.entity.Product;

import java.util.List;

public interface ProductService {

    void saveNewProduct(Product param);

    List<Product> getAll();

    Product getProductById(Long id);

    Product updateProduct(Long id, Product param);

    void deleteProduct(Long id);
}
