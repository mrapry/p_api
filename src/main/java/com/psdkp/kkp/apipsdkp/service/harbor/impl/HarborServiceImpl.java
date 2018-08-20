package com.psdkp.kkp.apipsdkp.service.harbor.impl;

//import com.github.kevinsawicki.http.HttpRequest;

import com.psdkp.kkp.apipsdkp.domain.harbor.Harbor;
import com.psdkp.kkp.apipsdkp.domain.harbor.HarborType;
//import com.psdkp.kkp.apipsdkp.domain.harbor.responseCity.ResponseCity;
//import com.psdkp.kkp.apipsdkp.domain.harbor.responseCity.Data;
import com.psdkp.kkp.apipsdkp.repository.harbor.HarborDao;
import com.psdkp.kkp.apipsdkp.repository.harbor.HarborTypeDao;
import com.psdkp.kkp.apipsdkp.service.harbor.HarborService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HarborServiceImpl implements HarborService {

    @Autowired
    private HarborDao harborDao;

    @Autowired
    private HarborTypeDao harborTypeDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(harborDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(Harbor harbor) {
        if (harbor.getCode().equals("") || harbor.getName().equals("") || harbor.getAddress().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Harbor hCode = harborDao.findByCode(harbor.getCode());
            Harbor hName = harborDao.findByName(harbor.getName());
            HarborType typeId = harborTypeDao.findId(harbor.getHarborType().getId());

//            String response = HttpRequest.get("http://localhost:9001/address/province?id=1").accept("application/json").body();
//            Gson gson = new Gson();
//            ResponseCity rc = gson.fromJson(response, ResponseCity.class);
//            Data u10 = rc.data;
//
//            if (hCode != null) {
//                return responMessage.DUPLICATE("KODE");
//            } else if (hName != null) {
//                return responMessage.DUPLICATE("NAMA");
//            } else if (cId == null) {
//                return responMessage.NOT_FOUND("KOTA");
//            } else if (typeId == null) {
//                return responMessage.NOT_FOUND("TYPE HARBOR");
//            } else {
//                harborDao.save(harbor);
//                return responMessage.SUCCESS_PROCESS_DATA();
//            }
            return null;
        }
    }

    @Override
    public Object edit(Harbor harbor) {
        if (harbor.getId() == null || harbor.getCode().equals("") || harbor.getName().equals("") || harbor.getAddress().equals("") || harbor.getHarborType().getId() == null) {
            return responMessage.BAD_REUQEST();
        } else {
            Harbor hId = harborDao.findId(harbor.getId());

//            if (hId != null){
//                Harbor hCode = harborDao.findByCode(harbor.getCode());
//                Harbor hName = harborDao.findByName(harbor.getName());
//                City cId = cityDao.findId(harbor.getCity().getId());
//                HarborType typeId = harborTypeDao.findId(harbor.getHarborType().getId());
//
//                if (typeId != null){
//                    if (cId != null){
//                        if (!hId.getCode().equals(harbor.getCode())){
//                            if (hCode != null){
//                                return responMessage.DUPLICATE("KODE");
//                            } else {
//                                if (!hId.getName().equals(harbor.getName())){
//                                    if (hName != null){
//                                        return responMessage.DUPLICATE("NAMA");
//                                    } else {
//                                        harborDao.save(harbor);
//                                        return responMessage.SUCCESS_PROCESS_DATA();
//                                    }
//                                } else {
//                                    harborDao.save(harbor);
//                                    return responMessage.SUCCESS_PROCESS_DATA();
//                                }
//                            }
//                        } else {
//                            if (!hId.getName().equals(harbor.getName())){
//                                if (hName != null){
//                                    return responMessage.DUPLICATE("NAMA");
//                                } else {
//                                    harborDao.save(harbor);
//                                    return responMessage.SUCCESS_PROCESS_DATA();
//                                }
//                            } else {
//                                harborDao.save(harbor);
//                                return responMessage.SUCCESS_PROCESS_DATA();
//                            }
//                        }
//                    } else {
//                        return responMessage.NOT_FOUND("KOTA");
//                    }
//                } else {
//                    return responMessage.NOT_FOUND("TYPE");
//                }
//            } else {
//                return responMessage.NOT_FOUND("ID");
//            }
            return null;
        }
    }

    @Override
    public Object del(Integer id) {
        if (id == null) {
            return responMessage.BAD_REUQEST();
        } else {
            Harbor hId = harborDao.findId(id);
            if (hId != null) {
                harborDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        Harbor hId = harborDao.findId(id);
        if (hId != null) {
            return responMessage.SUCCESS_GET(harborDao.findId(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }

    @Override
    public Object findByTypeId(Integer typeId) {
        Harbor harborTypeId = harborDao.findByType(typeId);
        if (harborTypeId != null){
            return responMessage.SUCCESS_GET(harborTypeId);
        } else {
            return responMessage.NOT_FOUND("ID TYPE");
        }
    }
}
