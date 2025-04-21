package com.petmgmt.PetManagement.repositry;

import com.petmgmt.PetManagement.dto.PetDTO;
import com.petmgmt.PetManagement.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface PetRepositry extends JpaRepository<Pet, Integer> {
//    Optional<Pet> findById(int petId);

//    Optional<Double> findAverageAgeOfPet();
}
