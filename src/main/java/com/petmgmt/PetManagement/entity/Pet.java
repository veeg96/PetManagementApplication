package com.petmgmt.PetManagement.entity;

import com.petmgmt.PetManagement.enums.Gender;
import com.petmgmt.PetManagement.enums.PetType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name="pet_info")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)//cz wild n domestic extends pet
public class Pet extends Base{
    @Column(nullable = false)
    private String name;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PetType type;
    @OneToOne(mappedBy = "pet")
    private Owner owner;
}
