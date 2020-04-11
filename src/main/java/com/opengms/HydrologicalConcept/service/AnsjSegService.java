package com.opengms.HydrologicalConcept.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opengms.HydrologicalConcept.dao.ConceptsDao;
import com.opengms.HydrologicalConcept.dto.ConceptMapDTO;
import com.opengms.HydrologicalConcept.dto.GeoIconDTO;
import com.opengms.HydrologicalConcept.entity.*;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;


/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/23 11:39
 */
@Service
public class AnsjSegService{
    @Value("${spring.data.mongodb.conceptCollection}")
    String ConceptSemantic;
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    GeoIconService geoIconService;
    @Autowired
    ConceptMapService conceptMapService;

    @Autowired
    ConceptsService conceptsService;

    @Autowired
    UserImageService userImageService;

    @Autowired
    GeoRuleService geoRuleService;

    public String processInfo(String info) {
        String result="";
        Result output= NlpAnalysis.parse(info);
        if(output.size()!=0){
            JSONArray wordArray = new JSONArray();
            for(int i=0;i<output.size();i++){
                String word = output.get(i).getName(); //拿到词
                String natureStr = output.get(i).getNatureStr(); //拿到词性

                System.out.println(word + "," + "词性是:" + natureStr);
                if(natureStr.equals("n")||natureStr.equals("nt")||natureStr.equals("vn")||natureStr.equals("an")||natureStr.equals("nz")||natureStr.equals("nl")||natureStr.equals("nw")||natureStr.equals("nt")||natureStr.equals("ns")||natureStr.equals("t")||natureStr.equals("tg")||natureStr.equals("f")||natureStr.equals("z")) {
                    wordArray.add(word);
                    System.out.println("--"+word + "," + "词性是:" + natureStr+"--");
                }

            }
            System.out.println(wordArray.toString());
            result = wordArray.toString();
        }else{
            result = "empty";
        }
        return result;
    }

    //废弃，最后删除
    public String map_SpaceInfo(String info){
        String result="";
        Result output= NlpAnalysis.parse(info);
        if(output.size()!=0){
            JSONArray wordArray = new JSONArray();
            for(int i=0;i<output.size()-1;i++){
                String word = output.get(i).getName(); //拿到词
                String nextWord = output.get(i+1).getName();
                String natureStr = output.get(i).getNatureStr(); //拿到词性
                String nextNatureStr = output.get(i+1).getNatureStr();

                if(((natureStr.equals("n")||natureStr.equals("ns")) && nextNatureStr.equals("f")) ) {
                    wordArray.add(word);
                    wordArray.add(word + nextWord);
                    System.out.println(word + "," + "词性是:" + natureStr);
                }
                else if(natureStr.equals("nw")){
                    wordArray.add(word);
                    System.out.println(word + "," + "词性是:" + natureStr);
                }

            }
            if (output.size() == 1){
                String word = output.get(0).getName();
                String natureStr = output.get(0).getNatureStr();
                if (natureStr.equals("nw")){
                    wordArray.add(word);
                    System.out.println(word + "," + "词性是:" + natureStr);
                }
            }
            System.out.println(wordArray.toString());
            result = wordArray.toString();
        }else{
            result = "empty";
        }
        return result;
    }
    public String map_PropertyInfo(String info){
        String result="";
        Result output= NlpAnalysis.parse(info);
        if(output.size()!=0){
            JSONArray wordArray = new JSONArray();
            for(int i=0;i<output.size()-1;i++){
                String word = output.get(i).getName(); //拿到词
                String nextWord = output.get(i+1).getName();
                String natureStr = output.get(i).getNatureStr(); //拿到词性
                String nextNatureStr = output.get(i+1).getNatureStr();

                if((natureStr.equals("n") && nextNatureStr.equals("a")) ) {
                    wordArray.add(word + nextWord);
                    System.out.println(word + "," + "词性是:" + natureStr);
                }

            }

            System.out.println(wordArray.toString());
            result = wordArray.toString();
        }else{
            result = "empty";
        }
        return result;
    }

    public String elasticSearch(String info) {
        JSONArray wordArray = JSONObject.parseArray(info);
        String searchResult="";
        JSONArray arr = new JSONArray();
        int size = wordArray.size();
        if(wordArray.size()!=0){
            //搜索概念
            JSONArray arr1 = new JSONArray();
            //搜索概念图
            JSONArray arr3 = new JSONArray();
            for(int i = 0; i<size; i++){
                Query query = new Query();
                String name = wordArray.getString(i);

                Concepts concept = conceptsService.findByName(name);
                if(concept != null){
                    arr1.add(concept);
                    List<UserImage> images = userImageService.findByConceptId(concept.getConceptID());
                    arr3.add(images);
                }

                //搜索规则
                List<GeoRule> rules = geoRuleService.findRulesByKey(name);
                for (int j = 0; j < rules.size(); j++) {
                    List<String> to = rules.get(j).getTo();
                    for (int k = 0; k < to.size(); k++) {
                        String n = to.get(k);
                        Concepts c = conceptsService.findByName(n);
                        if(c != null){
                            arr1.add(c);
                            List<UserImage> images = userImageService.findByConceptId(c.getConceptID());
                            for (int l = 0; l < images.size(); l++) {
                                UserImage image = images.get(l);
                                arr3.add(image);
                            }
                        }
                    }
                }

            }
            arr.add(arr1);
            arr.add(arr3);
            searchResult= arr.toJSONString();
        }

        return searchResult;
    }

}
