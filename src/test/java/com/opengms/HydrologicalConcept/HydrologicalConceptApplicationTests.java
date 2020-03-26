package com.opengms.HydrologicalConcept;

import com.alibaba.fastjson.JSON;
import com.opengms.HydrologicalConcept.entity.GeoRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class HydrologicalConceptApplicationTests {

	@Test
	void saveRule(){
		String rule = "{\n" +
				"\tname: \"地形雨\",\n" +
				"\tconcept: {\n" +
				"\t\tkindOf: [\"雨\"],\n" +
				"\t\tcomponentsOf: [\"高山\",\"暖湿气团\",\"云\"]\n" +
				"\t},\n" +
				"\tspacePosition: {\n" +
				"\t\tkindOf: [\"山地\"]\n" +
				"\t},\n" +
				"\tprocess: {\n" +
				"\t\torderOf: [\"气流抬升\",\"凝结\",\"降雨\"]\n" +
				"\t},\n" +
				"\telementRelation: {\n" +
				"\t\tpositionOfElements: [\"暖湿气团位于高山上方\",\"云位于暖湿气流交汇处上方\"]\n" +
				"\t}\n" +
				"}";
        GeoRule geoRule = JSON.parseObject(rule,GeoRule.class);

	}

}
