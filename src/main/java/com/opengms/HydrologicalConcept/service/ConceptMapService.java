package com.opengms.HydrologicalConcept.service;

import com.alibaba.fastjson.JSONObject;
import com.opengms.HydrologicalConcept.dao.ConceptMapDao;
import com.opengms.HydrologicalConcept.dto.ConceptMapDTO;
import com.opengms.HydrologicalConcept.entity.*;
import com.opengms.HydrologicalConcept.utils.ArrayUtils;
import com.opengms.HydrologicalConcept.utils.MxGraphUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ConceptMapService {

    @Autowired
    ConceptMapDao conceptMapDao;

    @Autowired
    ConceptsService conceptsService;

    @Value("${resourcePath}")
    String resourcePath;

    @Autowired
    AnsjSegService ansjSegService;

    MongoTemplate mongoTemplate;

    public void uploadConceptMap(ConceptMap conceptMap) {
        conceptMapDao.insert(conceptMap);
    }


    public void updateConceptMap(ConceptMap conceptMap) throws Exception {

        //获取conceptId
        Concepts c = conceptsService.findByName(conceptMap.getName());
        conceptMap.setConceptId(c.getConceptID());

        String name = new Date().getTime() + "_conceptMap.png";
        MxGraphUtils mxGraphUtils = new MxGraphUtils();
        mxGraphUtils.exportImage(conceptMap.getWidth(), conceptMap.getHeight(), conceptMap.getXml(), resourcePath+"/conceptMap/", name);

        //废弃
//        //添加标签
//        String result = ansjSegService.processInfo(conceptMap.getDescription());
//        result = result.replaceAll("\"","");
//        String[] tags = result.substring(1,result.length()-1).split(",");
//        tags = ArrayUtils.removeDuplication(tags);
//        conceptMap.setTags(tags);
//        //几何
//        if (conceptMap.getShapeInfo().getDesc() != ""){
//            result = ansjSegService.processInfo(conceptMap.getShapeInfo().getDesc());
//            result = result.replaceAll("\"","");
//            tags = result.substring(1,result.length()-1).split(",");
//            tags = ArrayUtils.removeDuplication(tags);
//            ShapeStructure shapeInfo = conceptMap.getShapeInfo();
//            shapeInfo.setTags(tags);
//        }
//        //位置
//        if (conceptMap.getSpacePosition().getDesc() != ""){
//            result = ansjSegService.map_SpaceInfo(conceptMap.getSpacePosition().getDesc());
//            result = result.replaceAll("\"","");
//            tags = result.substring(1,result.length()-1).split(",");
//            tags = ArrayUtils.removeDuplication(tags);
//            SpacePositionStructure spacePosition = conceptMap.getSpacePosition();
//            spacePosition.setTags(tags);
//        }
//        //语义
//        if (conceptMap.getConcept().getDefinition() != ""){
//            List<String> a = new ArrayList<>();
//            a.addAll(conceptMap.getConcept().getRelatedConcepts());
//
//            for (int i = 0; i < conceptMap.getConcept().getClassifications().size(); i++) {
//                a.addAll(conceptMap.getConcept().getClassifications().get(i).getSubConcepts());
//            }
//            tags = ArrayUtils.removeDuplication(a.toArray(new String[1]));
//            ConceptStructure concept = conceptMap.getConcept();
//            concept.setTags(tags);
//        }
//        //属性
//        List<PropertyStructure> properties = conceptMap.getProperties();
//        for (int i = 0; i < properties.size(); i++) {
//            if (properties.get(i).getDescription() != ""){
//                result = ansjSegService.map_PropertyInfo(properties.get(i).getDescription());
//                result = result.replaceAll("\"","");
//                tags = result.substring(1,result.length()-1).split(",");
//                tags = ArrayUtils.removeDuplication(tags);
//                PropertyStructure property = properties.get(i);
//                property.setTags(tags);
//            }
//        }
//        //过程
//        List<ProcessStructure> processes = conceptMap.getProcesses();
//        for (int i = 0; i < processes.size(); i++) {
//            if (processes.get(i).getDescription() != ""){
//                result = ansjSegService.processInfo(processes.get(i).getDescription());
//                result = result.replaceAll("\"","");
//                tags = result.substring(1,result.length()-1).split(",");
//                tags = ArrayUtils.removeDuplication(tags);
//                ProcessStructure process = processes.get(i);
//                process.setTags(tags);
//            }
//        }
//        //关系
//        List<ElementRelationStructure> elementRelations = conceptMap.getElementRelations();
//        for (int i = 0; i < elementRelations.size(); i++) {
//            if (elementRelations.get(i).getRelateValue() != ""){
//                result = ansjSegService.processInfo(elementRelations.get(i).getRelateValue());
//                result = result.replaceAll("\"","");
//                tags = result.substring(1,result.length()-1).split(",");
//                tags = ArrayUtils.removeDuplication(tags);
//                ElementRelationStructure relate = elementRelations.get(i);
//                relate.setTags(tags);
//            }
//        }
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
