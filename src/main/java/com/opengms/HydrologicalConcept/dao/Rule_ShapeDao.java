package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.Rule_Shape;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Rule_ShapeDao extends MongoRepository<Rule_Shape,String> {
    List<Rule_Shape> findByFrom(String key);
}
