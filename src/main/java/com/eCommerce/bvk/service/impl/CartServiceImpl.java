package com.eCommerce.bvk.service.impl;

import com.eCommerce.bvk.dto.CartRequestDto;
import com.eCommerce.bvk.dto.CartTotalPriceResponseDto;
import com.eCommerce.bvk.entity.Cart;
import com.eCommerce.bvk.entity.Checkout;
import com.eCommerce.bvk.entity.Product;
import com.eCommerce.bvk.entity.User;
import com.eCommerce.bvk.repository.CartRepository;
import com.eCommerce.bvk.repository.CheckoutRepository;
import com.eCommerce.bvk.repository.ProductRepository;
import com.eCommerce.bvk.repository.UserRepository;
import com.eCommerce.bvk.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final CheckoutRepository checkoutRepository;

    @Override
    public void addSelectedProductToCart(CartRequestDto param) {

        Cart cart = new Cart();

        User user = userRepository.findById(param.getUserId()).orElse(null);
        cart.setUserId(user);
        Product product = productRepository.findById(param.getProductId()).orElse(null);
        cart.setProductId(product);
        assert product != null;
        cart.setAmount(product.getPrice());
        cart.setTotalPrice(product.getPrice() * param.getQuantity());
        cart.setIsCancel(0);

        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllProductsInCart(Long userid) {

        User user = userRepository.findById(userid).orElse(null);
        List<Cart> allByUserId = cartRepository.findAllByUserId(user);
        System.out.println("allByUserId = " + allByUserId);
        return allByUserId;
    }

    @Override
    public CartTotalPriceResponseDto getFinalizeTotalPrice(Long userid) {

        List<Cart> allProductsInCart = this.getAllProductsInCart(userid);

        int totalPrice = 0;

        for (Cart cart : allProductsInCart) {
            totalPrice = totalPrice + cart.getTotalPrice();
        }

        CartTotalPriceResponseDto c = new CartTotalPriceResponseDto();
        c.setUserId(userid);
        c.setTotalPrice(totalPrice);
        c.setTransactionId(UUID.randomUUID().toString());

        Checkout checkout = new Checkout();

        User user = userRepository.findById(userid).orElse(null);
        checkout.setUserId(user);
        checkout.setTotalPrice(totalPrice);
        checkout.setTransactionId(UUID.randomUUID().toString());

        checkoutRepository.save(checkout);

        return c;
    }

    @Override
    public void deleteProductInCart(Long id) {
        cartRepository.deleteById(id);
    }
}
