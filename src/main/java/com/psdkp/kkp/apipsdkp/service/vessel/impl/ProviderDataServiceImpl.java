package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.vessel.ProviderData;
import com.psdkp.kkp.apipsdkp.repository.vessel.ProviderDataDao;
import com.psdkp.kkp.apipsdkp.service.vessel.ProviderDataService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProviderDataServiceImpl implements ProviderDataService {

    @Autowired
    private ProviderDataDao providerDataDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(providerDataDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(ProviderData providerData) {
        if (providerData.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            ProviderData pName = providerDataDao.findByName(providerData.getName());
            if (pName != null) {
                return responMessage.DUPLICATE("NAMA");
            } else {
                providerDataDao.save(providerData);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(ProviderData providerData) {
        if (providerData.getId() == null || providerData.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            ProviderData pId = providerDataDao.findId(providerData.getId());
            if (pId != null) {
                if (!pId.getName().equals(providerData.getName())) {
                    ProviderData pName = providerDataDao.findByName(providerData.getName());
                    if (pName != null) {
                        return responMessage.DUPLICATE("NAMA");
                    } else {
                        providerDataDao.save(providerData);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    }
                } else {
                    providerDataDao.save(providerData);
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
            ProviderData providerData = providerDataDao.findId(id);
            if (providerData != null) {
                providerDataDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        ProviderData vt = providerDataDao.findId(id);
        if (vt != null) {
            return responMessage.SUCCESS_GET(providerDataDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
