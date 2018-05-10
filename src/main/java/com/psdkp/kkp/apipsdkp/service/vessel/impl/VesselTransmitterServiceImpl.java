package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.vessel.VesselTransmitter;
import com.psdkp.kkp.apipsdkp.repository.vessel.VesselTransmitterDao;
import com.psdkp.kkp.apipsdkp.service.vessel.VesselTransmitterService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VesselTransmitterServiceImpl implements VesselTransmitterService {

    @Autowired
    private VesselTransmitterDao vesselTransmitterDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(vesselTransmitterDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(VesselTransmitter vesselTransmitter) {
        if (vesselTransmitter.getTransmitterCode().equals("") || vesselTransmitter.getSkatCode().equals("") || vesselTransmitter.getSkatExp().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            VesselTransmitter tCode = vesselTransmitterDao.findByTransmitterCode(vesselTransmitter.getTransmitterCode());
            VesselTransmitter tSkatCode = vesselTransmitterDao.findBySkatCode(vesselTransmitter.getSkatCode());
            if (tCode != null) {
                return responMessage.DUPLICATE("KODE TRANSMITTER");
            } else if (tSkatCode != null) {
                return responMessage.DUPLICATE("KODE SKAT");
            } else {
                vesselTransmitterDao.save(vesselTransmitter);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(VesselTransmitter vesselTransmitter) {
        if (vesselTransmitter.getId() == null || vesselTransmitter.getTransmitterCode().equals("") || vesselTransmitter.getSkatCode().equals("") || vesselTransmitter.getSkatExp().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            VesselTransmitter tId = vesselTransmitterDao.findId(vesselTransmitter.getId());
            if (tId != null) {
                if (!tId.getTransmitterCode().equals(vesselTransmitter.getTransmitterCode())) {
                    VesselTransmitter tCode = vesselTransmitterDao.findByTransmitterCode(vesselTransmitter.getTransmitterCode());
                    if (tCode != null) {
                        return responMessage.DUPLICATE("KODE TRANSMITTER");
                    } else {
                        if (!tId.getSkatCode().equals(vesselTransmitter.getSkatCode())) {
                            VesselTransmitter tSkatCode = vesselTransmitterDao.findBySkatCode(vesselTransmitter.getSkatCode());
                            if (tSkatCode != null) {
                                return responMessage.DUPLICATE("KODE SKAT");
                            } else {
                                vesselTransmitterDao.save(vesselTransmitter);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        } else {
                            vesselTransmitterDao.save(vesselTransmitter);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    }
                } else {
                    if (!tId.getSkatCode().equals(vesselTransmitter.getSkatCode())) {
                        VesselTransmitter tSkatCode = vesselTransmitterDao.findBySkatCode(vesselTransmitter.getSkatCode());
                        if (tSkatCode != null) {
                            return responMessage.DUPLICATE("KODE SKAT");
                        } else {
                            vesselTransmitterDao.save(vesselTransmitter);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    } else {
                        vesselTransmitterDao.save(vesselTransmitter);
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
            VesselTransmitter vesselTransmitter = vesselTransmitterDao.findId(id);
            if (vesselTransmitter != null) {
                vesselTransmitterDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        VesselTransmitter vt = vesselTransmitterDao.findId(id);
        if (vt != null) {
            return responMessage.SUCCESS_GET(vesselTransmitterDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }

    @Override
    public Object findByCode(String code) {
        VesselTransmitter vCode = vesselTransmitterDao.findCode(code);
        if (vCode != null) {
            return responMessage.SUCCESS_GET(vCode);
        } else {
            return responMessage.NOT_FOUND("KODE TRANSMITTER");
        }
    }
}
