package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.GeoRule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GeoRuleDao extends MongoRepository<GeoRule,String> {

}
