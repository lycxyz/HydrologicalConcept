package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.Rule_Concept;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Rule_ConceptDao extends MongoRepository<Rule_Concept,String> {
    List<Rule_Concept> findAllByFrom(String key);
}
