package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository){
        this.petRepository = petRepository;
    }

    public Pet savePet(Pet pet){
        return petRepository.save(pet);
    }

    public Optional<Pet> findPetById(long id){
        return petRepository.findById(id);
    }

    public List<Pet> getAllPet(){
        return petRepository.findAll();
    }

    public List<Pet> findPetByCustomerId(long customerId){
        return petRepository.findAllPetByCusId(customerId);
    }

    public List<Pet> findPetByPetId(List<Long> petIds){
        return petRepository.findAllById(petIds);
    }

}
