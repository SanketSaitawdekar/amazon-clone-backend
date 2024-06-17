package com.sanket.amazonclone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_category_master")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;
    private String category_name;
    @Column(columnDefinition = "CHAR(2) DEFAULT 'N'")
    private String is_deleted = "N";

}
