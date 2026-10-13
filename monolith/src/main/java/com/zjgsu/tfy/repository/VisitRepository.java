package com.zjgsu.tfy.repository;

import com.zjgsu.tfy.model.Visit;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class VisitRepository {
    private final Map<Long, Visit> visitMap = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public Visit save(Visit visit) {
        if(visit.getId() == null){
            visit.setId(idGen.getAndIncrement());
        }
        visitMap.put(visit.getId(), visit);
        return visit;
    }

    public List<Visit> findByPetId(Long petId) {
        List<Visit> result = new ArrayList<>();
        for(Visit v : visitMap.values()){
            if(v.getPetId().equals(petId)){
                result.add(v);
            }
        }
        return result;
    }
}