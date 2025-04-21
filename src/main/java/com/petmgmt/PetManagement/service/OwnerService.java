package com.petmgmt.PetManagement.service;

import com.petmgmt.PetManagement.dto.OwnerDTO;
import com.petmgmt.PetManagement.entity.Owner;
import com.petmgmt.PetManagement.exception.DuplicateOwnerIdException;
import com.petmgmt.PetManagement.exception.OwnerNotFoundException;

import java.util.List;

public interface OwnerService {
    void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerIdException;

    OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

//    void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

    void deleteOwner(int ownerId) throws OwnerNotFoundException;

    List<OwnerDTO> findAllOwners();

    List<OwnerDTO> findByIdAndFirstNameAndLastNameAndPetName(int id, String firstName, String lastName, String petName);

    Iterable<Object> findAllSortedOwners(String sortingBy, String order);
     List<OwnerDTO> findAllPaginatedOwners(int pageNumber, int numberOfRecordsPerPage) ;

    Iterable<Object> findAllSortedAndPaginatedOwners(int i, int numberOfRecordsPerPage, String sortingBy, String order);



}
