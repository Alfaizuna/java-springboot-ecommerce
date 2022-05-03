package com.eCommerce.bvk.controller;

import com.eCommerce.bvk.common.BaseResponse;
import com.eCommerce.bvk.common.CommonCode;
import com.eCommerce.bvk.common.CommonMessage;
import com.eCommerce.bvk.dto.CartRequestDto;
import com.eCommerce.bvk.dto.CartTotalPriceResponseDto;
import com.eCommerce.bvk.entity.Cart;
import com.eCommerce.bvk.service.CartService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    CartService cartService;

    // add selected product to cart
    @PostMapping(value = "/addProductToCart")
    public BaseResponse addProductToItem(@RequestBody CartRequestDto param){
        try {
            if(Objects.nonNull(param)){
                cartService.addSelectedProductToCart(param);
                return new BaseResponse(CommonMessage.SAVED, CommonCode.SUCCESS);
            }else{
                return null;
            }
        }catch (Exception e){
            return new BaseResponse(CommonMessage.NOT_SAVED, CommonCode.BAD_REQUEST);
        }
    }

    //get list product in cart
    @GetMapping(value = "/getListProductsInCart/{userid}")
    public BaseResponse getListProductsInCart(@PathVariable("userid") Long userid){
        try {
            List<Cart> allProductsInCart = cartService.getAllProductsInCart(userid);
            return new BaseResponse(CommonMessage.FOUND, CommonCode.SUCCESS, allProductsInCart);
        }catch (Exception e){
            return new BaseResponse(CommonMessage.NOT_FOUND, CommonCode.BAD_REQUEST);
        }
    }

    // finalize the total price of cart
    @PostMapping(value = "/finalizeTotalPrice/{userid}")
    public BaseResponse finalizeTotalPrice(@PathVariable("userid") Long userid){
        try {
            CartTotalPriceResponseDto getFinalizeTotalPrice = cartService.getFinalizeTotalPrice(userid);
            return new BaseResponse(CommonMessage.FOUND, CommonCode.SUCCESS, getFinalizeTotalPrice);
        }catch (Exception e){
            return new BaseResponse(CommonMessage.NOT_FOUND, CommonCode.BAD_REQUEST);
        }
    }

    //remove item from cart
    @DeleteMapping(value = "/deleteProductInCart/{id}")
    public BaseResponse deleteProductInCart(@PathVariable("id") Long id){
        cartService.deleteProductInCart(id);
        return new BaseResponse(CommonMessage.DELETED, CommonCode.SUCCESS);
    }
}
