package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.Rule_Process;
import com.opengms.HydrologicalConcept.entity.Rule_SpacePosition;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Rule_SpacePositionDao extends MongoRepository<Rule_SpacePosition,String> {
    List<Rule_SpacePosition> findByFrom(String key);
}
