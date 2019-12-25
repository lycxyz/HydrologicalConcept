package com.opengms.HydrologicalConcept.controller;

import com.alibaba.fastjson.JSONObject;
import com.opengms.HydrologicalConcept.entity.User;
import com.opengms.HydrologicalConcept.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/19 17:05
 */
@RestController
@RequestMapping("/concept")
public class TestController {
    @Autowired
    TestService testService;

    @PostMapping(value = "/user")
    @ResponseBody
    public User addUser(@RequestBody User user){
        testService.insertUser(user);
        return user;
    }

}
