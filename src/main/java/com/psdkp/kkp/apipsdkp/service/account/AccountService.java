package com.psdkp.kkp.apipsdkp.service.account;

import com.psdkp.kkp.apipsdkp.domain.account.Account;
import com.psdkp.kkp.apipsdkp.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService extends BaseService<Object, Account, Integer>{

    Object findByStatus(String status, Pageable pageable);
    Object updateStatus(String status, Integer id);
}
