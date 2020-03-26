package com.opengms.HydrologicalConcept.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opengms.HydrologicalConcept.dto.ConceptMapDTO;
import com.opengms.HydrologicalConcept.dto.GeoIconDTO;
import com.opengms.HydrologicalConcept.entity.ConceptMap;
import com.opengms.HydrologicalConcept.entity.Concepts;
import com.opengms.HydrologicalConcept.entity.GeoIcon;
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
            for(int i = 0; i<size; i++){
                Query query = new Query();
                String name = wordArray.getString(i);
                Pattern pattern = Pattern.compile("^"+name+".*$",Pattern.CASE_INSENSITIVE );
                query.addCriteria(Criteria.where("name").regex(pattern));
                List<Concepts> result = mongoTemplate.find(query, Concepts.class, ConceptSemantic);
                if(result.size()!=0){
                    arr1.add(result);
                }
            }
            //搜索图标
            JSONArray arr2 = new JSONArray();
            for (int i = 0; i < size; i++) {
                List<GeoIconDTO> result = geoIconService.getGeoIconByNameContains(wordArray.getString(i));
                if(result.size()!=0){
                    arr2.add(result);
                }
            }

            //搜索概念图
            JSONArray arr3 = new JSONArray();
            for (int i = 0; i < size; i++) {
                List<ConceptMapDTO> result = conceptMapService.getConceptMapByDescriptionContains(wordArray.getString(i));
                if(result.size()!=0){
                    arr3.add(result);
                }
            }
            arr.add(arr1);
            arr.add(arr2);
            arr.add(arr3);
            searchResult= arr.toJSONString();
        }

        return searchResult;
    }

}
