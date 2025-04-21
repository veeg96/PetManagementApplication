package com.petmgmt.PetManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table
@Entity
public class WildPet extends Pet{
    @Column(name = "place_of_birth" ,nullable = false)
    private String birthPlace;
}
