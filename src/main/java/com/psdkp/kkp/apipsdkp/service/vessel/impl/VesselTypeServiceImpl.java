package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.vessel.VesselType;
import com.psdkp.kkp.apipsdkp.repository.vessel.VesselTypeDao;
import com.psdkp.kkp.apipsdkp.service.vessel.VesselTypeService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VesselTypeServiceImpl implements VesselTypeService {

    private final VesselTypeDao vesselTypeDao;

    private final ResponMessage responMessage;

    @Autowired
    public VesselTypeServiceImpl(VesselTypeDao vesselTypeDao, ResponMessage responMessage) {
        this.vesselTypeDao = vesselTypeDao;
        this.responMessage = responMessage;
    }

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(vesselTypeDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(VesselType vesselType) {
        if (vesselType.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            VesselType vt1 = vesselTypeDao.findByName(vesselType.getName());
            if (vt1 == null) {
                vesselTypeDao.save(vesselType);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.DUPLICATE("TYPE");
            }
        }
    }

    @Override
    public Object edit(VesselType vesselType) {
        if (vesselType.getId() == null || vesselType.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            VesselType vId = vesselTypeDao.findId(vesselType.getId());
            if (vId != null) {
                VesselType vName = vesselTypeDao.findByName(vesselType.getName());
                if (vName != null) {
                    return responMessage.DUPLICATE("TYPE");
                } else {
                    vesselTypeDao.save(vesselType);
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
            VesselType vesselType = vesselTypeDao.findId(id);
            if (vesselType != null) {
                vesselTypeDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        VesselType vt = vesselTypeDao.findId(id);
        if (vt != null) {
            return responMessage.SUCCESS_GET(vesselTypeDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
