package com.psdkp.kkp.apipsdkp.service.unitWorking.impl;

import com.psdkp.kkp.apipsdkp.domain.unitWorking.Facilities;
import com.psdkp.kkp.apipsdkp.repository.unitWorking.FacilitiesDao;
import com.psdkp.kkp.apipsdkp.service.unitWorking.FacilitiesService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FacilitiesServiceImpl implements FacilitiesService{

    @Autowired
    private ResponMessage responMessage;

    @Autowired
    private FacilitiesDao facilitiesDao;

    @Override
    public Object findAll(String type, Pageable pageable) {
        return responMessage.SUCCESS_GET(facilitiesDao.findAllByName(type, pageable));
    }

    @Override
    public Object save(Facilities facilities) {
        if (facilities.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Facilities t = facilitiesDao.findByName(facilities.getName());
            if (t != null) {
                return responMessage.DUPLICATE("TYPE");
            } else {
                facilitiesDao.save(facilities);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(Facilities facilities) {
        if (facilities.getId() == null || facilities.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Facilities t = facilitiesDao.findId(facilities.getId());
            if (t == null){
                return responMessage.NOT_FOUND("ID");
            } else {
                Facilities t2 = facilitiesDao.findByName(facilities.getName());
                if (t2 != null){
                    return responMessage.DUPLICATE("TYPE");
                } else {
                    facilitiesDao.save(facilities);
                    return responMessage.SUCCESS_PROCESS_DATA();
                }
            }
        }
    }

    @Override
    public Object del(Integer id) {
        if (id == null) {
            return responMessage.BAD_REUQEST();
        } else {
            Facilities t = facilitiesDao.findId(id);
            if (t != null) {
                facilitiesDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        Facilities t = facilitiesDao.findId(id);
        if (t != null) {
            return responMessage.SUCCESS_GET(facilitiesDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
