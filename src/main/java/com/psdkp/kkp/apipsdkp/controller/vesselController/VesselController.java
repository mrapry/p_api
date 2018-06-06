package com.psdkp.kkp.apipsdkp.controller.vesselController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.vessel.Vessel;
import com.psdkp.kkp.apipsdkp.service.vessel.impl.VesselServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vessel/data")
public class VesselController {

    @Autowired
    private VesselServiceImpl vesselService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            Pageable pageable
    ) {
        if (id != null) {
            return vesselService.findById(id);
        } else {
            return vesselService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveVessel(@RequestBody Vessel vessel) {
        return vesselService.save(vessel);
    }

    @PutMapping
    public Object editVessel(@RequestBody Vessel vessel) {
        return vesselService.edit(vessel);
    }

    @DeleteMapping(value = "/del")
    public Object removeVessel(@RequestBody Input input) {
        return vesselService.del(input.getId());
    }
}
