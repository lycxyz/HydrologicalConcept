package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.Rule_ElementRelation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Rule_ElementRelationDao extends MongoRepository<Rule_ElementRelation,String> {
    List<Rule_ElementRelation> findByFromOrTo(String key);
}
