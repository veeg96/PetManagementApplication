package com.petmgmt.PetManagement.service.impl;

import com.petmgmt.PetManagement.Util.PetMapper;
import com.petmgmt.PetManagement.dto.PetDTO;
import com.petmgmt.PetManagement.exception.PetNotFoundException;
import com.petmgmt.PetManagement.repositry.PetRepositry;
import com.petmgmt.PetManagement.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {
    private  final PetRepositry petRepository;
    @Autowired
    private  PetMapper petMapper;
    @Value("${pet.not.found}")
    private String petNotFound;
   /* public PetServiceImpl(PetRepositry petRepository, @Value("${pet.not.found}") String petNotFound) {
        this.petRepository = petRepository;
        this.petNotFound = petNotFound;
    }*/
    @Override
    public PetDTO findPet(int petId) throws PetNotFoundException {
        return petRepository.findById(petId).map(petMapper::toDTO)
                .orElseThrow(() -> new PetNotFoundException(String.format(petNotFound, petId)));
    }

//    @Override
//    public Double findAverageAgeOfPet() {
//        return petRepository.findAverageAgeOfPet()
//                .orElse(0.0);
//    }
}
