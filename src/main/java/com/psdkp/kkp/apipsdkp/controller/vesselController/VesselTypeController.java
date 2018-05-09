package com.psdkp.kkp.apipsdkp.controller.vesselController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.vessel.VesselType;
import com.psdkp.kkp.apipsdkp.service.vessel.impl.VesselTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vessel/vesselType")
public class VesselTypeController {

    @Autowired
    private VesselTypeServiceImpl vesselTypeService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) String name,
            Pageable pageable
    ) {
        if (id != null) {
            return vesselTypeService.findById(id);
        } else {
            return vesselTypeService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveUnitWorking(@RequestBody VesselType vesselType) {
        return vesselTypeService.save(vesselType);
    }

    @PutMapping
    public Object editUnitWorking(@RequestBody VesselType vesselType) {
        return vesselTypeService.edit(vesselType);
    }

    @DeleteMapping(value = "/del")
    public Object removeUnitWorking(@RequestBody Input input) {
        return vesselTypeService.del(input.getId());
    }

}
