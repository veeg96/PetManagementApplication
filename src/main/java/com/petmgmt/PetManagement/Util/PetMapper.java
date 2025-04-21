package com.petmgmt.PetManagement.Util;

import com.petmgmt.PetManagement.dto.*;
import com.petmgmt.PetManagement.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetMapper {
    default PetDTO toDTO(Pet pet){
        if (pet instanceof DomesticPet)
            return toDomesticDTO((DomesticPet) pet);
        else
            return toWildDTO((WildPet) pet);
    }

    @Mapping(target = "ownerDTO.petDTO", ignore = true)
    @Mapping(source = "wildPet.owner", target = "ownerDTO")
    WildPetDTO toWildDTO(WildPet wildPet);

    @Mapping(target = "ownerDTO.petDTO", ignore = true)
    @Mapping(source = "domesticPet.owner", target = "ownerDTO")
    DomesticPetDTO toDomesticDTO(DomesticPet domesticPet);

}
