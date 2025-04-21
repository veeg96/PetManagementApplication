package com.petmgmt.PetManagement.dto;

import com.petmgmt.PetManagement.enums.Gender;
import com.petmgmt.PetManagement.enums.PetType;
import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)// use those variables for overiding hashcode adnd equals
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private Gender gender;
    private PetType type;
    private OwnerDTO ownerDTO;
}
