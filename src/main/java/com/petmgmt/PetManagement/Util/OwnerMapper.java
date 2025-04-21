package com.petmgmt.PetManagement.Util;

import com.petmgmt.PetManagement.dto.DomesticPetDTO;
import com.petmgmt.PetManagement.dto.OwnerDTO;
import com.petmgmt.PetManagement.dto.PetDTO;
import com.petmgmt.PetManagement.dto.WildPetDTO;
import com.petmgmt.PetManagement.entity.DomesticPet;
import com.petmgmt.PetManagement.entity.Owner;
import com.petmgmt.PetManagement.entity.Pet;
import com.petmgmt.PetManagement.entity.WildPet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerMapper {
    String UNSUPPORTED_PET_INSTANCE="Unsupported pet instance";
    @Mapping(source = "owner.pet", target = "petDTO")
    OwnerDTO toDto(Owner owner);

    @Mapping(source = "ownerDTO.petDTO", target = "pet")
    Owner toEntity(OwnerDTO ownerDTO);


    default PetDTO toPetDTO(Pet pet){
        if(pet instanceof DomesticPet)
            return  toDomesticDTO((DomesticPet) pet);
        else
            return  toWildDTO((WildPet) pet);
    }

    @Mapping(target = "ownerDTO",ignore = true)
    WildPetDTO toWildDTO(WildPet wildPet);

    @Mapping(target = "ownerDTO",ignore = true)
    DomesticPetDTO  toDomesticDTO(DomesticPet domesticPet);


    default Pet toPet(PetDTO petDto){
        if(petDto instanceof DomesticPetDTO)
            return  toDomesticPet((DomesticPetDTO) petDto);
        else
            return  toWildPet((WildPetDTO) petDto);
    }

    @Mapping(target  ="owner",ignore = true)
    WildPet toWildPet(WildPetDTO petDto);

    @Mapping(target = "owner",ignore = true)
    DomesticPet toDomesticPet(DomesticPetDTO petDto);

}
