package com.psdkp.kkp.apipsdkp.service.address.impl;

import com.psdkp.kkp.apipsdkp.domain.address.Province;
import com.psdkp.kkp.apipsdkp.repository.address.ProvinceDao;
import com.psdkp.kkp.apipsdkp.service.address.ProvinceService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(provinceDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(Province province) {
        if (province.getName().trim().equals("") || province.getCode().trim().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Province p = provinceDao.findByCode(province.getCode().trim());
            if (p != null) {
                return responMessage.DUPLICATE("KODE");
            } else {
                Province p2 = provinceDao.findByName(province.getName().trim());
                if (p2 != null) {
                    return responMessage.DUPLICATE("NAMA");
                } else {
                    provinceDao.save(province);
                    return responMessage.SUCCESS_PROCESS_DATA();
                }
            }
        }
    }

    @Override
    public Object edit(Province province) {
        if (province.getId() == null || province.getName().trim().equals("") || province.getCode().trim().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Province pCode = provinceDao.findId(province.getId());
            if (pCode != null) {
                if (province.getCode().trim().equals(pCode.getCode())) {
                    if (pCode.getName().equals(province.getName().trim())){
                        provinceDao.save(province);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    } else {
                        Province p2 = provinceDao.findByName(province.getName().trim());
                        if (p2 != null) {
                            return responMessage.DUPLICATE("NAMA");
                        } else {
                            provinceDao.save(province);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    }
                } else {
                    Province proCode = provinceDao.findByCode(province.getCode().trim());
                    if (proCode != null) {
                        return responMessage.DUPLICATE("CODE");
                    } else {
                        if (!province.getName().trim().equals(pCode.getName())) {
                            Province p2 = provinceDao.findByName(province.getName().trim());
                            if (p2 != null) {
                                return responMessage.DUPLICATE("NAMA");
                            } else {
                                provinceDao.save(province);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        } else {
                            provinceDao.save(province);
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
        if (id == null) {
            return responMessage.BAD_REUQEST();
        } else {
            Province pCode = provinceDao.findId(id);
            if (pCode != null) {
                provinceDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        Province pCode = provinceDao.findId(id);
        if (pCode != null) {
            return responMessage.SUCCESS_GET(provinceDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
