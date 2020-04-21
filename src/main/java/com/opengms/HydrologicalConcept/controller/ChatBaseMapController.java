package com.opengms.HydrologicalConcept.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/chatBaseMap")
public class ChatBaseMapController {

    @RequestMapping(value = "/graph",method = RequestMethod.GET)
    public ModelAndView graph(){
        ModelAndView mv = new ModelAndView("chatBaseMapEditor");
        return mv;
    }

    @RequestMapping(value = "/chatRoom",method = RequestMethod.GET)
    ModelAndView chatRoom (){
        ModelAndView mv = new ModelAndView("chat_new_simple");
        return mv;
    }

}
