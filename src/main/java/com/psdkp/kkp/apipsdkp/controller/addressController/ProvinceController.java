package com.psdkp.kkp.apipsdkp.controller.addressController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.address.Province;
import com.psdkp.kkp.apipsdkp.repository.address.ProvinceDao;
import com.psdkp.kkp.apipsdkp.service.address.impl.ProvinceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address/province")
public class ProvinceController {

    @Autowired
    private ProvinceServiceImpl provinceService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            Pageable pageable
    ){
        return provinceService.findAll(name, pageable);
    }

    @PostMapping
    public Object saveProvince(@RequestBody Province province){
        return provinceService.save(province);
    }

    @PutMapping
    public Object editProvince(@RequestBody Province province){
        return provinceService.edit(province);
    }

    @DeleteMapping(value = "/del")
    public Object removeProvince(@RequestBody Input in){
        return provinceService.del(in.getId());
    }


}
