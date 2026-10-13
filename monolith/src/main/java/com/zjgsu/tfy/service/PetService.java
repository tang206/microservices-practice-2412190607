package com.zjgsu.tfy.service;

import com.zjgsu.tfy.model.Pet;
import com.zjgsu.tfy.repository.PetRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet createPet(Pet pet){
        return petRepository.save(pet);
    }

    public Optional<Pet> getPetById(Long id){
        return petRepository.findById(id);
    }

    public List<Pet> findAllPets(){
        return petRepository.findAll();
    }
}