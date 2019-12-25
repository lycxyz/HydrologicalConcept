package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.ConceptMap;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConceptMapDao  extends MongoRepository<ConceptMap,String> {

    ConceptMap findConceptMapByGeoId(String geoId);
}
