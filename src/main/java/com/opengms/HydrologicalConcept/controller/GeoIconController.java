package com.opengms.HydrologicalConcept.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opengms.HydrologicalConcept.entity.GeoIcon;
import com.opengms.HydrologicalConcept.service.GeoIconService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "地理图标Controller")
@RestController
@RequestMapping(value = "/geoIcon")
public class GeoIconController {

    @Autowired
    GeoIconService geoIconService;

    @ApiOperation(value = "上传地理图标")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestParam("upGeoIcon") @ApiParam(value = "地理图标文件") MultipartFile mfile,
                         @RequestParam("geoIcon") @ApiParam(value = "地理图标JSON对象") String jsonObject) throws IOException {

        GeoIcon geoIcon = JSON.parseObject(jsonObject, GeoIcon.class);
        geoIconService.uploadGeoIcons(mfile,geoIcon);

        return "ok";
    }
}
