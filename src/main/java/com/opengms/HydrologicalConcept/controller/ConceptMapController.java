package com.opengms.HydrologicalConcept.controller;

import com.alibaba.fastjson.JSON;
import com.opengms.HydrologicalConcept.dao.ConceptMapDao;
import com.opengms.HydrologicalConcept.dao.Impl.ConceptDao;
import com.opengms.HydrologicalConcept.entity.ConceptMap;
import com.opengms.HydrologicalConcept.service.ConceptMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Api(tags = {"概念图Controller"})
@RestController
@RequestMapping(value = "/conceptMap")
public class ConceptMapController {
    @Autowired
    ConceptMapService conceptMapService;
    @Autowired
    ConceptMapDao conceptMapDao;

    @ApiOperation(value = "上传概念图")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    String upload (@RequestBody @ApiParam(value = "概念图JSON对象") ConceptMap conceptMap){
        conceptMapService.uploadConceptMap(conceptMap);
        return "ok";
    }
    @ApiOperation(value = "更新概念图")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    String update(@RequestBody @ApiParam(value = "概念图JSON对象") ConceptMap conceptMap) throws Exception {
        conceptMapService.updateConceptMap(conceptMap);
        return "ok";
    }

    @ApiOperation(value = "根据Id获取概念图")
    @RequestMapping(value = "/getConceptMapByGeoId",method = RequestMethod.GET)
    ConceptMap getConceptMapByGeoId(String geoId){
        return conceptMapService.getConceptMapByGeoId(geoId);
    }

    @ApiOperation(value = "获取所有概念图")
    @RequestMapping(value = "/getAllConceptMaps",method = RequestMethod.GET)
    List<ConceptMap> getAllConceptMaps(){
        return conceptMapDao.findAll();
    }

    @RequestMapping(value = "/getConceptMapInfoByGeoId")
    ModelAndView getConceptMapInfoByGeoId(String geoId){
        ModelAndView mv = new ModelAndView("conceptMapInfo");
        ConceptMap conceptMap = conceptMapService.getConceptMapByGeoId(geoId);
        mv.addObject("conceptMap",conceptMap);
        return mv;
    }
}
