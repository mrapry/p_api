package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.vessel.VesselMaterial;
import com.psdkp.kkp.apipsdkp.repository.vessel.VesselMaterialDao;
import com.psdkp.kkp.apipsdkp.service.vessel.VesselMaterialService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VesselMaterialServiceImpl implements VesselMaterialService {

    @Autowired
    private VesselMaterialDao vesselMaterialDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(vesselMaterialDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(VesselMaterial vesselMaterial) {
        if (vesselMaterial.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            VesselMaterial hm = vesselMaterialDao.findByName(vesselMaterial.getName());
            if (hm != null) {
                return responMessage.DUPLICATE("NAMA");
            } else {
                vesselMaterialDao.save(vesselMaterial);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(VesselMaterial vesselMaterial) {
        if (vesselMaterial.getId() == null || vesselMaterial.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            VesselMaterial hmId = vesselMaterialDao.findId(vesselMaterial.getId());
            if (hmId != null) {
                if (!hmId.getName().equals(vesselMaterial.getName())) {
                    VesselMaterial hm = vesselMaterialDao.findByName(vesselMaterial.getName());
                    if (hm != null) {
                        return responMessage.DUPLICATE("NAMA");
                    } else {
                        vesselMaterialDao.save(vesselMaterial);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    }
                } else {
                    vesselMaterialDao.save(vesselMaterial);
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
            VesselMaterial hm = vesselMaterialDao.findId(id);
            if (hm != null) {
                vesselMaterialDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        VesselMaterial hm = vesselMaterialDao.findId(id);
        if (hm != null) {
            return responMessage.SUCCESS_GET(vesselMaterialDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
