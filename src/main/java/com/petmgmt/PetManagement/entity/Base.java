package com.petmgmt.PetManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass //why?
public class Base {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
}
