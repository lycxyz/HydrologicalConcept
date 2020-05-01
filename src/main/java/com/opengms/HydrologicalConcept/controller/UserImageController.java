package com.opengms.HydrologicalConcept.controller;

import com.opengms.HydrologicalConcept.dao.UserImageDao;
import com.opengms.HydrologicalConcept.entity.Concepts;
import com.opengms.HydrologicalConcept.entity.UserImage;
import com.opengms.HydrologicalConcept.service.AnsjSegService;
import com.opengms.HydrologicalConcept.service.ConceptsService;
import com.opengms.HydrologicalConcept.service.UserImageService;
import com.opengms.HydrologicalConcept.utils.ArrayUtils;
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
    ConceptsService conceptsService;

    @Autowired
    UserImageDao userImageDao;

    @Autowired
    AnsjSegService ansjSegService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    UserImage upload(MultipartFile mfile,String conceptName) throws IOException {
        UserImage userImage = userImageService.upload(mfile,conceptName);
        return userImage;
    }

    @RequestMapping(value = "/online", method = RequestMethod.POST)
    UserImage online(@RequestParam("url")String url,@RequestParam("conceptName")String conceptName){
        UserImage userImage = new UserImage();
        userImage.setGeoId(UUID.randomUUID().toString());

        //获取conceptId
        Concepts c = conceptsService.findByName(conceptName);
        userImage.setConceptId(c.getConceptID());

        userImage.setName(new Date().getTime() + "_onlineImage");
        userImage.setPathUrl(url);
        userImageDao.save(userImage);
        return userImage;
    }

    @RequestMapping(value = "/userDIY",method = RequestMethod.POST)
    UserImage userDIY(@RequestParam("width") String width,@RequestParam("height") String height,
                      @RequestParam("xml") String xml,@RequestParam("type") String type,
                      @RequestParam("conceptName")String conceptName) throws Exception {
        UserImage userImage = userImageService.userDIY(width,height,xml,type,conceptName);


        return userImage;
    }

    //已废弃
//    @RequestMapping(value = "/update",method = RequestMethod.POST)
//    String update(@RequestBody UserImage userImage){
//
//
//        UserImage image = userImageDao.findByGeoId(userImage.getGeoId());
//        image.setDescription(userImage.getDescription());
//
//        String result = ansjSegService.processInfo(image.getDescription());
//        result = result.replaceAll("\"","");
//        String[] tags = result.substring(1,result.length()-1).split(",");
//        tags = ArrayUtils.removeDuplication(tags);
//        image.setTags(tags);
//
//        userImageDao.save(image);
//        return "ok";
//    }


}
