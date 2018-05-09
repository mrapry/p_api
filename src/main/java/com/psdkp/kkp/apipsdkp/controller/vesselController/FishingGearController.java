package com.psdkp.kkp.apipsdkp.controller.vesselController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.vessel.FishingGear;
import com.psdkp.kkp.apipsdkp.service.vessel.impl.FishingGearServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vessel/fishingGear")
public class FishingGearController {

    @Autowired
    private FishingGearServiceImpl fishingGearService;

    @GetMapping
    public Object getAll(
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer fishingGearId,
            Pageable pageable
    ) {
        if (id != null) {
            return fishingGearService.findById(id);
        } else if (fishingGearId != null) {
            return fishingGearService.findByFishingGearGt(fishingGearId);
        } else {
            return fishingGearService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveUnitWorking(@RequestBody FishingGear fishingGear) {
        return fishingGearService.save(fishingGear);
    }

    @PutMapping
    public Object editUnitWorking(@RequestBody FishingGear fishingGear) {
        return fishingGearService.edit(fishingGear);
    }

    @DeleteMapping(value = "/del")
    public Object removeUnitWorking(@RequestBody Input input) {
        return fishingGearService.del(input.getId());
    }
}
