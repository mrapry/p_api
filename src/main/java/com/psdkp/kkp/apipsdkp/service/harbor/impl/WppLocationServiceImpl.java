package com.psdkp.kkp.apipsdkp.service.harbor.impl;

import com.psdkp.kkp.apipsdkp.domain.harbor.WppLocation;
import com.psdkp.kkp.apipsdkp.repository.harbor.WppLocationDao;
import com.psdkp.kkp.apipsdkp.service.harbor.WppLocationService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WppLocationServiceImpl implements WppLocationService {

    @Autowired
    private WppLocationDao wppLocationDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String location, Pageable pageable) {
        return responMessage.SUCCESS_GET(wppLocationDao.findAllByLocation(location, pageable));
    }

    @Override
    public Object save(WppLocation wppLocation) {
        if (wppLocation.getLocation().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            WppLocation wLoc = wppLocationDao.findByLocation(wppLocation.getLocation());
            if (wLoc != null) {
                return responMessage.DUPLICATE("NAMA LOKASI");
            } else {
                wppLocationDao.save(wppLocation);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(WppLocation wppLocation) {
        if (wppLocation.getId().equals("") || wppLocation.getLocation().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            WppLocation wId = wppLocationDao.findId(wppLocation.getId());
            if (wId != null) {
                if (!wId.getLocation().equals(wppLocation.getLocation())) {
                    WppLocation wLoc = wppLocationDao.findByLocation(wppLocation.getLocation());
                    if (wLoc != null) {
                        return responMessage.DUPLICATE("NAMA LOKASI");
                    } else {
                        wppLocationDao.save(wppLocation);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    }
                } else {
                    wppLocationDao.save(wppLocation);
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
            WppLocation wId = wppLocationDao.findId(id);
            if (wId != null) {
                wppLocationDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        WppLocation wId = wppLocationDao.findId(id);
        if (wId != null) {
            return responMessage.SUCCESS_GET(wppLocationDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
