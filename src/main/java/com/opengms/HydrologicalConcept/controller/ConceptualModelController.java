package com.opengms.HydrologicalConcept.controller;

import com.opengms.HydrologicalConcept.dao.GeoIconDao;
import com.opengms.HydrologicalConcept.entity.GeoIcon;
import com.opengms.HydrologicalConcept.service.GeoIconService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"概念建模页面"})
@RestController
@RequestMapping("/conceptualModel")
public class ConceptualModelController {

    @Autowired
    GeoIconDao geoIconDao;

    @Autowired
    GeoIconService geoIconService;

    @ApiOperation(value="根据id获取图标")
    @RequestMapping(value = "/getGeoIconByGeoId",method = RequestMethod.GET)
    GeoIcon getGeoIconByGeoId(@RequestParam("geoId")String geoId){
        GeoIcon icon = geoIconService.getGeoIconByGeoId(geoId);
//        GeoIcon icon = new GeoIcon();
//
//        icon.setGeoId("c9cda363-8cbb-4c6a-8956-82d9bce988cc");
//        icon.setName("水面");
//        icon.setDescription("Wateraa");
//        icon.setXml("<GeoIcon id=\"water\"><Localizations><Localization Local=\"ZH_CN\" Name=\"water\" Description=\"wateraaa\" /></Localizations><Categories><Add name=\"水系统\" /></Categories></GeoIcon>");
//        icon.setPathUrl("../static/upload/geoIcon/水面.png");
//        icon.setIconClass("水系统");
//
//        List<GeoIcon> iconList = new ArrayList<>();
//        GeoIcon icon1 = new GeoIcon();
//
//        icon1.setGeoId("c9cda363-8cbb-4c6a-8956-82d9bce988cc");
//        icon1.setName("水面");
//        icon1.setDescription("Wateraa");
//        icon1.setXml("<GeoIcon id=\"water\"><Localizations><Localization Local=\"ZH_CN\" Name=\"water\" Description=\"wateraaa\" /></Localizations><Categories><Add name=\"水系统\" /></Categories></GeoIcon>");
//        icon1.setPathUrl("../static/upload/geoIcon/水面.png");
//        icon1.setIconClass("水系统");
//        iconList.add(icon1);
//        icon1.setIconClass("海洋系统");
//        iconList.add(icon1);
//
//        List<ConceptMap> mapList = new ArrayList<>();
//        ConceptMap map = new ConceptMap();
//        map.setGeoId("c9cda363-8cbb-4c6a-8956-82d9bce988cc");
//        map.setName("水面");
//        map.setDescription("Wateraa");
//        map.setXml("<GeoIcon id=\"water\"><Localizations><Localization Local=\"ZH_CN\" Name=\"water\" Description=\"wateraaa\" /></Localizations><Categories><Add name=\"水系统\" /></Categories></GeoIcon>");
//        map.setPathUrl("../static/upload/geoIcon/水面.png");
//        map.setMapClass("地表水");
//        mapList.add(map);
//        map.setMapClass("海洋水");
//        mapList.add(map);
//
//
//
//        icon.setRelatedConceptMaps(mapList);
//        icon.setRelatedGeoIcons(iconList);
//
//        geoIconDao.save(icon);
        return icon;
    }

    @ApiOperation(value="获取所有地理图标")
    @RequestMapping(value = "/getAllGeoIcons",method = RequestMethod.GET)
    List<GeoIcon> getAllGeoIcons(){
        List<GeoIcon> geoIcons = geoIconService.getAllGeoIcons();
        return geoIcons;
    }
}
