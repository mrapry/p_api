package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.vessel.VesselTransmitter;
import com.psdkp.kkp.apipsdkp.repository.vessel.TransmitterDao;
import com.psdkp.kkp.apipsdkp.service.vessel.VesselTransmitterService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VesselTransmitterServiceImpl implements VesselTransmitterService {

    @Autowired
    private TransmitterDao transmitterDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(transmitterDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(VesselTransmitter vesselTransmitter) {
        if (vesselTransmitter.getTransmitterCode().equals("") || vesselTransmitter.getSkatCode().equals("") || vesselTransmitter.getSkatExp().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            VesselTransmitter tCode = transmitterDao.findByTransmitterCode(vesselTransmitter.getTransmitterCode());
            VesselTransmitter tSkatCode = transmitterDao.findBySkatCode(vesselTransmitter.getSkatCode());
            if (tCode != null) {
                return responMessage.DUPLICATE("KODE TRANSMITTER");
            } else if (tSkatCode != null) {
                return responMessage.DUPLICATE("KODE SKAT");
            } else {
                transmitterDao.save(vesselTransmitter);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(VesselTransmitter vesselTransmitter) {
        if (vesselTransmitter.getId() == null || vesselTransmitter.getTransmitterCode().equals("") || vesselTransmitter.getSkatCode().equals("") || vesselTransmitter.getSkatExp().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            VesselTransmitter tId = transmitterDao.findId(vesselTransmitter.getId());
            if (tId != null) {
                if (!tId.getTransmitterCode().equals(vesselTransmitter.getTransmitterCode())) {
                    VesselTransmitter tCode = transmitterDao.findByTransmitterCode(vesselTransmitter.getTransmitterCode());
                    if (tCode != null) {
                        return responMessage.DUPLICATE("KODE TRANSMITTER");
                    } else {
                        if (!tId.getSkatCode().equals(vesselTransmitter.getSkatCode())) {
                            VesselTransmitter tSkatCode = transmitterDao.findBySkatCode(vesselTransmitter.getSkatCode());
                            if (tSkatCode != null) {
                                return responMessage.DUPLICATE("KODE SKAT");
                            } else {
                                transmitterDao.save(vesselTransmitter);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        } else {
                            transmitterDao.save(vesselTransmitter);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    }
                } else {
                    if (!tId.getSkatCode().equals(vesselTransmitter.getSkatCode())) {
                        VesselTransmitter tSkatCode = transmitterDao.findBySkatCode(vesselTransmitter.getSkatCode());
                        if (tSkatCode != null) {
                            return responMessage.DUPLICATE("KODE SKAT");
                        } else {
                            transmitterDao.save(vesselTransmitter);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    } else {
                        transmitterDao.save(vesselTransmitter);
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
            VesselTransmitter vesselTransmitter = transmitterDao.findId(id);
            if (vesselTransmitter != null) {
                transmitterDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        VesselTransmitter vt = transmitterDao.findId(id);
        if (vt != null) {
            return responMessage.SUCCESS_GET(transmitterDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
