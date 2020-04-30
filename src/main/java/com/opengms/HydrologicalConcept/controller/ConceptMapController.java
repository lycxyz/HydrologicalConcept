package com.opengms.HydrologicalConcept.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.opengms.HydrologicalConcept.dao.ConceptMapDao;
import com.opengms.HydrologicalConcept.dao.Impl.ConceptDao;
import com.opengms.HydrologicalConcept.entity.ConceptMap;
import com.opengms.HydrologicalConcept.entity.Concepts;
import com.opengms.HydrologicalConcept.entity.GeoRule;
import com.opengms.HydrologicalConcept.entity.UserImage;
import com.opengms.HydrologicalConcept.service.ConceptMapService;
import com.opengms.HydrologicalConcept.service.ConceptsService;
import com.opengms.HydrologicalConcept.service.GeoRuleService;
import com.opengms.HydrologicalConcept.service.UserImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Api(tags = {"概念图Controller"})
@RestController
@RequestMapping(value = "/conceptMap")
public class ConceptMapController {
    @Autowired
    ConceptMapService conceptMapService;
    @Autowired
    ConceptMapDao conceptMapDao;
    @Autowired
    GeoRuleService geoRuleService;
    @Autowired
    ConceptsService conceptsService;
    @Autowired
    UserImageService userImageService;

    @ApiOperation(value = "上传概念图")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    String upload(@RequestBody @ApiParam(value = "概念图JSON对象") ConceptMap conceptMap) {
        conceptMapService.uploadConceptMap(conceptMap);
        return "ok";
    }

    @ApiOperation(value = "更新概念图")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    String update(@RequestBody @ApiParam(value = "概念图JSON对象") ConceptMap conceptMap) throws Exception {
        conceptMapService.updateConceptMap(conceptMap);
        return "ok";
    }

    @ApiOperation(value = "根据Id获取概念图")
    @RequestMapping(value = "/getConceptMapByGeoId", method = RequestMethod.GET)
    ModelAndView getConceptMapByGeoId(String geoId,String name,String className) {
        ModelAndView modelAndView = new ModelAndView("conceptualModel");
        ConceptMap conceptMap = conceptMapService.getConceptMapByGeoId(geoId);
        modelAndView.addObject("GeoElements",conceptMap);
        if (name != "") modelAndView.addObject("name",name);
        if (className != "") modelAndView.addObject("className",className);
        return modelAndView;
    }

    @ApiOperation(value = "获取所有概念图")
    @RequestMapping(value = "/getAllConceptMaps", method = RequestMethod.GET)
    List<ConceptMap> getAllConceptMaps() {
        return conceptMapDao.findAll();
    }

    @RequestMapping(value = "/getConceptMapInfoByGeoId")
    ModelAndView getConceptMapInfoByGeoId(String geoId) {
        ModelAndView mv = new ModelAndView("conceptMapInfo");
        ConceptMap conceptMap = conceptMapService.getConceptMapByGeoId(geoId);
        mv.addObject("conceptMap", conceptMap);
        return mv;
    }

    @RequestMapping(value = "/conceptMapHub")
    ModelAndView getConceptMapHub(){
        ModelAndView modelAndView = new ModelAndView("conceptMapHub");
        List<ConceptMap> list =  conceptMapDao.findAll();
        modelAndView.addObject("concepts",list);
        return modelAndView;
    }

//    语义描述,空间定位,几何形态,
//    演化过程,要素关系,属性特征
    @RequestMapping(value = "/searchFromAll")
    JSONArray searchFromAll(String conceptId) {
        return conceptMapService.searchFromAll(conceptId,6);
    }
    @RequestMapping(value = "/searchFromShape")
    JSONArray searchFromShape(String conceptId){ return conceptMapService.searchFromAll(conceptId,2);}
    @RequestMapping(value = "/searchFromSpace")
    JSONArray searchFromSpace(String conceptId){ return conceptMapService.searchFromAll(conceptId,1);}
    @RequestMapping(value = "/searchFromConcept")
    JSONArray searchFromConcept(String conceptId){ return conceptMapService.searchFromAll(conceptId,0);}
    @RequestMapping(value = "/searchFromProperty")
    JSONArray searchFromProperty(String conceptId){ return conceptMapService.searchFromAll(conceptId,5);}
    @RequestMapping(value = "/searchFromProcess")
    JSONArray searchFromProcess(String conceptId){ return conceptMapService.searchFromAll(conceptId,3);}
    @RequestMapping(value = "/searchFromElementRelation")
    JSONArray searchFromElementRelation(String conceptId){ return conceptMapService.searchFromAll(conceptId,4);}
}
