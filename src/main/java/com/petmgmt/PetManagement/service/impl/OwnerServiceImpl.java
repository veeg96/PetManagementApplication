package com.petmgmt.PetManagement.service.impl;

import com.petmgmt.PetManagement.Util.OwnerMapper;
import com.petmgmt.PetManagement.dto.OwnerDTO;
import com.petmgmt.PetManagement.entity.Owner;
import com.petmgmt.PetManagement.exception.DuplicateOwnerIdException;
import com.petmgmt.PetManagement.exception.OwnerNotFoundException;
import com.petmgmt.PetManagement.repositry.OwnerRepository;
import com.petmgmt.PetManagement.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class OwnerServiceImpl implements OwnerService {

//    @Autowired
    private  final OwnerRepository ownerRepository;
    @Value("${owner.already.exists}")
    private  String ownerAlreadyExists;
    @Value("${owner.not.found}")
    private  String ownerNotFound;
    @Autowired
    private  OwnerMapper ownerMapper;
   /*commented because of @RequiredArgsconstructor
   public OwnerServiceImpl(OwnerRepositry ownerRepository, @Value("${owner.already.exists}") String ownerAlreadyExists, @Value("${owner.not.found}")String ownerNotFound) {
        this.ownerRepository = ownerRepository;
        this.ownerAlreadyExists = ownerAlreadyExists;
        this.ownerNotFound = ownerNotFound;
    }*/

    @Override
    public void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerIdException {
        //need tp convert ownerdto to owner
        if (ownerRepository.findById(ownerDTO.getId()).isPresent()) {
            throw new DuplicateOwnerIdException(String.format(ownerAlreadyExists, ownerDTO.getId()));
        } else {
            Owner owner = ownerMapper.toEntity(ownerDTO);
            ownerRepository.save(owner);
        }
    }

    @Override
    public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
        return ownerMapper.toDto(ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId))));
    }

//    @Override
//    public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
//        Owner owner = ownerRepository.findById(ownerId)
//                .orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
//        owner.getPet().setName(petName);
//        ownerRepository.updatePetDetails(owner.getId(), petName);
//    }

    @Override
    public void deleteOwner(int ownerId) throws OwnerNotFoundException {
        Optional<Owner> optionalOwner = ownerRepository.findById(ownerId);
        if (optionalOwner.isEmpty()) {
            throw new OwnerNotFoundException(String.format(ownerNotFound, ownerId));
        } else {
            ownerRepository.deleteById(ownerId);
        }
    }

    @Override
    public List<OwnerDTO> findAllOwners() {
        //owner to ownee dto
        List<Owner> ls = ownerRepository.findAll();
        return ls.stream().map(ownerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OwnerDTO> findByIdAndFirstNameAndLastNameAndPetName(int id, String firstName, String lastName, String petName) {
        return ownerRepository.findByIdAndFirstNameAndLastNameAndPetName(id, firstName, lastName, petName).stream().map(ownerMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public List<OwnerDTO> findAllPaginatedOwners(int pageNumber, int numberOfRecordsPerPage) {
        Pageable pageable = PageRequest.of(pageNumber , numberOfRecordsPerPage);
        return ownerRepository.findAll(pageable).stream().map(ownerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Iterable<Object> findAllSortedAndPaginatedOwners(int pageNumber, int numberOfRecordsPerPage, String sortingBy, String order) {
        Sort sort = Sort.by(sortingBy);
        if (order.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(pageNumber , numberOfRecordsPerPage,sort);

        return ownerRepository.findAll(pageable).stream().map(ownerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Iterable<Object> findAllSortedOwners(String sortingBy, String order) {
        Sort sort = Sort.by(sortingBy);
        if (order.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        }
        return ownerRepository.findAll(sort).stream().map(ownerMapper::toDto).collect(Collectors.toList());
    }
}
