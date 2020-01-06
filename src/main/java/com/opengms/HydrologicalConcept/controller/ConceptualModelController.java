package com.opengms.HydrologicalConcept.controller;

import com.opengms.HydrologicalConcept.dao.GeoIconDao;
import com.opengms.HydrologicalConcept.dto.GeoIconDTO;
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
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
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

        return icon;
    }

    @ApiOperation(value="获取所有地理图标")
    @RequestMapping(value = "/getAllGeoIcons",method = RequestMethod.GET)
    List<GeoIcon> getAllGeoIcons(){
        List<GeoIcon> geoIcons = geoIconService.getAllGeoIcons();
        return geoIcons;
    }

    @ApiOperation(value = "进入聊天室页面")
    @RequestMapping(value = "/chatRoom",method = RequestMethod.GET)
    ModelAndView chatRoom (){
        ModelAndView mv = new ModelAndView("chatRoom");
        return mv;
    }

    @ApiOperation(value="根据词条返回地理图标")
    @RequestMapping(value = "/getGeoIconsByKey",method = RequestMethod.GET)
    List<GeoIconDTO> getGeoIconsByKey(@RequestParam("searchTerms") String key){
        List<GeoIconDTO> geoIcons = geoIconService.getGeoIconByNameContains(key);
        return geoIcons;
    }

}
