package com.opengms.HydrologicalConcept.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.opengms.HydrologicalConcept.bean.JsonResult;
import com.opengms.HydrologicalConcept.dao.*;
import com.opengms.HydrologicalConcept.entity.*;
import com.opengms.HydrologicalConcept.entity.Rule_Enum.Aspect;
import com.opengms.HydrologicalConcept.service.ConceptMapService;
import com.opengms.HydrologicalConcept.service.GeoRuleService;
import com.opengms.HydrologicalConcept.utils.ArrayUtils;
import com.opengms.HydrologicalConcept.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static javafx.scene.input.KeyCode.L;

@RestController
@RequestMapping(value = "/geoRule")
public class GeoRuleController {

    @Autowired
    GeoRuleDao geoRuleDao;
    @Autowired
    Rule_ConceptDao rule_conceptDao;
    @Autowired
    Rule_ElementRelationDao rule_elementRelationDao;
    @Autowired
    Rule_ProcessDao rule_processDao;
    @Autowired
    Rule_PropertyDao rule_propertyDao;
    @Autowired
    Rule_ShapeDao rule_shapeDao;
    @Autowired
    Rule_SpacePositionDao rule_spacePositionDao;
    @Autowired
    GeoRuleService geoRuleService;
    @Autowired
    ConceptMapService conceptMapService;

    @RequestMapping(value = "/createGeoRule")
    ModelAndView createGeoRule(){
        ModelAndView mv = new ModelAndView("createGeoRule");
        return  mv;
    }

    @RequestMapping(value = "/findImageByRule")
    List<UserImage> findImageByRule(String rule){
        GeoRule geoRule = JSON.parseObject(rule,GeoRule.class);
        return conceptMapService.findImageByRule(geoRule);
    }

    @RequestMapping(value = "/findRulesByKey")
    List<GeoRule> findRulesByKey(String key){
        return geoRuleService.findRulesByKey(key);
        //将方法与实现分开，更方便对方法的复用
    }

    @RequestMapping(value = "/saveRuleConcept")
    JsonResult saveRule_Concept(@RequestParam("rule") String rule){

        Rule_Concept rule_concept = JSON.parseObject(rule, Rule_Concept.class);
        rule_concept.setId(UUID.randomUUID().toString());
        rule_conceptDao.save(rule_concept);

        return ResultUtils.success(rule_concept);

//        String[] rule ={"{'from':'径流','to':['湖泊径流'],'type':1,'description':''}","{'from':'水循环','to':['径流'],'type':2,'description':''}"};
//
//        System.out.println("saveRule_Concept");
//        for (int i = 0; i < rule.length; i++) {
//            Rule_Concept rule_concept = JSON.parseObject(rule[i], Rule_Concept.class);
//            System.out.println(rule_concept);
//            rule_conceptDao.save(rule_concept);
//        }

    }

    @RequestMapping(value = "/saveRuleElementRelation")
    JsonResult saveRule_ElementRelation(@RequestParam("rule") String rule){

        Rule_ElementRelation rule_elementRelation = JSON.parseObject(rule, Rule_ElementRelation.class);
        rule_elementRelation.setId(UUID.randomUUID().toString());
        rule_elementRelationDao.save(rule_elementRelation);

        return ResultUtils.success(rule_elementRelation);

//        String[] rule ={"{'from':'降雨','to':['云','水汽','水滴'],'type':0,'description':''}"};
//        System.out.println("saveRule_ElementRelation");
//        for (int i = 0; i < rule.length; i++) {
//            Rule_ElementRelation rule_elementRelation = JSON.parseObject(rule[i], Rule_ElementRelation.class);
//            System.out.println(rule_elementRelation);
//            rule_elementRelationDao.save(rule_elementRelation);
//        }
    }

    @RequestMapping(value = "/saveRuleProcess")
    JsonResult saveRule_Process(@RequestParam("rule") String rule){

        Rule_Process rule_process = JSON.parseObject(rule, Rule_Process.class);
        rule_process.setId(UUID.randomUUID().toString());
        rule_processDao.save(rule_process);
        return ResultUtils.success(rule_process);

//        String[] rule ={"{'from':'径流','to':['TOPMODEL','VIC'],'type':0,'description':''}","{'from':'湖泊径流','to':['降雨','蒸发','植物截留','下渗','土壤蓄水'],'type':5,'description':''}","{'from':'水循环','to':['PDTank','SHE','HSPF','SWAT','WATLAC'],'type':0,'description':''}"};
//        System.out.println("saveRule_Process");
//        for (int i = 0; i < rule.length; i++) {
//            Rule_Process rule_process = JSON.parseObject(rule[i], Rule_Process.class);
//            System.out.println(rule_process);
//            rule_processDao.save(rule_process);
//        }
    }

    @RequestMapping(value = "/saveRuleProperty")
    JsonResult saveRule_Property(@RequestParam("rule") String rule){
        Rule_Property rule_property = JSON.parseObject(rule, Rule_Property.class);
        rule_property.setId(UUID.randomUUID().toString());
        rule_propertyDao.save(rule_property);
        return ResultUtils.success(rule_property);

//        String[] rule ={"{'from':'鄱阳湖','to':['富营养化','水质','径流'],'type':0,'description':''}"};
//        System.out.println("saveRule_Property");
//        for (int i = 0; i < rule.length; i++) {
//            Rule_Property rule_property = JSON.parseObject(rule[i], Rule_Property.class);
//            System.out.println(rule_property);
//            rule_propertyDao.save(rule_property);
//        }
    }

    @RequestMapping(value = "/saveRuleShape")
    JsonResult saveRule_Shape(@RequestParam("rule") String rule){
        Rule_Shape rule_shape = JSON.parseObject(rule, Rule_Shape.class);
        rule_shape.setId(UUID.randomUUID().toString());
        rule_shapeDao.save(rule_shape);
        return ResultUtils.success(rule_shape);
//        String[] rule ={"{'from':'鄱阳湖','to':['牛轭湖'],'type':2,'description':'与牛轭湖的形状相似'}"};
//        System.out.println("saveRule_Shape");
//        for (int i = 0; i < rule.length; i++) {
//            Rule_Shape rule_shape = JSON.parseObject(rule[i], Rule_Shape.class);
//            System.out.println(rule_shape);
//            rule_shapeDao.save(rule_shape);
//        }
    }

    @RequestMapping(value = "/saveRuleSpacePosition")
    JsonResult saveRule_SpacePosition(@RequestParam("rule") String rule){
        Rule_SpacePosition rule_spacePosition = JSON.parseObject(rule, Rule_SpacePosition.class);
        rule_spacePosition.setId(UUID.randomUUID().toString());
        rule_spacePositionDao.save(rule_spacePosition);
        return ResultUtils.success(rule_spacePosition);
//        String[] rule ={"{'from':'鄱阳湖','to':['赣江','修水','抚河','饶河','信江'],'type':2,'description':'河水汇入鄱阳湖'}"};
//        System.out.println("saveRule_SpacePosition");
//        for (int i = 0; i < rule.length; i++) {
//            Rule_SpacePosition rule_spacePosition = JSON.parseObject(rule[i], Rule_SpacePosition.class);
//            System.out.println(rule_spacePosition);
//            rule_spacePositionDao.save(rule_spacePosition);
//        }
    }
}
