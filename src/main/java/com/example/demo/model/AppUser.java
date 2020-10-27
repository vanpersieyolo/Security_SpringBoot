package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AppUser {
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String userPassword;
    @ManyToOne
    private AppRole role;
}
