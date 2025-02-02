package com.mystore.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int itemId;
    private String orderDate;
    private int amount;
}
