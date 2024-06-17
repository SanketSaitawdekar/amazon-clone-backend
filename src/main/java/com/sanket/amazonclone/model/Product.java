package com.sanket.amazonclone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_master")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    private String product_name;

    private int product_category_id;
    private float product_price;
    @Column(columnDefinition = "CHAR(2) DEFAULT 'N'")
    private String is_deleted = "N";

}
