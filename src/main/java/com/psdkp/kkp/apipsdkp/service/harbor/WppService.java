package com.psdkp.kkp.apipsdkp.service.harbor;

import com.psdkp.kkp.apipsdkp.domain.harbor.Wpp;
import com.psdkp.kkp.apipsdkp.domain.harbor.WppLocation;
import com.psdkp.kkp.apipsdkp.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public interface WppService extends BaseService<Object, Wpp, Integer>{

    Object findByLocation(Integer id);
}
