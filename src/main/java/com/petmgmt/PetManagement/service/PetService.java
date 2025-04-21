package com.petmgmt.PetManagement.service;

import com.petmgmt.PetManagement.dto.PetDTO;
import com.petmgmt.PetManagement.exception.PetNotFoundException;

public interface PetService {
    PetDTO findPet(int petId) throws PetNotFoundException;

//    Double findAverageAgeOfPet();
}
