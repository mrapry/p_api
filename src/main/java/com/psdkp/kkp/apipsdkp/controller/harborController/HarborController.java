package com.psdkp.kkp.apipsdkp.controller.harborController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.harbor.Harbor;
import com.psdkp.kkp.apipsdkp.service.harbor.impl.HarborServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/harbor/data")
public class HarborController {

    @Autowired
    private HarborServiceImpl harborService;

    @GetMapping
    public Object getFindAll(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) Integer harborType,
            Pageable pageable
    ) {
        if (id != null) {
            return harborService.findById(id);
        } else if (harborType != null) {
            return harborService.findByTypeId(harborType);
        } else {
            return harborService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object save(@RequestBody Harbor harbor) {
        return harborService.save(harbor);
    }

    @PutMapping
    public Object edit(@RequestBody Harbor harbor) {
        return harborService.edit(harbor);
    }

    @DeleteMapping(value = "/del")
    public Object remove(@RequestBody Input input) {
        return harborService.del(input.getId());
    }
}
