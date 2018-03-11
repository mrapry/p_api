package com.psdkp.kkp.apipsdkp.controller;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.address.City;
import com.psdkp.kkp.apipsdkp.service.address.impl.CityServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/address/city")
public class CityController{

    @Autowired
    private CityServiceImpl cityService;

    @GetMapping
    public Object findAll(Pageable pageable){
        return cityService.findAll(pageable);
    }

    @PostMapping
    public Object save(@RequestBody City city){
        return cityService.save(city);
    }

    @PutMapping
    public Object edit(@RequestBody City city){
        return cityService.edit(city);
    }

    @DeleteMapping
    public Object del(@RequestBody Input in){
        return cityService.del(in.getId());
    }


}