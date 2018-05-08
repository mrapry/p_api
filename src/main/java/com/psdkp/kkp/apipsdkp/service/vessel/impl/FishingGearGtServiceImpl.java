package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.vessel.FishingGearGt;
import com.psdkp.kkp.apipsdkp.repository.vessel.FishingGearGtDao;
import com.psdkp.kkp.apipsdkp.service.vessel.FishingGearGtService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FishingGearGtServiceImpl implements FishingGearGtService {

    @Autowired
    private FishingGearGtDao fishingGearGtDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(fishingGearGtDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(FishingGearGt fishingGearGt) {
        if (fishingGearGt.getValueUp() == null || fishingGearGt.getValueDown() == null) {
            return responMessage.BAD_REUQEST();
        } else {
//            System.out.println("IN_VALUE_UP: " + fishingGearGt.getValueUp() + ", IN_VALUE_DOWN: " + fishingGearGt.getValueDown());

            FishingGearGt fgUp = fishingGearGtDao.findByValueUp(fishingGearGt.getValueUp());
            FishingGearGt fgDown = fishingGearGtDao.findByValueDown(fishingGearGt.getValueDown());
            if (fgUp != null) {
                return responMessage.DUPLICATE("NILAI ATAS");
            } else if (fgDown != null) {
                return responMessage.DUPLICATE("NILAI BAWAH");
            } else if (fishingGearGt.getValueUp() < fishingGearGt.getValueDown()) {
                return responMessage.NOT_ALLOW();
            } else {
                fishingGearGtDao.save(fishingGearGt);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(FishingGearGt fishingGearGt) {
        if (fishingGearGt.getId() == null || fishingGearGt.getValueUp() == null || fishingGearGt.getValueDown() == null) {
            return responMessage.BAD_REUQEST();
        } else {
//            System.out.println("EDIT FishingGearGt :: GET ID: " + fishingGearGt.getId() + "; IN_VALUE_UP: " + fishingGearGt.getValueUp() + "; IN_VALUE_DOWN: " + fishingGearGt.getValueDown());

            FishingGearGt fId = fishingGearGtDao.findId(fishingGearGt.getId());
            if (fId != null) {
                if (fishingGearGt.getValueUp() <= fishingGearGt.getValueDown()) {
                    return responMessage.VALUE_NOT_ALLOW();
                } else {
                    if (!fId.getValueUp().equals(fishingGearGt.getValueUp())) {
                        FishingGearGt vUp = fishingGearGtDao.findByValueUp(fishingGearGt.getValueUp());
                        if (vUp != null) {
                            return responMessage.DUPLICATE("NILAI ATAS");
                        } else {
                            if (!fId.getValueDown().equals(fishingGearGt.getValueDown())) {
                                FishingGearGt vDown = fishingGearGtDao.findByValueDown(fishingGearGt.getValueDown());
                                if (vDown != null) {
                                    return responMessage.DUPLICATE("NILAI BAWAH");
                                } else {
                                    fishingGearGtDao.save(fishingGearGt);
                                    return responMessage.SUCCESS_PROCESS_DATA();
                                }
                            } else {
                                fishingGearGtDao.save(fishingGearGt);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        }
                    } else {
                        if (!fId.getValueDown().equals(fishingGearGt.getValueDown())) {
                            FishingGearGt vDown = fishingGearGtDao.findByValueDown(fishingGearGt.getValueDown());
                            if (vDown != null) {
                                return responMessage.DUPLICATE("NILAI BAWAH");
                            } else {
                                fishingGearGtDao.save(fishingGearGt);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        } else {
                            fishingGearGtDao.save(fishingGearGt);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    }
                }
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object del(Integer id) {
        if (id == null) {
            return responMessage.BAD_REUQEST();
        } else {
            FishingGearGt fishingGearGt = fishingGearGtDao.findId(id);
            if (fishingGearGt != null) {
                fishingGearGtDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        FishingGearGt fishingGearGt = fishingGearGtDao.findId(id);
        if (fishingGearGt != null) {
            return responMessage.SUCCESS_GET(fishingGearGtDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
