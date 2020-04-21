package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.Rule_Process;
import com.opengms.HydrologicalConcept.entity.Rule_Property;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Rule_PropertyDao extends MongoRepository<Rule_Property,String> {
    List<Rule_Property> findByFrom(String key);
}
