package com.opengms.HydrologicalConcept.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opengms.HydrologicalConcept.entity.GeoIcon;
import com.opengms.HydrologicalConcept.service.GeoIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/geoIcon")
public class GeoIconController {

    @Autowired
    GeoIconService geoIconService;
    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("upGeoIcon") MultipartFile mfile, @RequestParam("geoIcon")String jsonObject)  throws IOException {
        JSONObject object = JSON.parseObject(jsonObject);
        GeoIcon geoIcon = JSON.toJavaObject(object, GeoIcon.class);
        geoIconService.uploadGeoIcons(mfile,geoIcon);

        return "";
    }
}
