package com.psdkp.kkp.apipsdkp.controller.addressController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.address.District;
import com.psdkp.kkp.apipsdkp.service.address.impl.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/address/district")
public class DistrictController {

    @Autowired
    private DistrictServiceImpl districtService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            Pageable pageable
    ){
        if (id!=null){
            return districtService.findById(id);
        } else {
            return districtService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveProvince(@RequestBody District province){
        return districtService.save(province);
    }

    @PutMapping
    public Object editProvince(@RequestBody District province){
        return districtService.edit(province);
    }

    @DeleteMapping(value = "/del")
    public Object removeProvince(@RequestBody Input in){
        return districtService.del(in.getId());
    }
}
