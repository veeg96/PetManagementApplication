package com.petmgmt.PetManagement.repositry;

import com.petmgmt.PetManagement.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    List<Owner> findByIdAndFirstNameAndLastNameAndPetName(int id, String firstName, String lastName, String petName);
//    void save(Owner owner);
//
//    Optional<Owner> findById(int ownerId);
//
//    void updatePetDetails(int ownerId, String petName);
//
//    void deleteById(int ownerId);
//
//    List<Owner> findAll();
//
////    List<Object[]> findIdAndFirstNameAndLastNameAndPetName(int pageNumber, int pageSize);
}
