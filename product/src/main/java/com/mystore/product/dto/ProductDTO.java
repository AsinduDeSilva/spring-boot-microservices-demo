package com.mystore.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    private int id;
    private int productId;
    private String productName;
    private String description;
    private Boolean forSale;
}
