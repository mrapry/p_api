package com.psdkp.kkp.apipsdkp.controller.vesselController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.vessel.ProviderData;
import com.psdkp.kkp.apipsdkp.service.vessel.impl.ProviderDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vessel/provider")
public class ProviderDataController {
    
    @Autowired
    private ProviderDataServiceImpl providerDataService;

    @GetMapping
    public Object getAll(
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) String name,
            Pageable pageable
    ) {
        if (id != null) {
            return providerDataService.findById(id);
        } else {
            return providerDataService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveUnitWorking(@RequestBody ProviderData providerData) {
        return providerDataService.save(providerData);
    }

    @PutMapping
    public Object editUnitWorking(@RequestBody ProviderData providerData) {
        return providerDataService.edit(providerData);
    }

    @DeleteMapping(value = "/del")
    public Object removeUnitWorking(@RequestBody Input input) {
        return providerDataService.del(input.getId());
    }
}
