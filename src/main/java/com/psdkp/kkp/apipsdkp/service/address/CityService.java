package com.psdkp.kkp.apipsdkp.service.address;

import com.psdkp.kkp.apipsdkp.domain.address.City;
import com.psdkp.kkp.apipsdkp.service.BaseService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface CityService extends BaseService<Object, City, Integer> {

    Page<City> findByCode(String code, Pageable pageable);
    Page<City> findByName(String name, Pageable pageable);
}
