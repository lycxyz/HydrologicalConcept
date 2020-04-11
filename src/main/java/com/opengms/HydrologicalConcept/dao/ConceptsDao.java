package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.Concepts;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConceptsDao extends MongoRepository<Concepts,String> {
    Concepts findByName(String name);
}
