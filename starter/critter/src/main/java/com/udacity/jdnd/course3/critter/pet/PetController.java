package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.UserService;
import org.h2.engine.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;
    private UserService userService;
    public PetController(PetService petService, UserService userService){
        this.petService = petService;
        this.userService = userService;
    }
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        Customer customer = null;
        if (!StringUtils.isEmpty(petDTO.getOwnerId())){
            Optional<Customer> optionalCustomer = userService.findOne(petDTO.getOwnerId());
            if (optionalCustomer.isPresent()) {
                customer = optionalCustomer.get();
            }
        }
        Pet pet = new Pet();
        pet.setType(petDTO.getType());
        pet.setNotes(petDTO.getNotes());
        pet.setName(petDTO.getName());
        pet.setCustomer(customer);
        pet.setBirthDate(petDTO.getBirthDate());

        Pet petResult = petService.savePet(pet);
        PetDTO petDTOResponse = new PetDTO();
        petDTOResponse.setName(petResult.getName());
        petDTOResponse.setId(petResult.getId());
        petDTOResponse.setNotes(petResult.getNotes());
        if(Objects.nonNull(petResult.getCustomer())) {
            petDTOResponse.setOwnerId(petResult.getCustomer().getId());
        }

        return petDTOResponse;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Optional<Pet> optionalPet = petService.findPetById(petId);
        Pet pet = optionalPet.get();
        PetDTO petDTO = new PetDTO();
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setNotes(pet.getNotes());
        petDTO.setName(pet.getName());
        petDTO.setType(pet.getType());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setId(pet.getId());
        return petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAllPet();
        List<PetDTO> petDTOS = new ArrayList<>();
        pets.forEach(p -> {
            PetDTO petDTO = new PetDTO();
            petDTO.setOwnerId(p.getId());
            petDTO.setNotes(p.getNotes());
            petDTO.setName(p.getName());
            petDTO.setType(p.getType());
            petDTO.setBirthDate(p.getBirthDate());
            petDTOS.add(petDTO);
        });
        return  petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findPetByCustomerId(ownerId);
        List<PetDTO> petDTOS = new ArrayList<>();
        pets.forEach(p -> {
            PetDTO petDTO = new PetDTO();
            petDTO.setOwnerId(p.getCustomer().getId());
            petDTO.setNotes(p.getNotes());
            petDTO.setName(p.getName());
            petDTO.setType(p.getType());
            petDTO.setBirthDate(p.getBirthDate());
            petDTO.setId(p.getId());
            petDTOS.add(petDTO);
        });
        return  petDTOS;
    }
}
