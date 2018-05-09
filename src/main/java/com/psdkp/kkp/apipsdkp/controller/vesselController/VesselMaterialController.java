package com.psdkp.kkp.apipsdkp.controller.vesselController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.vessel.VesselMaterial;
import com.psdkp.kkp.apipsdkp.service.vessel.impl.VesselMaterialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vessel/vesselMaterial")
public class VesselMaterialController {

    @Autowired
    private VesselMaterialServiceImpl vesselMaterialService;

    @GetMapping
    public Object getAll(
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) String name,
            Pageable pageable
    ) {
        if (id != null) {
            return vesselMaterialService.findById(id);
        } else {
            return vesselMaterialService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveUnitWorking(@RequestBody VesselMaterial vesselMaterial) {
        return vesselMaterialService.save(vesselMaterial);
    }

    @PutMapping
    public Object editUnitWorking(@RequestBody VesselMaterial vesselMaterial) {
        System.out.println("EDIT COMPANY : " + vesselMaterial.toString());
        return vesselMaterialService.edit(vesselMaterial);
    }

    @DeleteMapping(value = "/del")
    public Object removeUnitWorking(@RequestBody Input input) {
        return vesselMaterialService.del(input.getId());
    }
}
