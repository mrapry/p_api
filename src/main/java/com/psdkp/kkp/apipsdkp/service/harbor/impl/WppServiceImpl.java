package com.psdkp.kkp.apipsdkp.service.harbor.impl;

import com.psdkp.kkp.apipsdkp.domain.harbor.Wpp;
import com.psdkp.kkp.apipsdkp.domain.harbor.WppLocation;
import com.psdkp.kkp.apipsdkp.repository.harbor.WppDao;
import com.psdkp.kkp.apipsdkp.repository.harbor.WppLocationDao;
import com.psdkp.kkp.apipsdkp.service.harbor.WppService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WppServiceImpl implements WppService {

    @Autowired
    private WppLocationDao wppLocationDao;

    @Autowired
    private WppDao wppDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(wppDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(Wpp wpp) {
        if (wpp.getCode().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Wpp wCode = wppDao.findByCode(wpp.getCode());
            if (wCode != null) {
                return responMessage.DUPLICATE("KODE");
            } else {
                wppDao.save(wpp);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(Wpp wpp) {
        if (wpp.getId() == null || wpp.getCode().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Wpp wId = wppDao.findId(wpp.getId());
            if (wId != null) {
                Wpp wCode = wppDao.findByCode(wpp.getCode());
                if (!wId.getCode().equals(wpp.getCode())) {
                    if (wCode != null) {
                        return responMessage.DUPLICATE("KODE WPP");
                    } else {
                        wppDao.save(wpp);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    }
                } else {
                    wppDao.save(wpp);
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
            Wpp w = wppDao.findId(id);
            if (w != null) {
                wppDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        Wpp w = wppDao.findId(id);
        if (w != null) {
            return responMessage.SUCCESS_GET(wppDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }

    @Override
    public Object findByLocation(Integer id) {
        WppLocation wId = wppLocationDao.findId(id);
        if (wId != null) {
            return responMessage.SUCCESS_GET(wppLocationDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
