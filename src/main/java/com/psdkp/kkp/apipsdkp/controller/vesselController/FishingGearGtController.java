package com.psdkp.kkp.apipsdkp.controller.vesselController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.vessel.FishingGearGt;
import com.psdkp.kkp.apipsdkp.service.vessel.impl.FishingGearGtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vessel/fishingGearGt")
public class FishingGearGtController {
    
    @Autowired
    private FishingGearGtServiceImpl fishingGearGtService;

    @GetMapping
    public Object getAll(
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) String name,
            Pageable pageable
    ) {
        if (id != null) {
            return fishingGearGtService.findById(id);
        } else {
            return fishingGearGtService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveUnitWorking(@RequestBody FishingGearGt fishingGearGt) {
        return fishingGearGtService.save(fishingGearGt);
    }

    @PutMapping
    public Object editUnitWorking(@RequestBody FishingGearGt fishingGearGt) {
        System.out.println("EDIT COMPANY : " + fishingGearGt.toString());
        return fishingGearGtService.edit(fishingGearGt);
    }

    @DeleteMapping(value = "/del")
    public Object removeUnitWorking(@RequestBody Input input) {
        return fishingGearGtService.del(input.getId());
    }
}
