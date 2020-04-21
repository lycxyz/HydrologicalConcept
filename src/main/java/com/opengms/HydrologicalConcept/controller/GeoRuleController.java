package com.opengms.HydrologicalConcept.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.opengms.HydrologicalConcept.dao.*;
import com.opengms.HydrologicalConcept.entity.*;
import com.opengms.HydrologicalConcept.entity.Rule_Enum.Aspect;
import com.opengms.HydrologicalConcept.service.ConceptMapService;
import com.opengms.HydrologicalConcept.service.GeoRuleService;
import com.opengms.HydrologicalConcept.utils.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    void saveRule_Concept(){
        String[] rule ={"{'from':'湖泊','to':['淡水湖','咸水湖'],'type':1,'description':'湖泊可以分为淡水湖和咸水湖。'}","{'from':'淡水湖','to':['鄱阳湖','洞庭湖'],'type':0,'description':'鄱阳湖和洞庭湖都是淡水湖。'}","{'from':'鄱阳湖','to':['彭蠡','彭蠡泽','彭泽'],'type':3,'description':'鄱阳湖的其他称呼。'}","{'from':'水循环','to':['鄱阳湖水循环','洞庭湖水循环'],'type':0,'description':''}"};
        System.out.println("saveRule_Concept");
        for (int i = 0; i < rule.length; i++) {
            Rule_Concept rule_concept = JSON.parseObject(rule[i], Rule_Concept.class);
            System.out.println(rule_concept);
            rule_conceptDao.save(rule_concept);
        }

    }

    @RequestMapping(value = "/saveRuleElementRelation")
    void saveRule_ElementRelation(){
        String[] rule ={"{'from':'降雨','to':['云','水汽','水滴'],'type':0,'description':''}"};
        System.out.println("saveRule_ElementRelation");
        for (int i = 0; i < rule.length; i++) {
            Rule_ElementRelation rule_elementRelation = JSON.parseObject(rule[i], Rule_ElementRelation.class);
            System.out.println(rule_elementRelation);
            rule_elementRelationDao.save(rule_elementRelation);
        }
    }

    @RequestMapping(value = "/saveRuleProcess")
    void saveRule_Process(){
        String[] rule ={"{'from':'鄱阳湖水循环','to':['降雨','蒸发','植物截留','土壤入渗','地下水运动','坡面漫流','河道汇流'],'type':5,'description':''}"};
        System.out.println("saveRule_Process");
        for (int i = 0; i < rule.length; i++) {
            Rule_Process rule_process = JSON.parseObject(rule[i], Rule_Process.class);
            System.out.println(rule_process);
            rule_processDao.save(rule_process);
        }
    }

    @RequestMapping(value = "/saveRuleProperty")
    void saveRule_Property(){
        String[] rule ={"{'from':'赣江','to':['径流量','含沙量','水位'],'type':0,'description':'鄱阳湖的水文特征'}"};
        System.out.println("saveRule_Property");
        for (int i = 0; i < rule.length; i++) {
            Rule_Property rule_property = JSON.parseObject(rule[i], Rule_Property.class);
            System.out.println(rule_property);
            rule_propertyDao.save(rule_property);
        }
    }

    @RequestMapping(value = "/saveRuleShape")
    void saveRule_Shape(){
        String[] rule ={"{'from':'鄱阳湖','to':['牛轭湖'],'type':2,'description':'与牛轭湖的形状相似'}"};
        System.out.println("saveRule_Shape");
        for (int i = 0; i < rule.length; i++) {
            Rule_Shape rule_shape = JSON.parseObject(rule[i], Rule_Shape.class);
            System.out.println(rule_shape);
            rule_shapeDao.save(rule_shape);
        }
    }

    @RequestMapping(value = "/saveRuleSpacePosition")
    void saveRule_SpacePosition(){
        String[] rule ={"{'from':'鄱阳湖','to':['赣江','修水','抚河','饶河','信江'],'type':2,'description':'河水汇入鄱阳湖'}"};
        System.out.println("saveRule_SpacePosition");
        for (int i = 0; i < rule.length; i++) {
            Rule_SpacePosition rule_spacePosition = JSON.parseObject(rule[i], Rule_SpacePosition.class);
            System.out.println(rule_spacePosition);
            rule_spacePositionDao.save(rule_spacePosition);
        }
    }
}
