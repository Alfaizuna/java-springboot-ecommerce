package com.eCommerce.bvk.repository;

import com.eCommerce.bvk.entity.Cart;
import com.eCommerce.bvk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUserId(User user);

}
