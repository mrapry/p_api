package com.psdkp.kkp.apipsdkp.controller.addressController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.address.SubDistrict;
import com.psdkp.kkp.apipsdkp.service.address.impl.SubDistrictImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address/subDistrict")
public class SubDistrictController {

    @Autowired
    private SubDistrictImpl subDistrictService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            Pageable pageable
    ){
        if (id!=null){
            return subDistrictService.findById(id);
        } else {
            return subDistrictService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveProvince(@RequestBody SubDistrict province){
        return subDistrictService.save(province);
    }

    @PutMapping
    public Object editProvince(@RequestBody SubDistrict province){
        return subDistrictService.edit(province);
    }

    @DeleteMapping(value = "/del")
    public Object removeProvince(@RequestBody Input in){
        return subDistrictService.del(in.getId());
    }
}
