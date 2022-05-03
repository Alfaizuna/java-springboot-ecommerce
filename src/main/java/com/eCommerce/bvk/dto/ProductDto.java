package com.eCommerce.bvk.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Setter
@Getter
public class ProductDto {

    private String name;
    private String brand;
    private Integer quantity;
    private String code;
    private Double price;
    private String image;
    private Date expDate;
}
