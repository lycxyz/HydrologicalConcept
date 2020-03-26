package com.opengms.HydrologicalConcept.controller;

import com.alibaba.fastjson.JSON;
import com.opengms.HydrologicalConcept.dao.GeoRuleDao;
import com.opengms.HydrologicalConcept.entity.GeoRule;
import com.opengms.HydrologicalConcept.entity.SubGeoRule;
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

    @RequestMapping(value = "/getAllGeoRules",method = RequestMethod.GET)
    List<GeoRule> getAllGeoRules(){
        String word = "积雨云";
        List<String> relateWords = new ArrayList<String>();
        List<GeoRule> list = geoRuleDao.findAll();
        for (GeoRule geoRule:list) {
            // 名称一致
            if (geoRule.getName().equals(word)){
                SubGeoRule subConcept = geoRule.getConcept();

                String[] arr = subConcept.getAliasOf();
                if (arr != null){
                    for (int i = 0; i < arr.length; i++) {
                        relateWords.add(arr[i]);
                    }
                }
                arr = subConcept.getKindOf();
                if (arr != null){
                    for (int i = 0; i < arr.length; i++) {
                        relateWords.add(arr[i]);
                    }
                }
                arr = subConcept.getPartOf();
                if (arr != null){
                    for (int i = 0; i < arr.length; i++) {
                        relateWords.add(arr[i]);
                    }
                }
                arr = subConcept.getInstanceOf();
                if (arr != null){
                    for (int i = 0; i < arr.length; i++) {
                        relateWords.add(arr[i]);
                    }
                }
                arr = subConcept.getParentOf();
                if (arr != null){
                    for (int i = 0; i < arr.length; i++) {
                        relateWords.add(arr[i]);
                    }
                }
                arr = subConcept.getComponentsOf();
                if (arr != null){
                    for (int i = 0; i < arr.length; i++) {
                        relateWords.add(arr[i]);
                    }
                }
            }else{  //与某个方面的规则一致
                SubGeoRule sub = geoRule.getConcept();
                getRelateWord(word,relateWords,sub);

                sub = geoRule.getProcess();
                getRelateWord(word,relateWords,sub);
            }

        }
        System.out.println(relateWords);

        return geoRuleDao.findAll();
    }

    @RequestMapping(value = "/saveGeoRule")
    void saveGeoRule(String rule){
        System.out.println("进来了");
        GeoRule geoRule = JSON.parseObject(rule,GeoRule.class);
        System.out.println(geoRule);
        geoRuleDao.save(geoRule);
    }

    void getRelateWord(String word, List<String> relateWords, SubGeoRule sub){
        Boolean flag = false;

        String[] arr = sub.getAliasOf();
        if (arr != null && ArrayUtils.contain(arr,word)){
            for (int i = 0; i < arr.length; i++) {
                relateWords.add(arr[i]);
            }
        }
        arr = sub.getKindOf();
        if (arr != null && ArrayUtils.contain(arr,word)){
            System.out.println(arr[0].getClass().toString());
            for (int i = 0; i < arr.length; i++) {
                relateWords.add(arr[i]);
            }
        }
        arr = sub.getPartOf();
        if (arr != null && ArrayUtils.contain(arr,word)){
            for (int i = 0; i < arr.length; i++) {
                relateWords.add(arr[i]);
            }
        }
        arr = sub.getAttributeOf();
        if (arr != null && ArrayUtils.contain(arr,word)){
            for (int i = 0; i < arr.length; i++) {
                relateWords.add(arr[i]);
            }
        }
        arr = sub.getInstanceOf();
        if (arr != null && ArrayUtils.contain(arr,word)){
            for (int i = 0; i < arr.length; i++) {
                relateWords.add(arr[i]);
            }
        }
        arr = sub.getParentOf();
        if (arr != null && ArrayUtils.contain(arr,word)){
            for (int i = 0; i < arr.length; i++) {
                relateWords.add(arr[i]);
            }
        }
        arr = sub.getComponentsOf();
        if (arr != null && ArrayUtils.contain(arr,word)){
            for (int i = 0; i < arr.length; i++) {
                relateWords.add(arr[i]);
            }
        }
        arr = sub.getOrderOf();
        if (arr != null && ArrayUtils.contain(arr,word)){
            for (int i = 0; i < arr.length; i++) {
                relateWords.add(arr[i]);
            }
        }
        arr = sub.getPositionOfElements();
        if (arr != null && ArrayUtils.contain(arr,word)){
            for (int i = 0; i < arr.length; i++) {
                relateWords.add(arr[i]);
            }
        }
    }


}
