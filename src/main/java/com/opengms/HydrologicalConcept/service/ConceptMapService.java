package com.opengms.HydrologicalConcept.service;

import com.alibaba.fastjson.JSONObject;
import com.opengms.HydrologicalConcept.dao.ConceptMapDao;
import com.opengms.HydrologicalConcept.dto.ConceptMapDTO;
import com.opengms.HydrologicalConcept.entity.*;
import com.opengms.HydrologicalConcept.utils.MxGraphUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConceptMapService {

    @Autowired
    ConceptMapDao conceptMapDao;

    @Value("${resourcePath}")
    String resourcePath;

    @Autowired
    AnsjSegService ansjSegService;

    MongoTemplate mongoTemplate;

    public void uploadConceptMap(ConceptMap conceptMap) {
        conceptMapDao.insert(conceptMap);
    }


    public void updateConceptMap(ConceptMap conceptMap) throws Exception {

        String name = new Date().getTime() + "_conceptMap.png";
        MxGraphUtils mxGraphUtils = new MxGraphUtils();
        mxGraphUtils.exportImage(conceptMap.getWidth(), conceptMap.getHeight(), conceptMap.getXml(), resourcePath+"/conceptMap/", name);

        //添加标签
        String result = ansjSegService.processInfo(conceptMap.getDescription());
        result = result.replaceAll("\"","");
        String[] tags = result.substring(1,result.length()-1).split(",");
        conceptMap.setTags(tags);
        //几何
        if (conceptMap.getShapeInfo().getDesc() != ""){
            result = ansjSegService.processInfo(conceptMap.getShapeInfo().getDesc());
            result = result.replaceAll("\"","");
            tags = result.substring(1,result.length()-1).split(",");
            ShapeStructure shapeInfo = conceptMap.getShapeInfo();
            shapeInfo.setTags(tags);
        }
        //位置
        if (conceptMap.getSpacePosition().getDesc() != ""){
            result = ansjSegService.processInfo(conceptMap.getSpacePosition().getDesc());
            result = result.replaceAll("\"","");
            tags = result.substring(1,result.length()-1).split(",");
            SpacePositionStructure spacePosition = conceptMap.getSpacePosition();
            spacePosition.setTags(tags);
        }
        //语义
        if (conceptMap.getConcept().getDefinition() != ""){
            result = ansjSegService.processInfo(conceptMap.getConcept().getDefinition());
            result = result.replaceAll("\"","");
            tags = result.substring(1,result.length()-1).split(",");
            ConceptStructure concept = conceptMap.getConcept();
            concept.setTags(tags);
        }
        //属性
        List<PropertyStructure> properties = conceptMap.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getDescription() != ""){
                result = ansjSegService.processInfo(properties.get(i).getDescription());
                result = result.replaceAll("\"","");
                tags = result.substring(1,result.length()-1).split(",");
                PropertyStructure property = properties.get(i);
                property.setTags(tags);
            }
        }
        //过程
        List<ProcessStructure> processes = conceptMap.getProcesses();
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).getDescription() != ""){
                result = ansjSegService.processInfo(processes.get(i).getDescription());
                result = result.replaceAll("\"","");
                tags = result.substring(1,result.length()-1).split(",");
                ProcessStructure process = processes.get(i);
                process.setTags(tags);
            }
        }
        //关系
        List<ElementRelationStructure> elementRelations = conceptMap.getElementRelations();
        for (int i = 0; i < elementRelations.size(); i++) {
            if (elementRelations.get(i).getRelateValue() != ""){
                result = ansjSegService.processInfo(elementRelations.get(i).getRelateValue());
                result = result.replaceAll("\"","");
                tags = result.substring(1,result.length()-1).split(",");
                ElementRelationStructure relate = elementRelations.get(i);
                relate.setTags(tags);
            }
        }
        conceptMap.setPathUrl("/static/conceptMap/" + name);
        Boolean f = conceptMapDao.existsByGeoId(conceptMap.getGeoId());
        if (f){
            ConceptMap map = conceptMapDao.findConceptMapByGeoId(conceptMap.getGeoId());
            conceptMap.setId(map.getId());
            conceptMapDao.save(conceptMap);
        }else {
            conceptMapDao.insert(conceptMap);
        }
    }

    public ConceptMap getConceptMapByGeoId(String geoId){
        ConceptMap concept =conceptMapDao.findConceptMapByGeoId(geoId);
        return concept;
    }

    public List<ConceptMapDTO> getConceptMapByDescriptionContains(String key){
        return conceptMapDao.findConceptMapByDescriptionContains(key);
    }

}
