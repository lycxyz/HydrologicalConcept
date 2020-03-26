package com.opengms.HydrologicalConcept.service;

import com.opengms.HydrologicalConcept.dao.GeoRuleDao;
import com.opengms.HydrologicalConcept.entity.GeoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoRuleService {

    @Autowired
    GeoRuleDao geoRuleDao;



}
