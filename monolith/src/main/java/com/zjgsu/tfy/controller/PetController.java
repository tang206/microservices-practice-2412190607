package com.zjgsu.tfy.controller;

import com.zjgsu.tfy.model.Pet;
import com.zjgsu.tfy.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody Pet pet){
        Pet saved = petService.createPet(pet);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getOne(@PathVariable Long id){
        Optional<Pet> opt = petService.getPetById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pet>> listAll(){
        List<Pet> list = petService.findAllPets();
        return ResponseEntity.ok(list);
    }
}