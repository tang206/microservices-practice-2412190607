package com.zjgsu.tfy.service;

import com.zjgsu.tfy.model.Visit;
import com.zjgsu.tfy.repository.PetRepository;
import com.zjgsu.tfy.repository.VisitRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final PetRepository petRepository;

    public VisitService(VisitRepository visitRepository, PetRepository petRepository) {
        this.visitRepository = visitRepository;
        this.petRepository = petRepository;
    }

    public Visit createVisit(Visit visit){
        // 业务规则：宠物必须存在，才能新增就诊记录
        if(petRepository.findById(visit.getPetId()).isEmpty()){
            throw new RuntimeException("该宠物不存在，无法添加就诊记录");
        }
        return visitRepository.save(visit);
    }

    public List<Visit> getVisitsByPetId(Long petId){
        return visitRepository.findByPetId(petId);
    }
}