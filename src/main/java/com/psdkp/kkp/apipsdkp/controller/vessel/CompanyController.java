package com.psdkp.kkp.apipsdkp.controller.vessel;

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
            /*@RequestParam(defaultValue = "", required = false) String address,
            @RequestParam(defaultValue = "", required = false) String postCode,
            @RequestParam(defaultValue = "", required = false) String phone,
            @RequestParam(defaultValue = "", required = false) String faxmail,
            @RequestParam(defaultValue = "", required = false) String email,
            @RequestParam(defaultValue = "", required = false) String siup,
            @RequestParam(defaultValue = "", required = false) String siupDate,
            @RequestParam(defaultValue = "", required = false) String pic,
            @RequestParam(defaultValue = "", required = false) String active,*/
            Pageable pageable
    ) {
        if (id != null) {
            return companyService.findById(id);
        } else {
            return companyService.findAll(name, pageable);
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
