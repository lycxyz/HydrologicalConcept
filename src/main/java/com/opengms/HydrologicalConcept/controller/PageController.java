package com.opengms.HydrologicalConcept.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/19 16:50
 */
@Controller
public class PageController {
    @RequestMapping("/index")
    public String index()
    {
        return "index";
    };
    @RequestMapping("/login")
    public String modelItemGroup()
    {
        return "login";
    };
    @RequestMapping("/register")
    public String register()
    {
        return "register";
    };
    @RequestMapping("/conceptualModel")
    public String conceptualModel()
    {
        return "conceptualModel";
    };
    @RequestMapping("/uploadIcons")
    public String uploadIcons()
    {
        return "uploadIcons";
    };

    @RequestMapping("/_login")
    public String aaa()
    {
        return "_login";
    };
    @RequestMapping("/home")
    public String home()
    {

        return "home";
    };

    @RequestMapping("/createConceptStructure")
    public String createConcept()

    {
        return "createConceptStructure";
    };

    @RequestMapping("/chat")
    public String chatroom()

    {
        return "chat";
    };

    @RequestMapping("/chatTest")
    public String chatTestroom()

    {
        return "chatTest";
    };
}
