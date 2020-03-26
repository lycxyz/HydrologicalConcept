package com.opengms.HydrologicalConcept.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ArrayUtils {

    public static String[] removeDuplication(String[] strArray){
        List<String> list = new ArrayList<>();
        for (int i=0; i<strArray.length; i++) {
            if(!list.contains(strArray[i])) {
                list.add(strArray[i]);
            }
        }
        //返回一个包含所有对象的指定类型的数组
        strArray =  list.toArray(new String[1]);
        return strArray;
    }

    public static Boolean contain(String[] arr,String word){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains(word)){
                return true;
            }
        }
        return false;
    }
}
