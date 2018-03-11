package com.psdkp.kkp.apipsdkp.service.address;

import com.psdkp.kkp.apipsdkp.domain.address.District;
import com.psdkp.kkp.apipsdkp.service.BaseService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DistrictService extends BaseService<Object, District, Integer> {

    Page<District> findByCode(String code, Pageable pageable);
    Page<District> findByName(String name, Pageable pageable);
}
