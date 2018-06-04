package com.psdkp.kkp.apipsdkp.service.harbor;

import com.psdkp.kkp.apipsdkp.domain.harbor.Harbor;
import com.psdkp.kkp.apipsdkp.service.BaseService;
import org.springframework.data.domain.Pageable;


public interface HarborService extends BaseService<Object, Harbor, Integer> {

    Object findByTypeId(Integer typeId);
}
