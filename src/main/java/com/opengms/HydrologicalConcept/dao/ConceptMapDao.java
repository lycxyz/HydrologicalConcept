package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.dto.ConceptMapDTO;
import com.opengms.HydrologicalConcept.entity.ConceptMap;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConceptMapDao  extends MongoRepository<ConceptMap,String> {

    ConceptMap findConceptMapByGeoId(String geoId);

    List<ConceptMapDTO> findConceptMapByDescriptionContains(String key);
}
