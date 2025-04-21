package com.petmgmt.PetManagement.entity;

import com.petmgmt.PetManagement.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "owner_info")
public class Owner extends Base{
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Enumerated(value = EnumType.STRING)//because Gender is enum nt normal type
    @Column(nullable = false)
    private Gender gender;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(name = "mobile_number",nullable = false,unique = true,length = 10)
    private String mobileNumber;
    @Column(name = "email_id",nullable = false,unique = true)
    private String emailId;
    /*orphan removal means if owner is deleted pet also should be deleted,cascade all
    when owner is saved even pet needs to be saved*/
    @Getter
    @OneToOne(cascade = CascadeType.ALL,optional = false,orphanRemoval = true)
    @JoinColumn(name = "pet_id",referencedColumnName = "id",nullable = false,unique = true)
    private Pet pet;

}
