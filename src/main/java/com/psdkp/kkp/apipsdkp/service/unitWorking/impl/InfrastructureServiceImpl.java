package com.psdkp.kkp.apipsdkp.service.unitWorking.impl;

import com.psdkp.kkp.apipsdkp.domain.unitWorking.Infrastructure;
import com.psdkp.kkp.apipsdkp.repository.unitWorking.InfrastructureDao;
import com.psdkp.kkp.apipsdkp.service.unitWorking.InfrastructureService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureServiceImpl implements InfrastructureService{

    @Autowired
    private ResponMessage responMessage;

    @Autowired
    private InfrastructureDao infrastructureDao;

    @Override
    public Object findAll(String type, Pageable pageable) {
        return responMessage.SUCCESS_GET(infrastructureDao.findAllByName(type, pageable));
    }

    @Override
    public Object save(Infrastructure infrastructure) {
        if (infrastructure.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Infrastructure t = infrastructureDao.findByName(infrastructure.getName());
            if (t != null) {
                return responMessage.DUPLICATE("TYPE");
            } else {
                infrastructureDao.save(infrastructure);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(Infrastructure infrastructure) {
        if (infrastructure.getId() == null || infrastructure.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Infrastructure t = infrastructureDao.findId(infrastructure.getId());
            if (t == null){
                return responMessage.NOT_FOUND("ID");
            } else {
                Infrastructure t2 = infrastructureDao.findByName(infrastructure.getName());
                if (t2 != null){
                    return responMessage.DUPLICATE("TYPE");
                } else {
                    infrastructureDao.save(infrastructure);
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
            Infrastructure t = infrastructureDao.findId(id);
            if (t != null) {
                infrastructureDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        Infrastructure t = infrastructureDao.findId(id);
        if (t != null) {
            return responMessage.SUCCESS_GET(infrastructureDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
