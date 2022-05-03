package com.eCommerce.bvk.service;

import com.eCommerce.bvk.dto.CartRequestDto;
import com.eCommerce.bvk.dto.CartTotalPriceResponseDto;
import com.eCommerce.bvk.entity.Cart;

import java.util.List;

public interface CartService {
    void addSelectedProductToCart(CartRequestDto param);

    List<Cart> getAllProductsInCart(Long userid);

    CartTotalPriceResponseDto getFinalizeTotalPrice(Long userid);

    void deleteProductInCart(Long id);
}
