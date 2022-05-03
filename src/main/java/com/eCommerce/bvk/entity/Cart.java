package com.eCommerce.bvk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
@Entity
public class Cart extends ModelBase{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "is_cancel")
    private Integer isCancel;
}
