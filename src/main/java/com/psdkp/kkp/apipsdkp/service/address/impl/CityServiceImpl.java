package com.psdkp.kkp.apipsdkp.service.address.impl;

import com.psdkp.kkp.apipsdkp.domain.address.City;
import com.psdkp.kkp.apipsdkp.repository.address.CityDao;
import com.psdkp.kkp.apipsdkp.service.address.CityService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityDao cityDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(cityDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(City city) {
        if (city.getName().equals("")||city.getCode().equals("")){
            return responMessage.BAD_REUQEST();
        } else {
            City p = cityDao.findByCode(city.getCode());
            if (p != null){
                return responMessage.DUPLICATE("KODE");
            } else {
                City p2 = cityDao.findByName(city.getName());
                if (p2 !=null){
                    return responMessage.DUPLICATE("NAMA");
                } else {
                    cityDao.save(city);
                    return responMessage.SUCCESS_PROCESS_DATA();
                }
            }
        }
    }

    @Override
    public Object edit(City city) {
        if (city.getId()==null||city.getName().equals("")||city.getCode().equals("")){
            return responMessage.BAD_REUQEST();
        } else {
            City pCode= cityDao.findId(city.getId());
            if(pCode!= null){
                if (city.getCode().equals(pCode.getCode())){
                    City p2 = cityDao.findByName(city.getName());
                    if (p2 !=null){
                        return responMessage.DUPLICATE("NAMA");
                    } else {
                        cityDao.save(city);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    }
                } else {
                    City proCode = cityDao.findByCode(city.getCode());
                    if (proCode!=null){
                        return responMessage.DUPLICATE("CODE");
                    } else {
                        if (!city.getName().equals(pCode.getName())){
                            City p2 = cityDao.findByName(city.getName());
                            if (p2 !=null){
                                return responMessage.DUPLICATE("NAMA");
                            } else {
                                cityDao.save(city);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        } else {
                            cityDao.save(city);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    }
                }
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object del(Integer id) {
        if (id==null){
            return responMessage.BAD_REUQEST();
        } else{
            City pCode= cityDao.findId(id);
            if(pCode!= null){
                cityDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        City pCode= cityDao.findId(id);
        if(pCode!= null){
            return responMessage.SUCCESS_GET(cityDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}