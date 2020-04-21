package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.dto.ConceptMapDTO;
import com.opengms.HydrologicalConcept.entity.ConceptMap;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ConceptMapDao  extends MongoRepository<ConceptMap,String> {

    ConceptMap findConceptMapByGeoId(String geoId);
    ConceptMap findConceptMapByConceptId(String conceptId);

    List<ConceptMapDTO> findConceptMapByDescriptionContains(String key);

    Boolean existsByGeoId(String geoId);

    void deleteByGeoId(String geoId);

}
