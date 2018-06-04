package com.psdkp.kkp.apipsdkp.service.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.Company;
import com.psdkp.kkp.apipsdkp.service.BaseService;
import org.springframework.data.domain.Pageable;


public interface CompanyService extends BaseService<Object, Company, Integer> {

    Object findSubDistrict(Integer subDistrictId, Pageable pageable);

}
