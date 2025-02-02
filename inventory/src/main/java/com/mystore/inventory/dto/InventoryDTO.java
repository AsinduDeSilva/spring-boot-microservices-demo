package com.mystore.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InventoryDTO {
    private int id;
    private int itemId;
    private int productId;
    private int quantity;
}
