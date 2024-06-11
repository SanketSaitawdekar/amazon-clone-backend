package com.sanket.amazonclone.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "user_details")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    @Column(columnDefinition = "CHAR(1) DEFAULT 'N'")
    private String is_deleted = "N";


}
