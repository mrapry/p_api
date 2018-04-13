package com.psdkp.kkp.apipsdkp.controller.accountController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.account.Account;
import com.psdkp.kkp.apipsdkp.service.account.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/secman/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            @RequestParam(defaultValue = "", required = false) String status,
            Pageable pageable
    ){
        if (id!=null) {
            return accountServiceImpl.findById(id);
        } else if(!status.equals("")) {
            return accountServiceImpl.findByStatus(status, pageable);
        } else {
            return accountServiceImpl.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object save(@RequestBody Account account){
        return accountServiceImpl.save(account);
    }

    @PostMapping(value = "/status")
    public Object updateStatus(@RequestBody Input in){
        return accountServiceImpl.updateStatus(in.getStatus(), in.getId());
    }

    @PutMapping
    public Object edit(@RequestBody Account account){
        return accountServiceImpl.edit(account);
    }

    @DeleteMapping(value = "/del")
    public Object del(@RequestBody Input in){
        return accountServiceImpl.del(in.getId());
    }
}
