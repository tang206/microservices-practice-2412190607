package com.zjgsu.tfy.controller;

import com.zjgsu.tfy.model.Visit;
import com.zjgsu.tfy.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitController {
    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public ResponseEntity<Visit> create(@RequestBody Visit visit){
        Visit saved = visitService.createVisit(visit);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<Visit>> getByPet(@PathVariable Long petId){
        List<Visit> list = visitService.getVisitsByPetId(petId);
        return ResponseEntity.ok(list);
    }
}