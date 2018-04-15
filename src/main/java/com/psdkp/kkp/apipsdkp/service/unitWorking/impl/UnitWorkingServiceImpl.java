package com.psdkp.kkp.apipsdkp.service.unitWorking.impl;

import com.psdkp.kkp.apipsdkp.domain.unitWorking.UnitWorking;
import com.psdkp.kkp.apipsdkp.repository.unitWorking.UnitWorkingDao;
import com.psdkp.kkp.apipsdkp.service.unitWorking.UnitWorkingService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UnitWorkingServiceImpl implements UnitWorkingService {

    @Autowired
    private UnitWorkingDao unitWorkingDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(unitWorkingDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(UnitWorking unitWorking) {
        if (unitWorking.getName().equals("") || unitWorking.getCode().equals("") || unitWorking.getEmail().equals("") || unitWorking.getPhone().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            UnitWorking u1 = unitWorkingDao.findByCode(unitWorking.getCode());
            UnitWorking u2 = unitWorkingDao.findByName(unitWorking.getName());
            UnitWorking u3 = unitWorkingDao.findByPhone(unitWorking.getPhone());
            UnitWorking u4 = unitWorkingDao.findByFaxmail(unitWorking.getFaxmail());
            UnitWorking u5 = unitWorkingDao.findByEmail(unitWorking.getEmail());
            UnitWorking u6 = unitWorkingDao.findByLangitude(unitWorking.getLangitude());
            UnitWorking u7 = unitWorkingDao.findByLongitude(unitWorking.getLongitude());
            UnitWorking u8 = unitWorkingDao.findByServiceLocation(unitWorking.getServiceLocation());
            UnitWorking u9 = unitWorkingDao.findByTypeUnit(unitWorking.getTypeUnit());

            if (u9 == null) {
                return responMessage.NOT_FOUND("UNIT TYPE");
            } else if (u1 == null) {
                return responMessage.DUPLICATE("KODE");
            } /*else if (u2 != null) {
                return responMessage.DUPLICATE("NAMA");
            } else if (u3 != null) {
                return responMessage.DUPLICATE("NO TELEPON");
            } else if (u4 != null) {
                return responMessage.DUPLICATE("FAKSIMILE");
            } else if (u5 != null) {
                return responMessage.DUPLICATE("EMAIL");
            } else if (u8 != null) {
                return responMessage.DUPLICATE("LOKASI PELAYANAN");
            }*/ else {
                unitWorkingDao.save(unitWorking);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(UnitWorking unitWorking) {
        if (unitWorking.getId() == null || unitWorking.getCode().equals("") || unitWorking.getName().equals("") || unitWorking.getPhone().equals("") || unitWorking.getFaxmail().equals("") || unitWorking.getEmail().equals("") || unitWorking.getLangitude().equals("") || unitWorking.getLongitude().equals("") || unitWorking.getServiceLocation().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            UnitWorking unitId = unitWorkingDao.findId(unitWorking.getId());

            if (unitId != null) {

                UnitWorking uw0 = unitWorkingDao.findByTypeUnit(unitWorking.getTypeUnit());
                UnitWorking uw1 = unitWorkingDao.findByCode(unitWorking.getCode());
                UnitWorking uw2 = unitWorkingDao.findByName(unitWorking.getName());
                UnitWorking uw3 = unitWorkingDao.findByPhone(unitWorking.getPhone());
                UnitWorking uw4 = unitWorkingDao.findByFaxmail(unitWorking.getFaxmail());
                UnitWorking uw5 = unitWorkingDao.findByEmail(unitWorking.getEmail());
                UnitWorking uw6 = unitWorkingDao.findByServiceLocation(unitWorking.getServiceLocation());

                if (uw0 == null) {
                    return responMessage.NOT_FOUND("UNIT TYPE");
                } else if (uw1 != null) {
                    return responMessage.DUPLICATE("KODE");
                } else if (uw2 != null) {
                    return responMessage.DUPLICATE("NAMA");
                } else if (uw3 != null) {
                    return responMessage.DUPLICATE("NO TELEPON");
                } else if (uw4 != null) {
                    return responMessage.DUPLICATE("FAKSIMILE");
                } else if (uw5 != null) {
                    return responMessage.DUPLICATE("EMAIL");
                } else if (uw6 != null) {
                    return responMessage.DUPLICATE("LOKASI PELAYANAN");
                } else {
                    unitWorkingDao.save(unitWorking);
                    return responMessage.SUCCESS_PROCESS_DATA();
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
            UnitWorking unitWorking = unitWorkingDao.findId(id);
            if (unitWorking != null) {
                unitWorkingDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        UnitWorking unitWorking = unitWorkingDao.findId(id);
        if (unitWorking != null) {
            return responMessage.SUCCESS_GET(unitWorkingDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
