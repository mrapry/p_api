package com.psdkp.kkp.apipsdkp.controller.addressController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.address.Province;
import com.psdkp.kkp.apipsdkp.service.address.impl.ProvinceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/area/province")
public class ProvinceController {

    @Autowired
    private ProvinceServiceImpl provinceService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            @PageableDefault(sort = { "id" }, value = 35) Pageable pageable
    ){
        if (id!=null){
            return provinceService.findById(id);
        } else {
            return provinceService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveProvince(@RequestBody Province province){
        System.out.println(province.toString());
        return provinceService.save(province);
    }

    @PutMapping
    public Object editProvince(@RequestBody Province province){
        System.out.println(province.toString());
        return provinceService.edit(province);
    }

    @DeleteMapping(value = "/del")
    public Object removeProvince(@RequestBody Input in){
        return provinceService.del(in.getId());
    }


}
