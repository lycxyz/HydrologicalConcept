package com.opengms.HydrologicalConcept.service;

import com.opengms.HydrologicalConcept.dao.ConceptsDao;
import com.opengms.HydrologicalConcept.entity.Concepts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptsService {
    @Autowired
    ConceptsDao conceptsDao;

    public Concepts findByName(String name){
        Concepts concept = conceptsDao.findByName(name);
        return concept;
    }

    public Concepts findByConceptId(String conceptId){
        return conceptsDao.findByConceptID(conceptId);
    }

    public void createNewConcept(Concepts c) {
      conceptsDao.save(c);
    }
}
