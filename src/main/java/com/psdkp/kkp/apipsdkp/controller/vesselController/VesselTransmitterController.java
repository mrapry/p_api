package com.psdkp.kkp.apipsdkp.controller.vesselController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.vessel.VesselTransmitter;
import com.psdkp.kkp.apipsdkp.service.vessel.impl.VesselTransmitterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vessel/transmitter")
public class VesselTransmitterController {

    @Autowired
    private VesselTransmitterServiceImpl transmitterService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) String code,
            Pageable pageable
    ) {
        if (id != null) {
            return transmitterService.findById(id);
        } else if (!code.equals("")) {
            return transmitterService.findByCode(code);
        } else {
            return transmitterService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveUnitWorking(@RequestBody VesselTransmitter vesselTransmitter) {
        return transmitterService.save(vesselTransmitter);
    }

    @PutMapping
    public Object editUnitWorking(@RequestBody VesselTransmitter vesselTransmitter) {
        return transmitterService.edit(vesselTransmitter);
    }

    @DeleteMapping(value = "/del")
    public Object removeUnitWorking(@RequestBody Input input) {
        return transmitterService.del(input.getId());
    }
}
