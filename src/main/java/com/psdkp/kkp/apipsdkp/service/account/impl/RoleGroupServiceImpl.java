package com.psdkp.kkp.apipsdkp.service.account.impl;

import com.psdkp.kkp.apipsdkp.domain.account.RoleGroup;
import com.psdkp.kkp.apipsdkp.repository.account.RoleGroupDao;
import com.psdkp.kkp.apipsdkp.service.account.RoleGroupService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleGroupServiceImpl implements RoleGroupService {

    @Autowired
    private RoleGroupDao roleGroupDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(roleGroupDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(RoleGroup roleGroup) {
        if (roleGroup.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            RoleGroup p = roleGroupDao.findByName(roleGroup.getName());
            if (p != null) {
                return responMessage.DUPLICATE("NAME");
            } else {
                roleGroupDao.save(roleGroup);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(RoleGroup roleGroup) {
        if (roleGroup.getId() == null || roleGroup.getName().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            RoleGroup p = roleGroupDao.findByName(roleGroup.getName());
            if (p != null) {
                return responMessage.DUPLICATE("NAME");
            } else {
                roleGroupDao.save(roleGroup);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object del(Integer id) {
        if (id == null) {
            return responMessage.BAD_REUQEST();
        } else {
            RoleGroup pCode = roleGroupDao.findId(id);
            if (pCode != null) {
                roleGroupDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        RoleGroup pCode = roleGroupDao.findId(id);
        if (pCode != null) {
            return responMessage.SUCCESS_GET(roleGroupDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
