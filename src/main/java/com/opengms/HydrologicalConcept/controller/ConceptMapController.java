package com.opengms.HydrologicalConcept.controller;

import com.opengms.HydrologicalConcept.entity.ConceptMap;
import com.opengms.HydrologicalConcept.service.ConceptMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/conceptMap")
public class ConceptMapController {
    @Autowired
    ConceptMapService conceptMapService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    String upload (ConceptMap conceptMap){
        conceptMapService.uploadConceptMap(conceptMap);
        return "ok";
    }

}
