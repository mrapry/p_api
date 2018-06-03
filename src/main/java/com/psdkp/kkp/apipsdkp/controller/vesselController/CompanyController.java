package com.psdkp.kkp.apipsdkp.controller.vesselController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.vessel.Company;
import com.psdkp.kkp.apipsdkp.service.vessel.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vessel/company")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @GetMapping
    public Object getAll(
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer subDistrictId,
            Pageable pageable
    ) {
        if (id != null) {
            return companyService.findById(id);
        } else if (name != null){
            return companyService.findAll(name, pageable);
        } else {
            return companyService.findSubDistrict(subDistrictId, pageable);
        }
    }

    @PostMapping
    public Object saveUnitWorking(@RequestBody Company company) {
        return companyService.save(company);
    }

    @PutMapping
    public Object editUnitWorking(@RequestBody Company company) {
        System.out.println("EDIT COMPANY : " + company.toString());
        return companyService.edit(company);
    }

    @DeleteMapping(value = "/del")
    public Object removeUnitWorking(@RequestBody Input input) {
        return companyService.del(input.getId());
    }
}
