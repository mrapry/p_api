package com.psdkp.kkp.apipsdkp.service.unitWorking.impl;

import com.psdkp.kkp.apipsdkp.domain.unitWorking.MappingUnitWorking;
import com.psdkp.kkp.apipsdkp.domain.unitWorking.UnitWorking;
import com.psdkp.kkp.apipsdkp.repository.unitWorking.MappingUnitWorkingDao;
import com.psdkp.kkp.apipsdkp.repository.unitWorking.UnitWorkingDao;
import com.psdkp.kkp.apipsdkp.service.unitWorking.MappingUnitWorkingService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MappingUnitWorkingServiceImpl implements MappingUnitWorkingService{

    @Autowired
    private MappingUnitWorkingDao mappingUnitWorkingDao;

    @Autowired
    private UnitWorkingDao unitWorkingDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(mappingUnitWorkingDao.findAllByType(name, pageable));
    }

    @Override
    public Object save(MappingUnitWorking mappingUnitWorking) {
        if (mappingUnitWorking.getParrent().getId()==null|| mappingUnitWorking.getChild().getId()==null){
            return responMessage.BAD_REUQEST();
        } else {
            UnitWorking a = unitWorkingDao.findId(mappingUnitWorking.getParrent().getId());
            UnitWorking b = unitWorkingDao.findId(mappingUnitWorking.getChild().getId());
            if (a !=null){
                if (b != null){
                    if (a.getTypeUnit().getType().equals("UPT")){
                        if (b.getTypeUnit().getType().equals("SATWAS")){
                            mappingUnitWorkingDao.save(mappingUnitWorking);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        } else {
                            return responMessage.NOT_ALLOW();
                        }
                    } else if (a.getTypeUnit().getType().equals("SATWAS")){
                        if (b.getTypeUnit().getType().equals("WILKER")){
                            mappingUnitWorkingDao.save(mappingUnitWorking);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        } else {
                            return responMessage.NOT_ALLOW();
                        }
                    } else {
                        return responMessage.NOT_ALLOW();
                    }
                } else {
                    return responMessage.NOT_FOUND("ID CHILD");
                }
            } else {
                return responMessage.NOT_FOUND("ID PARRENT");
            }
        }
    }

    @Override
    public Object edit(MappingUnitWorking mappingUnitWorking) {
        if (mappingUnitWorking.getId()!=null || mappingUnitWorking.getParrent().getId() !=null || mappingUnitWorking.getChild().getId() !=null){
            return responMessage.BAD_REUQEST();
        } else {
            MappingUnitWorking mapping = mappingUnitWorkingDao.findId(mappingUnitWorking.getId());
            if (mapping !=null){
                UnitWorking a = unitWorkingDao.findId(mappingUnitWorking.getParrent().getId());
                UnitWorking b = unitWorkingDao.findId(mappingUnitWorking.getChild().getId());
                if (a !=null){
                    if (b != null){
                        if (a.getTypeUnit().getType().equals("UPT")){
                            if (b.getTypeUnit().getType().equals("SATWAS")){
                                mappingUnitWorkingDao.save(mappingUnitWorking);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            } else {
                                return responMessage.NOT_ALLOW();
                            }
                        } else if (a.getTypeUnit().getType().equals("SATWAS")){
                            if (b.getTypeUnit().getType().equals("WILKER")){
                                mappingUnitWorkingDao.save(mappingUnitWorking);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            } else {
                                return responMessage.NOT_ALLOW();
                            }
                        } else {
                            return responMessage.NOT_ALLOW();
                        }
                    } else {
                        return responMessage.NOT_FOUND("ID CHILD");
                    }
                } else {
                    return responMessage.NOT_FOUND("ID PARRENT");
                }
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object del(Integer id) {
        if (id != null){
            return responMessage.BAD_REUQEST();
        } else {
            MappingUnitWorking mapping = mappingUnitWorkingDao.findId(id);
            if (mapping != null){
                mappingUnitWorkingDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        MappingUnitWorking mapping = mappingUnitWorkingDao.findId(id);
        return responMessage.SUCCESS_GET(mapping);
    }
}
