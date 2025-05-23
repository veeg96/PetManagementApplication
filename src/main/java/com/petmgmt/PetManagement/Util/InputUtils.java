package com.petmgmt.PetManagement.Util;

import com.petmgmt.PetManagement.dto.DomesticPetDTO;
import com.petmgmt.PetManagement.dto.OwnerDTO;
import com.petmgmt.PetManagement.dto.PetDTO;
import com.petmgmt.PetManagement.dto.WildPetDTO;
import com.petmgmt.PetManagement.enums.Gender;
import com.petmgmt.PetManagement.enums.PetType;
import com.petmgmt.PetManagement.enums.SortingOrder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;



/**
 * @author abhishekvermaa10
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)//access level private to get private constructor
public class InputUtils {


    public static int acceptMenuOption(Scanner scanner) {
        System.out.println("Press 1 to get all owners.");
        System.out.println("Press 2 to get sorted owners");
        System.out.println("Press 3 to owners according to pagination");
        System.out.println("Press 4 to get sorted and paginated owners");
        System.out.println("Press 5 to get owners by fn,ln,pn");

        int menuOption = scanner.nextInt();
        if (menuOption >= 1 && menuOption <= 5) {
            return menuOption;
        } else {
            System.out.println("Invalid option entered.");
            return acceptMenuOption(scanner);
        }
    }

    public static boolean wantToContinue(Scanner scanner) {
        System.out.println("Press Y to continue and N to exit.");
        char choice = scanner.next().toUpperCase().charAt(0);
        return 'Y' == choice;
    }

    public static OwnerDTO acceptOwnerDetailsToSave(Scanner scanner) {
//        System.out.println("Enter id of owner:");
//        int id = scanner.nextInt();
        System.out.println("Enter first name of owner:");
        String firstName = scanner.next();
        System.out.println("Enter last name of owner:");
        String lastName = scanner.next();
        System.out.println("Enter gender of owner:" + Arrays.asList(Gender.values()).toString());
        String gender = scanner.next().toUpperCase();
        System.out.println("Enter city of owner:");
        String city = scanner.next();
        System.out.println("Enter state of owner:");
        String state = scanner.next();
        System.out.println("Enter mobile number of owner:");
        String mobileNumber = scanner.next();
        System.out.println("Enter email id of owner:");
        String emailId = scanner.next();
        try {
            OwnerDTO ownerDTO = new OwnerDTO();
//            ownerDTO.setId(id);
            ownerDTO.setFirstName(firstName);
            ownerDTO.setLastName(lastName);
            ownerDTO.setGender(Gender.valueOf(gender));
            ownerDTO.setCity(city);
            ownerDTO.setState(state);
            ownerDTO.setMobileNumber(mobileNumber);
            ownerDTO.setEmailId(emailId);
            return ownerDTO;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return acceptOwnerDetailsToSave(scanner);
        }
    }

    public static PetDTO acceptPetDetailsToSave(Scanner scanner) {
//        System.out.println("Enter id of pet:");
//        int petId = scanner.nextInt();
        System.out.println("Enter name of pet:");
        String petName = scanner.next();
        System.out.println("Press D for domestic pet and W for wild pet.");
        char choice = scanner.next().toUpperCase().charAt(0);
        String petPlaceOfBirth = null;
        String petDateOfBirth = null;
        if ('W' == choice) {
            System.out.println("Enter place of birth of pet:");
            petPlaceOfBirth = scanner.next();
        } else if ('D' == choice) {
            System.out.println("Enter date of birth of pet (dd-MM-yyyy):");
            petDateOfBirth = scanner.next();
        }
        System.out.println("Enter gender of pet:" + Arrays.asList(Gender.values()).toString());
        String petGender = scanner.next().toUpperCase();
        System.out.println("Enter pet type:" + Arrays.asList(PetType.values()).toString());
        String petType = scanner.next().toUpperCase();
        try {
            PetDTO petDTO = null;
            if ('D' == choice) {
                petDTO = new DomesticPetDTO();
                ((DomesticPetDTO) petDTO).setBirthDate(convertStringToDate(petDateOfBirth));
            } else if ('W' == choice) {
                petDTO = new WildPetDTO();
                ((WildPetDTO) petDTO).setBirthPlace(petPlaceOfBirth);
            } else {
                throw new IllegalArgumentException("Unsupported pet choice: " + choice);
            }
//            petDTO.setId(petId);
            petDTO.setName(petName);
            petDTO.setGender(Gender.valueOf(petGender));
            petDTO.setType(PetType.valueOf(petType));
            return petDTO;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return acceptPetDetailsToSave(scanner);
        }
    }

    public static String acceptPetDetailsToUpdate(Scanner scanner) {
        System.out.println("Enter upda1ted name of pet:");
        return scanner.next();
    }

    public static int acceptOwnerIdToOperate(Scanner scanner) {
        System.out.println("Enter id of owner:");
        return scanner.nextInt();
    }

    public static int acceptPetIdToOperate(Scanner scanner) {
        System.out.println("Enter id of pet:");
        return scanner.nextInt();
    }

    public static int acceptPageSizeToOperate(Scanner scanner) {
        System.out.println("Enter page size:");
        int pageSize = scanner.nextInt();
        if (pageSize > 0) {
            return pageSize;
        } else {
            System.out.println("Page size must be greater than 0.");
            return acceptPageSizeToOperate(scanner);
        }
    }

    public static int acceptPageNumberToOperate(Scanner scanner) {
        System.out.println("Enter page number:");
        int pageNumber = scanner.nextInt();
        if (pageNumber > 0) {
            return pageNumber;
        } else {
            System.out.println("Page number must be greater than 0.");
            return acceptPageNumberToOperate(scanner);
        }
    }

    private static LocalDate convertStringToDate(String stringDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(stringDate, format);
    }

    public static String acceptSortingBy(Scanner scanner) {
        System.out.println("Enter Sort by param:");
        return scanner.next();
    }

    public static String acceptSortingOrder(Scanner scanner) {
        System.out.println("Enter gender of owner:" + Arrays.asList(SortingOrder.values()).toString());
        return scanner.next().toUpperCase();
    }

    public static String acceptCommonInput(Scanner scanner, String toaccept) {
        System.out.println("Enter "+toaccept+" : " );
        return scanner.next();
    }

}