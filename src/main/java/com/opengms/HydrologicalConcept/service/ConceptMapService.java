package com.opengms.HydrologicalConcept.service;

import com.alibaba.fastjson.JSONObject;
import com.opengms.HydrologicalConcept.dao.ConceptMapDao;
import com.opengms.HydrologicalConcept.dto.ConceptMapDTO;
import com.opengms.HydrologicalConcept.entity.ConceptMap;
import com.opengms.HydrologicalConcept.utils.MxGraphUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConceptMapService {

    @Autowired
    ConceptMapDao conceptMapDao;

    @Value("${resourcePath}")
    String resourcePath;

    public void uploadConceptMap(ConceptMap conceptMap) {
        conceptMapDao.insert(conceptMap);
    }


    public void updateConceptMap(ConceptMap conceptMap) throws Exception {

        String name = new Date().getTime() + "_conceptMap.png";
        MxGraphUtils mxGraphUtils = new MxGraphUtils();
        mxGraphUtils.exportImage(conceptMap.getWidth(), conceptMap.getHeight(), conceptMap.getXml(), resourcePath+"/conceptMap/", name);

        conceptMap.setPathUrl("/static/conceptMap/" + name);

        conceptMapDao.save(conceptMap);
    }

    public ConceptMap getConceptMapByGeoId(String geoId){
        ConceptMap concept =conceptMapDao.findConceptMapByGeoId(geoId);
        return concept;
    }

    public List<ConceptMapDTO> getConceptMapByDescriptionContains(String key){
        return conceptMapDao.findConceptMapByDescriptionContains(key);
    }

}
