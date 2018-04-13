package com.psdkp.kkp.apipsdkp.controller.accountController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.account.RoleGroup;
import com.psdkp.kkp.apipsdkp.service.account.impl.RoleGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/secman/role")
public class RoleGroupController {

    @Autowired
    private RoleGroupServiceImpl roleGroupService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            Pageable pageable
    ){
        if (id!=null){
            return roleGroupService.findById(id);
        } else {
            return roleGroupService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object save(@RequestBody RoleGroup roleGroup){
        return roleGroupService.save(roleGroup);
    }

    @PutMapping
    public Object edit(@RequestBody RoleGroup roleGroup){
        return roleGroupService.edit(roleGroup);
    }

    @DeleteMapping(value = "/del")
    public Object del(@RequestBody Input in){
        return roleGroupService.del(in.getId());
    }
}
