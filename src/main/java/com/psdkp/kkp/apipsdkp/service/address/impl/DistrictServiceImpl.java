package com.psdkp.kkp.apipsdkp.service.address.impl;

import com.psdkp.kkp.apipsdkp.domain.address.District;
import com.psdkp.kkp.apipsdkp.repository.address.DistrictDao;
import com.psdkp.kkp.apipsdkp.service.address.DistrictService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Page<District> findAll(String name, Pageable pageable) {
        return districtDao.findAllByName(name, pageable);
    }

    @Override
    public Object save(District district) {
        if (district.getName().equals("")||district.getCode().equals("")){
            return responMessage.atributNull();
        } else {
            districtDao.save(district);
            return responMessage.success();
        }
    }

    @Override
    public Object edit(District district) {
        if (district.getId()==null||district.getName().equals("")||district.getCode().equals("")){
            return responMessage.atributNull();
        } else {
            districtDao.save(district);
            return responMessage.success();
        }
    }

    @Override
    public Object del(Integer id) {
        if (id==null){
            return responMessage.atributNull();
        } else{
            if (districtDao.findById(id).isPresent()){
                districtDao.deleteById(id);
                return responMessage.success();
            } else{
                return responMessage.notFound();
            }
        }
    }
}
