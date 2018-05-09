package com.psdkp.kkp.apipsdkp.service.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.FishingGear;
import com.psdkp.kkp.apipsdkp.service.BaseService;

public interface FishingGearService extends BaseService<Object, FishingGear, Integer> {

    Object findByFishingGearGt(Integer id);
}
