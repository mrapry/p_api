package com.psdkp.kkp.apipsdkp.service.unitWorking;

import com.psdkp.kkp.apipsdkp.domain.unitWorking.UnitWorking;
import com.psdkp.kkp.apipsdkp.service.BaseService;
import org.springframework.data.domain.Pageable;

public interface UnitWorkingService extends BaseService<Object, UnitWorking, Integer> {

    Object findByTypeUnit (Integer id, Pageable pageable);
}
