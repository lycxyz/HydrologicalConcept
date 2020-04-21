package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.Rule_ElementRelation;
import com.opengms.HydrologicalConcept.entity.Rule_Process;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Rule_ProcessDao extends MongoRepository<Rule_Process,String> {
    List<Rule_Process> findByFrom(String key);
}
