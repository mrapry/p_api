package com.psdkp.kkp.apipsdkp.controller.vesselController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.vessel.Abpi;
import com.psdkp.kkp.apipsdkp.service.vessel.impl.AbpiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vessel/abpi")
public class AbpiController {

    @Autowired
    private AbpiServiceImpl abpiService;

    @GetMapping
    public Object getAll(
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) String name,
            Pageable pageable
    ) {
        if (id != null) {
            return abpiService.findById(id);
        } else {
            return abpiService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveUnitWorking(@RequestBody Abpi abpi) {
        return abpiService.save(abpi);
    }

    @PutMapping
    public Object editUnitWorking(@RequestBody Abpi abpi) {
        return abpiService.edit(abpi);
    }

    @DeleteMapping(value = "/del")
    public Object removeUnitWorking(@RequestBody Input input) {
        return abpiService.del(input.getId());
    }
}
