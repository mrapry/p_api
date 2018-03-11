package com.psdkp.kkp.apipsdkp.service.address;

import com.psdkp.kkp.apipsdkp.domain.address.Province;
import com.psdkp.kkp.apipsdkp.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProvinceService extends BaseService<Object, Province, Integer> {

    Page<Province> findByCode(String code, Pageable pageable);
    Page<Province> findByNameOrCode(String name, Pageable pageable);
}
