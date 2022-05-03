package com.eCommerce.bvk.controller;

import com.eCommerce.bvk.common.BaseResponse;
import com.eCommerce.bvk.common.CommonCode;
import com.eCommerce.bvk.common.CommonMessage;
import com.eCommerce.bvk.entity.Product;
import com.eCommerce.bvk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    //create data product
    @PostMapping(value = "/add")
    public BaseResponse addNewProduct(@RequestBody Product param){
        productService.saveNewProduct(param);
        return new BaseResponse(CommonMessage.SAVED, CommonCode.SUCCESS);
    }

    //get all products
    @GetMapping(value = "/getAllProducts")
    public BaseResponse getAllProducts(){
        List<Product> productList = productService.getAll();
        return new BaseResponse(CommonMessage.FOUND, CommonCode.SUCCESS, productList);
    }

    //get product by id
    @GetMapping(value = "/getProductById/{id}")
    public BaseResponse getById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        return new BaseResponse(CommonMessage.FOUND, CommonCode.SUCCESS, product);
    }

    //update product
    @PutMapping(value = "/updateProduct/{id}")
    public BaseResponse updateProduct(@PathVariable("id") Long id, @RequestBody Product param){
        Product product = productService.updateProduct(id, param);
        return new BaseResponse(CommonMessage.UPDATED, CommonCode.SUCCESS, product);
    }

    //delete product
    @DeleteMapping(value = "/deleteProduct/{id}")
    public BaseResponse deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new BaseResponse(CommonMessage.DELETED, CommonCode.SUCCESS);
    }
}
