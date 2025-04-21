package com.petmgmt.PetManagement.dto;

import com.petmgmt.PetManagement.enums.Gender;
import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)// use those variables for overiding hashcode adnd equals
@Builder//instead of calling setter for all..we can use builder pattern
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {
    @EqualsAndHashCode.Include
    private int id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String city;
    private String state;
    @EqualsAndHashCode.Include
    private String mobileNumber;
    @EqualsAndHashCode.Include
    private String emailId;
    private PetDTO petDTO;
}
