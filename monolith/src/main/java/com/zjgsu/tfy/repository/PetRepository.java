package com.zjgsu.tfy.repository;

import com.zjgsu.tfy.model.Pet;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PetRepository {
    private final Map<Long, Pet> petMap = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public Pet save(Pet pet) {
        if(pet.getId() == null){
            pet.setId(idGen.getAndIncrement());
        }
        petMap.put(pet.getId(), pet);
        return pet;
    }

    public Optional<Pet> findById(Long id) {
        return Optional.ofNullable(petMap.get(id));
    }

    public List<Pet> findAll(){
        return new ArrayList<>(petMap.values());
    }
}