package com.petmgmt.PetManagement.dto;

import com.petmgmt.PetManagement.enums.Gender;
import com.petmgmt.PetManagement.enums.PetType;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class DomesticPetDTO extends PetDTO{
    private LocalDate birthDate;

    @Builder
    public DomesticPetDTO(int id, String name, Gender gender, PetType type,OwnerDTO ownerDTO,LocalDate birthDate) {
        super(id,name,gender,type,ownerDTO);
        this.birthDate = birthDate;
    }
}
