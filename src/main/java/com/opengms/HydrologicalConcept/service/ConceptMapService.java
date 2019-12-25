package com.opengms.HydrologicalConcept.service;

import com.opengms.HydrologicalConcept.dao.ConceptMapDao;
import com.opengms.HydrologicalConcept.dao.Impl.ConceptDao;
import com.opengms.HydrologicalConcept.entity.ConceptMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ConceptMapService {

    private static final String pathUrl = "\\upload\\conceptMap\\";

    @Autowired
    ConceptMapDao conceptMapDao;

    @Autowired
    ConceptDao conceptDao;

    public void uploadConceptMap(ConceptMap conceptMap) {
        conceptMapDao.insert(conceptMap);
    }

    // 应该还有缺陷，到时再改
    public void updateConceptMap(ConceptMap conceptMap){
//        conceptDao.saveConcept2File(mfile);
        conceptMap.setPathUrl(pathUrl + conceptMap.getPathUrl());
        conceptMapDao.save(conceptMap);
    }

    public ConceptMap getConceptMapByGeoId(String geoId){
        ConceptMap concept =conceptMapDao.findConceptMapByGeoId(geoId);
        return concept;
    }

}
