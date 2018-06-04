package com.psdkp.kkp.apipsdkp.controller.harborController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.harbor.HarborType;
import com.psdkp.kkp.apipsdkp.service.harbor.impl.HarborTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/harbor/type")
public class HarborTypeController {
    
    @Autowired
    private HarborTypeServiceImpl harborTypeService;
    
    @GetMapping
    public Object getFindAll(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            Pageable pageable
    ) {
        if (id != null) {
            return harborTypeService.findById(id);
        } else {
            return harborTypeService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object save(@RequestBody HarborType harborType) {
        return harborTypeService.save(harborType);
    }

    @PutMapping
    public Object edit(@RequestBody HarborType harborType) {
        return harborTypeService.edit(harborType);
    }

    @DeleteMapping(value = "/del")
    public Object remove(@RequestBody Input input) {
        return harborTypeService.del(input.getId());
    }
}
