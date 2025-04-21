package com.petmgmt.PetManagement;

import com.petmgmt.PetManagement.Util.InputUtils;
import com.petmgmt.PetManagement.dto.OwnerDTO;
import com.petmgmt.PetManagement.dto.PetDTO;
import com.petmgmt.PetManagement.service.OwnerService;
import com.petmgmt.PetManagement.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class PetManagementApplication implements CommandLineRunner {
	private final OwnerService ownerService;
	private final PetService petService;

    public PetManagementApplication(OwnerService ownerService, PetService petService) {
        this.ownerService = ownerService;
        this.petService = petService;
    }

    public static void main(String[] args) {
		SpringApplication.run(PetManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (Scanner scanner = new Scanner(System.in)) {
			do {
				System.out.println("Welcome to Petistaan->");
				OwnerDTO o = new OwnerDTO();
				o.getPetDTO();
				int menuOption = InputUtils.acceptMenuOption(scanner);

				switch (menuOption) {
					case 1:
						List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
						System.out.println("Owners: "+ownerDTOList.size());
						ownerDTOList.forEach(System.out::println);
						break;
					case 2:
						String sortingBy = InputUtils.acceptSortingBy(scanner);
						String order = InputUtils.acceptSortingOrder(scanner);
						ownerService.findAllSortedOwners(sortingBy, order).forEach(System.out::println);
						break;
					case 3:
						int pageNumber = InputUtils.acceptPageNumberToOperate(scanner);
						int numberOfRecordsPerPage = InputUtils.acceptPageSizeToOperate(scanner);
						ownerService.findAllPaginatedOwners(pageNumber-1, numberOfRecordsPerPage).forEach(System.out::println);
						break;
					case 4:
						sortingBy = InputUtils.acceptSortingBy(scanner);
						order = InputUtils.acceptSortingOrder(scanner);
						pageNumber = InputUtils.acceptPageNumberToOperate(scanner);
						numberOfRecordsPerPage = InputUtils.acceptPageSizeToOperate(scanner);
						ownerService.findAllSortedAndPaginatedOwners(pageNumber-1, numberOfRecordsPerPage,sortingBy,order).forEach(System.out::println);
						break;
					case 5:
						int id = Integer.parseInt(InputUtils.acceptCommonInput(scanner,"id"));
						String firstName = InputUtils.acceptCommonInput(scanner,"firstName");
						String lastName = InputUtils.acceptCommonInput(scanner,"last Name");
						String petName = InputUtils.acceptCommonInput(scanner,"pet Name");
						ownerService.findByIdAndFirstNameAndLastNameAndPetName(id,firstName,lastName,petName).forEach(System.out::println);
						break;




					default:
						System.out.println("Invalid option entered.");
				}
			} while (InputUtils.wantToContinue(scanner));
		}
	}
}
