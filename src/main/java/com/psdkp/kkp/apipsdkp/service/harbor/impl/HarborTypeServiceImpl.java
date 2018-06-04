package com.psdkp.kkp.apipsdkp.service.harbor.impl;

import com.psdkp.kkp.apipsdkp.domain.harbor.HarborType;
import com.psdkp.kkp.apipsdkp.repository.harbor.HarborTypeDao;
import com.psdkp.kkp.apipsdkp.service.harbor.HarborTypeService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HarborTypeServiceImpl implements HarborTypeService {

    @Autowired
    private HarborTypeDao harborTypeDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(harborTypeDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(HarborType harborType) {
        if (harborType.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            HarborType hName = harborTypeDao.findByName(harborType.getName());
            if (hName != null) {
                return responMessage.DUPLICATE("NAMA");
            } else {
                harborTypeDao.save(harborType);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(HarborType harborType) {
        if (harborType.getId() == null || harborType.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            HarborType hId = harborTypeDao.findId(harborType.getId());
            if (hId != null) {
                if (!hId.getName().equals(harborType.getName())) {
                    HarborType hName = harborTypeDao.findByName(harborType.getName());
                    if (hName != null){
                        return responMessage.DUPLICATE("NAMA");
                    } else {
                        harborTypeDao.save(harborType);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    }
                } else {
                    harborTypeDao.save(harborType);
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
            HarborType hId = harborTypeDao.findId(id);
            if (hId != null) {
                harborTypeDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        HarborType hId = harborTypeDao.findId(id);
        if (hId != null) {
            return responMessage.SUCCESS_GET(harborTypeDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
