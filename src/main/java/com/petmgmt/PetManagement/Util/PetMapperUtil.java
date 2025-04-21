package com.petmgmt.PetManagement.Util;

import com.petmgmt.PetManagement.dto.DomesticPetDTO;
import com.petmgmt.PetManagement.dto.OwnerDTO;
import com.petmgmt.PetManagement.dto.PetDTO;
import com.petmgmt.PetManagement.dto.WildPetDTO;
import com.petmgmt.PetManagement.entity.DomesticPet;
import com.petmgmt.PetManagement.entity.Owner;
import com.petmgmt.PetManagement.entity.Pet;
import com.petmgmt.PetManagement.entity.WildPet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PetMapperUtil {
    private static final String UNSUPPORTED_PET_INSTANCE="Unsupported pet Instance";
    public static PetDTO petToPetDTO(Pet pet){
        if (pet instanceof DomesticPet) {
            return convertDomesticPetToDomesticPetDTO((DomesticPet) pet);
        } else if(pet instanceof WildPet){
            return convertWildPetToWildPetDTO((WildPet) pet);
        } else
            throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE,pet.getClass()));
    }

    private static WildPetDTO convertWildPetToWildPetDTO(WildPet wildPet) {
        OwnerDTO ownerDTO = ownerToOwnerDTOWithOutPet(wildPet.getOwner());
        return WildPetDTO.builder().id(wildPet.getId())
                .name(wildPet.getName())
                .gender(wildPet.getGender())
                .type(wildPet.getType())
                .birthPlace(wildPet.getBirthPlace())
                .ownerDTO(ownerDTO)
                .build();
    }

    private static OwnerDTO ownerToOwnerDTOWithOutPet(Owner owner) {

        return OwnerDTO.builder().id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .gender(owner.getGender())
                .city(owner.getCity())
                .state(owner.getState())
                .mobileNumber(owner.getMobileNumber())
                .emailId(owner.getEmailId())
                .build();
    }

    private static DomesticPetDTO convertDomesticPetToDomesticPetDTO(DomesticPet domesticPet) {
        OwnerDTO ownerDTO = ownerToOwnerDTOWithOutPet(domesticPet.getOwner());
        return DomesticPetDTO.builder().id(domesticPet.getId())
                .name(domesticPet.getName())
                .gender(domesticPet.getGender())
                .type(domesticPet.getType())
                .birthDate(domesticPet.getBirthDate())
                .ownerDTO(ownerDTO)
                .build();
    }
}
