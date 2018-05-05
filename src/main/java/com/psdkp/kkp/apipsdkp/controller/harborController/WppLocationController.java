package com.psdkp.kkp.apipsdkp.controller.harborController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.harbor.WppLocation;
import com.psdkp.kkp.apipsdkp.service.harbor.impl.WppLocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/harbor/wppLocation")
public class WppLocationController {
    
    @Autowired
    private WppLocationServiceImpl wppLocationService;

    @GetMapping
    public Object getFindAll(
            @RequestParam(defaultValue = "", required = false) String location,
            @RequestParam(defaultValue = "", required = false) Integer id,
            Pageable pageable
    ) {
        if (id != null) {
            return wppLocationService.findById(id);
        } else {
            return wppLocationService.findAll(location, pageable);
        }
    }

    @PostMapping
    public Object save(@RequestBody WppLocation wppLocation){
        return wppLocationService.save(wppLocation);
    }

    @PutMapping
    public Object edit(@RequestBody WppLocation wppLocation){
        return wppLocationService.edit(wppLocation);
    }

    @DeleteMapping(value = "/del")
    public Object remove(@RequestBody Input input){
        return wppLocationService.del(input.getId());
    }
}
