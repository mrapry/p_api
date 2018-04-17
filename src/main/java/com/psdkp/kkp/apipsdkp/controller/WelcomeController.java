package com.psdkp.kkp.apipsdkp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class WelcomeController {

    @GetMapping
    public Map welcomePage(){
        Map map = new HashMap();
        map.put("message","Welcome to API PSDKP v 1.0");
        map.put("code",200);
        return map;
    }

}
