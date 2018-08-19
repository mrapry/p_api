package com.psdkp.kkp.apipsdkp.service.address;


import com.psdkp.kkp.apipsdkp.domain.address.City;
import com.psdkp.kkp.apipsdkp.service.BaseService;

import java.util.List;

public interface CityService extends BaseService<Object, City, Integer> {

    Object findAllByProvinceId(Integer id);
}
