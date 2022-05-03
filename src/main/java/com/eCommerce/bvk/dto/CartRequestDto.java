package com.eCommerce.bvk.dto;

import lombok.Data;

@Data
public class CartRequestDto {

    public Long userId;
    public Long productId;
    public Integer quantity;
}
