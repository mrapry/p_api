package com.psdkp.kkp.apipsdkp.controller.unitWorkingController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.unitWorking.TypeUnit;
import com.psdkp.kkp.apipsdkp.service.unitWorking.impl.TypeUnitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/areas/typeUnit")
public class TypeUnitController {

    @Autowired
    private TypeUnitServiceImpl typeUnitService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String type,
            @RequestParam(defaultValue = "", required = false) Integer id,
            Pageable pageable
    ) {
        if (id != null) {
            return typeUnitService.findById(id);
        } else {
            return typeUnitService.findAll(type, pageable);
        }
    }

    @PostMapping
    public Object saveTypeUnit(@RequestBody TypeUnit typeUnit){
        return typeUnitService.save(typeUnit);
    }

    @PutMapping
    public Object editTypeUnit(@RequestBody TypeUnit typeUnit){
        return typeUnitService.edit(typeUnit);
    }

    @DeleteMapping(value = "/del")
    public Object removeTypeUnit(@RequestBody Input input){
        return typeUnitService.del(input.getId());
    }
}
