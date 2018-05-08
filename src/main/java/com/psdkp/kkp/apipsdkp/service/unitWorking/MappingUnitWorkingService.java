package com.psdkp.kkp.apipsdkp.service.unitWorking;

import com.psdkp.kkp.apipsdkp.domain.unitWorking.MappingUnitWorking;
import com.psdkp.kkp.apipsdkp.service.BaseService;
import org.springframework.data.domain.Pageable;

public interface MappingUnitWorkingService extends BaseService<Object, MappingUnitWorking, Integer> {

    Object  findByParrent(Integer id, Pageable pageable);
    Object  findByUpt(Integer id, Pageable pageable);
}
