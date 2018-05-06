package com.psdkp.kkp.apipsdkp.controller.harborController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.harbor.Wpp;
import com.psdkp.kkp.apipsdkp.service.harbor.impl.WppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/harbor/wpp")
public class WppController {

    @Autowired
    private WppServiceImpl wppService;

    @GetMapping
    public Object getFindAll(
            @RequestParam(defaultValue = "", required = false) String code,
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) Integer loc,
            Pageable pageable
    ) {
        if (id != null) {
            return wppService.findById(id);
        } else if (loc != null) {
            return wppService.findByLocation(loc);
        } else {
            return wppService.findAll(code, pageable);
        }
    }

    @PostMapping
    public Object save(@RequestBody Wpp wpp) {
        return wppService.save(wpp);
    }

    @PutMapping
    public Object edit(@RequestBody Wpp wpp) {
        return wppService.edit(wpp);
    }

    @DeleteMapping(value = "/del")
    public Object remove(@RequestBody Input input) {
        return wppService.del(input.getId());
    }
}
