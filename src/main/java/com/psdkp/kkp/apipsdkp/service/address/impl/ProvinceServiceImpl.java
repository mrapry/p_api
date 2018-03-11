package com.psdkp.kkp.apipsdkp.service.address.impl;

import com.psdkp.kkp.apipsdkp.domain.address.Province;
import com.psdkp.kkp.apipsdkp.repository.address.ProvinceDao;
import com.psdkp.kkp.apipsdkp.service.address.ProvinceService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService{

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Page<Province> findByCode(String code, Pageable pageable) {
        return provinceDao.findByCode(code, pageable);
    }

    @Override
    public Page<Province> findByNameOrCode(String name,Pageable pageable) {
        return provinceDao.findByNameOrCode(name, pageable);
    }

    @Override
    public Page<Province> findAll(Pageable pageable) {
        return provinceDao.findAll(pageable);
    }

    @Override
    public Object save(Province province) {
        if (province.getName().equals("")||province.getCode().equals("")){
            return responMessage.atributNull();
        } else {
            provinceDao.save(province);
            return responMessage.success();
        }
    }

    @Override
    public Object edit(Province province) {
        if (province.getId()==null||province.getName().equals("")||province.getCode().equals("")){
            return responMessage.atributNull();
        } else {
            provinceDao.save(province);
            return responMessage.success();
        }
    }

    @Override
    public Object del(Integer id) {
        if (id==null){
            return responMessage.atributNull();
        } else{
            if (provinceDao.findById(id).isPresent()){
                provinceDao.deleteById(id);
                return responMessage.success();
            } else{
                return responMessage.notFound();
            }
        }
    }
}
