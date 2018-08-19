package com.psdkp.kkp.apipsdkp.controller.addressController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.address.District;
import com.psdkp.kkp.apipsdkp.service.address.impl.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/area/district")
public class DistrictController {

    @Autowired
    private DistrictServiceImpl districtService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) Integer cityId,
            @PageableDefault(sort = { "id" }, value = Integer.MAX_VALUE) Pageable pageable
    ) {
        if (id != null) {
            return districtService.findById(id);
        } else if (cityId!=null){
            return districtService.findAllByCityId(cityId);
        } else {
            return districtService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveDistrict(@RequestBody District district) {
        return districtService.save(district);
    }

    @PutMapping
    public Object editDistrict(@RequestBody District district) {
        return districtService.edit(district);
    }

    @DeleteMapping(value = "/del")
    public Object removeDistrict(@RequestBody Input in) {
        return districtService.del(in.getId());
    }
}
