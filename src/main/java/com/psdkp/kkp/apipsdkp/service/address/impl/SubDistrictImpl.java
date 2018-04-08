package com.psdkp.kkp.apipsdkp.service.address.impl;

import com.psdkp.kkp.apipsdkp.domain.address.SubDistrict;
import com.psdkp.kkp.apipsdkp.repository.address.SubDistrictDao;
import com.psdkp.kkp.apipsdkp.service.BaseService;
import com.psdkp.kkp.apipsdkp.service.address.SubDistrictService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubDistrictImpl implements SubDistrictService{

    @Autowired
    private SubDistrictDao subDistrictDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Page<SubDistrict> findAll(String name, Pageable pageable) {
        return subDistrictDao.findAllByName(name, pageable);
    }

    @Override
    public Object save(SubDistrict province) {
        if (province.getName().equals("")||province.getCode().equals("")){
            return responMessage.atributNull();
        } else {
            subDistrictDao.save(province);
            return responMessage.success();
        }
    }

    @Override
    public Object edit(SubDistrict province) {
        if (province.getId()==null||province.getName().equals("")||province.getCode().equals("")){
            return responMessage.atributNull();
        } else {
            subDistrictDao.save(province);
            return responMessage.success();
        }
    }

    @Override
    public Object del(Integer id) {
        if (id==null){
            return responMessage.atributNull();
        } else{
            if (subDistrictDao.findById(id).isPresent()){
                subDistrictDao.deleteById(id);
                return responMessage.success();
            } else{
                return responMessage.notFound();
            }
        }
    }
}
