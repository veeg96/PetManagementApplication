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
public class OwnerMapperUtil {
    private static final String UNSUPPORTED_PET_INSTANCE="Unsupported pet instance";

    public static Owner ownerDTOToOwner(OwnerDTO ownerDTO){
        Owner owner = new Owner();
        owner.setFirstName(ownerDTO.getFirstName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setGender(ownerDTO.getGender());
        owner.setCity(ownerDTO.getCity());
        owner.setState(ownerDTO.getState());
        owner.setMobileNumber(owner.getMobileNumber());
        owner.setEmailId(ownerDTO.getEmailId());
        Pet pet = convertPetDTOToPet(ownerDTO.getPetDTO());
        owner.setPet(pet);
        return owner;

    }

    public static OwnerDTO ownerToOwnerDTO(Owner owner){
        PetDTO petDTO = petToPetDTOWithoutOwner(owner.getPet());
        return OwnerDTO.builder().id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .gender(owner.getGender())
                .city(owner.getCity())
                .state(owner.getState())
                .mobileNumber(owner.getMobileNumber())
                .emailId(owner.getEmailId())
                .petDTO(petDTO)
                .build();
    }

    private static PetDTO petToPetDTOWithoutOwner(Pet pet) {
        if(pet instanceof DomesticPet){
            return convertDomesticPetToPetDTO((DomesticPet) pet);
        } else if(pet instanceof WildPet){
            return convertWildPetToPetDTO((WildPet) pet);
        } else
            throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE,pet.getClass()));
    }

    private static PetDTO convertWildPetToPetDTO(WildPet pet) {
       return PetMapperUtil.petToPetDTO(pet);

    }

    private static PetDTO convertDomesticPetToPetDTO(DomesticPet pet) {
        return PetMapperUtil.petToPetDTO(pet);
    }

    private static DomesticPet convertDomesticDTOToDeometicPet(DomesticPetDTO dto){
        DomesticPet domesticPet = new DomesticPet();
        domesticPet.setName(dto.getName());
        domesticPet.setGender(dto.getGender());
        domesticPet.setType(dto.getType());
        domesticPet.setBirthDate(dto.getBirthDate());
        return domesticPet;
    }
    private static WildPet convertWildDTOToWildPet(WildPetDTO dto){
        WildPet wildPet = new WildPet();
        wildPet.setName(dto.getName());
        wildPet.setGender(dto.getGender());
        wildPet.setType(dto.getType());
        wildPet.setBirthPlace(dto.getBirthPlace());
        return wildPet;
    }

    public static Owner ownerDtoToOwner(OwnerDTO dto){
        Owner owner = new Owner();
        owner.setFirstName(dto.getFirstName());
        owner.setLastName(dto.getLastName());
        owner.setGender(dto.getGender());
        owner.setCity(dto.getCity());
        owner.setState(dto.getState());
        owner.setMobileNumber(dto.getMobileNumber());
        owner.setEmailId(dto.getEmailId());
        Pet pet = convertPetDTOToPet(dto.getPetDTO());
        owner.setPet(pet);
        return owner;
    }

    private static Pet convertPetDTOToPet(PetDTO petDTO) {
        if(petDTO instanceof DomesticPetDTO){
            return convertDomesticDTOToDeometicPet((DomesticPetDTO) petDTO);
        } else if(petDTO instanceof WildPetDTO){
            return convertWildDTOToWildPet((WildPetDTO) petDTO);
        } else
            throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE,petDTO.getClass()));
    }
}
