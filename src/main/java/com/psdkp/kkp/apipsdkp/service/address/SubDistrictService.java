package com.psdkp.kkp.apipsdkp.service.address;

import com.psdkp.kkp.apipsdkp.domain.address.SubDistrict;
import com.psdkp.kkp.apipsdkp.service.BaseService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubDistrictService extends BaseService<Object, SubDistrict, Integer> {

    Page<SubDistrict> findByCode(String code, Pageable pageable);
    Page<SubDistrict> findByName(String name, Pageable pageable);
}
