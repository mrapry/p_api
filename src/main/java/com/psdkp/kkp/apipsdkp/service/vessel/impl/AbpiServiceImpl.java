package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.vessel.Abpi;
import com.psdkp.kkp.apipsdkp.repository.vessel.AbpiDao;
import com.psdkp.kkp.apipsdkp.service.vessel.AbpiService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AbpiServiceImpl implements AbpiService {

    @Autowired
    private AbpiDao abpiDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(abpiDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(Abpi abpi) {
        if (abpi.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Abpi aName = abpiDao.findByName(abpi.getName());
            if (aName != null) {
                return responMessage.DUPLICATE("NAMA");
            } else {
                abpiDao.save(abpi);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(Abpi abpi) {
        if (abpi.getId() == null || abpi.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Abpi aId = abpiDao.findId(abpi.getId());
            if (aId != null) {
                if (!aId.getName().equals(abpi.getName())) {
                    Abpi aName = abpiDao.findByName(abpi.getName());
                    if (aName != null) {
                        return responMessage.DUPLICATE("NAMA");
                    } else {
                        abpiDao.save(abpi);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    }
                } else {
                    abpiDao.save(abpi);
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
            Abpi abpi = abpiDao.findId(id);
            if (abpi != null) {
                abpiDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        Abpi abpi = abpiDao.findId(id);
        if (abpi != null) {
            return responMessage.SUCCESS_GET(abpiDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
