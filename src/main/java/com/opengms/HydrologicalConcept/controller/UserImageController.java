package com.opengms.HydrologicalConcept.controller;

import com.opengms.HydrologicalConcept.dao.UserImageDao;
import com.opengms.HydrologicalConcept.entity.UserImage;
import com.opengms.HydrologicalConcept.service.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/userImage")
public class UserImageController {

    @Autowired
    UserImageService userImageService;

    @Autowired
    UserImageDao userImageDao;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    UserImage upload(MultipartFile mfile) throws IOException {
        UserImage userImage = userImageService.upload(mfile);
        return userImage;
    }

    @RequestMapping(value = "/online", method = RequestMethod.POST)
    UserImage online(String url){
        UserImage userImage = new UserImage();
        userImage.setGeoId(UUID.randomUUID().toString());
        userImage.setName(new Date().getTime() + "_onlineImage");
        userImage.setPathUrl(url);
        userImageDao.save(userImage);
        return userImage;
    }

    @RequestMapping(value = "/userDIY",method = RequestMethod.POST)
    UserImage userDIY(@RequestParam("width") String width,@RequestParam("height") String height,
                      @RequestParam("xml") String xml,@RequestParam("type") String type) throws Exception {
        UserImage userImage = userImageService.userDIY(width,height,xml,type);
        return userImage;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    String update(@RequestBody UserImage userImage){

        UserImage image = userImageDao.findByGeoId(userImage.getGeoId());
        image.setDescription(userImage.getDescription());
        userImageDao.save(image);
        return "ok";
    }
}
