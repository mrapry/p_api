package com.psdkp.kkp.apipsdkp.service.address;


import com.psdkp.kkp.apipsdkp.domain.address.District;
import com.psdkp.kkp.apipsdkp.service.BaseService;

public interface DistrictService extends BaseService<Object, District, Integer> {
    Object findAllByCityId(Integer id);
}
