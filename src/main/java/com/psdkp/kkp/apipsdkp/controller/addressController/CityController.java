package com.psdkp.kkp.apipsdkp.controller.addressController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.address.City;
import com.psdkp.kkp.apipsdkp.service.address.impl.CityServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/address/city")
public class CityController{

    @Autowired
    private CityServiceImpl cityService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            Pageable pageable
    ){
        if (id!=null){
            return cityService.findById(id);
        } else {
            return cityService.findAll(name, pageable);
        }
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