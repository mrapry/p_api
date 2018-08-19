package com.psdkp.kkp.apipsdkp.service.address.impl;

import com.psdkp.kkp.apipsdkp.domain.address.District;
import com.psdkp.kkp.apipsdkp.repository.address.DistrictDao;
import com.psdkp.kkp.apipsdkp.service.address.DistrictService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(districtDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(District district) {
        if (district.getName().trim().equals("") || district.getCode().trim().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            District p = districtDao.findByCode(district.getCode().trim());
            if (p != null) {
                return responMessage.DUPLICATE("KODE");
            } else {
                District p2 = districtDao.findByName(district.getName().trim());
                if (p2 != null) {
                    return responMessage.DUPLICATE("NAMA");
                } else {
                    districtDao.save(district);
                    return responMessage.SUCCESS_PROCESS_DATA();
                }
            }
        }
    }

    @Override
    public Object findAllByCityId(Integer id) {
        return responMessage.SUCCESS_GET(districtDao.findAllByCityId(id));
    }

    @Override
    public Object edit(District district) {
        if (district.getId() == null || district.getName().trim().equals("") || district.getCode().trim().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            District cId = districtDao.findId(district.getId());
            if (cId != null) {
                District dCode = districtDao.findByCode(district.getCode().trim());
                if (!cId.getCode().equals(district.getCode().trim())) {
                    if (dCode != null) {
                        return responMessage.DUPLICATE("KODE");
                    } else {
                        District dName = districtDao.findByName(district.getName().trim());
                        if (!cId.getName().equals(district.getName().trim())) {
                            if (dName != null) {
                                return responMessage.DUPLICATE("NAMA");
                            } else {
                                districtDao.save(district);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        } else {
                            districtDao.save(district);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    }
                } else {
                    District dName = districtDao.findByName(district.getName().trim());
                    if (!cId.getName().equals(district.getName().trim())) {
                        if (dName != null) {
                            return responMessage.DUPLICATE("NAMA");
                        } else {
                            districtDao.save(district);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    } else {
                        districtDao.save(district);
                        return responMessage.SUCCESS_PROCESS_DATA();
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
            District pCode = districtDao.findId(id);
            if (pCode != null) {
                districtDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        District pCode = districtDao.findId(id);
        if (pCode != null) {
            return responMessage.SUCCESS_GET(districtDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
