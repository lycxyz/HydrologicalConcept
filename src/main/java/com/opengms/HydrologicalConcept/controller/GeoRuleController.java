package com.opengms.HydrologicalConcept.controller;

import com.alibaba.fastjson.JSON;
import com.opengms.HydrologicalConcept.dao.*;
import com.opengms.HydrologicalConcept.entity.*;
import com.opengms.HydrologicalConcept.entity.Rule_Enum.Aspect;
import com.opengms.HydrologicalConcept.service.GeoRuleService;
import com.opengms.HydrologicalConcept.utils.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/findRulesByKey")
    List<GeoRule> findRulesByKey(String key){
        return geoRuleService.findRulesByKey(key);
        //将方法与实现分开，更方便对方法的复用
    }

    @RequestMapping(value = "/saveRuleConcept")
    void saveRule_Concept(){
        String[] rule ={"{'from':'鄱阳湖','to':['淡水湖'],'type':2,'description':'鄱阳湖是一种淡水湖'}","{'from':'鄱阳湖','to':['彭蠡','彭蠡泽','彭泽'],'type':3,'description':'鄱阳湖的其他称呼'}","{'from':'地面径流','to':['雨洪径流','融雪径流'],'type':0,'description':'按照降水形态，雨洪径流由降雨形成，融雪径流由融雪产生'}","{'from':'水汽扩散','to':['分子扩散','对流扩散','紊动扩散'],'type':0,'description':''}","{'from':'地表径流','to':['地面径流'],'type':3,'description':''}"};
        System.out.println("saveRule_Concept");
        for (int i = 0; i < rule.length; i++) {
            Rule_Concept rule_concept = JSON.parseObject(rule[i], Rule_Concept.class);
            System.out.println(rule_concept);
            rule_conceptDao.save(rule_concept);
        }

    }

    @RequestMapping(value = "/saveRuleElementRelation")
    void saveRule_ElementRelation(){
        String[] rule ={"{'from':'湖泊','to':['流域地表径流','流域地下径流'],'type':1,'description':'流域地表径流和流域地下径流补给湖泊的水量'}","{'from':'湖泊','to':['地下水'],'type':1,'description':'湖泊与地下水进行水交换'}","{'from':'地表水','to':['地下水'],'type':1,'description':'当河流附近地下水水位高于河水水位时，地下水补给河道;反之当河流附近地下水水位低于河水水位时，地下水接受河流的渗漏补给。'}"};
        System.out.println("saveRule_ElementRelation");
        for (int i = 0; i < rule.length; i++) {
            Rule_ElementRelation rule_elementRelation = JSON.parseObject(rule[i], Rule_ElementRelation.class);
            System.out.println(rule_elementRelation);
            rule_elementRelationDao.save(rule_elementRelation);
        }
    }

    @RequestMapping(value = "/saveRuleProcess")
    void saveRule_Process(){
        String[] rule ={"{'from':'大气降水','to':['植被截留','地面降水'],'type':0,'description':'值被截留和地面降水的水量之和与大气降水的水量相同，大气降水经值被截留后，其余的降水落至地面形成地面降水'}","{'from':'水面蒸发','to':['水分汽化','水分扩散'],'type':1,'description':''}","{'from':'地表径流','to':['产流过程','汇流过程'],'type':1,'description':'按照水体的运动性质，可划分为产流、汇流两个阶段'}","{'from':'地表径流','to':['蒸发','填洼','下渗'],'type':0,'description':'降水经过蒸发、填洼、下渗消耗后形成地表径流'}"};
        System.out.println("saveRule_Process");
        for (int i = 0; i < rule.length; i++) {
            Rule_Process rule_process = JSON.parseObject(rule[i], Rule_Process.class);
            System.out.println(rule_process);
            rule_processDao.save(rule_process);
        }
    }

    @RequestMapping(value = "/saveRuleProperty")
    void saveRule_Property(){
        String[] rule ={"{'from':'鄱阳湖','to':['湖泊面积','湖泊水位','径流量','泥沙量'],'type':0,'description':'鄱阳湖的水文特征'}"};
        System.out.println("saveRule_Property");
        for (int i = 0; i < rule.length; i++) {
            Rule_Property rule_property = JSON.parseObject(rule[i], Rule_Property.class);
            System.out.println(rule_property);
            rule_propertyDao.save(rule_property);
        }
    }

    @RequestMapping(value = "/saveRuleShape")
    void saveRule_Shape(){
        String[] rule ={"{'from':'鄱阳湖','to':['牛轭湖'],'type':1,'description':'与牛轭湖的形状相似'}"};
        System.out.println("saveRule_Shape");
        for (int i = 0; i < rule.length; i++) {
            Rule_Shape rule_shape = JSON.parseObject(rule[i], Rule_Shape.class);
            System.out.println(rule_shape);
            rule_shapeDao.save(rule_shape);
        }
    }

    @RequestMapping(value = "/saveRuleSpacePosition")
    void saveRule_SpacePosition(){
        String[] rule ={"{'from':'鄱阳湖','to':['长江'],'type':2,'description':'鄱阳湖与长江相交，是长江的一条支流，位于长江中下游'}","{'from':'鄱阳湖','to':['赣河','抚河','信河','饶河','修河'],'type':2,'description':'河水汇入鄱阳湖'}","{'from':'地表径流','to':['非饱和土壤水'],'type':1,'description':'地表径流相对非饱和土壤水位置较高'}","{'from':'非饱和土壤水','to':['地下水'],'type':1,'description':'非饱和土壤水相对地下水位置较高'}"};
        System.out.println("saveRule_SpacePosition");
        for (int i = 0; i < rule.length; i++) {
            Rule_SpacePosition rule_spacePosition = JSON.parseObject(rule[i], Rule_SpacePosition.class);
            System.out.println(rule_spacePosition);
            rule_spacePositionDao.save(rule_spacePosition);
        }
    }
}
