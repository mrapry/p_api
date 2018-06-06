package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.vessel.*;
import com.psdkp.kkp.apipsdkp.repository.vessel.*;
import com.psdkp.kkp.apipsdkp.service.vessel.VesselService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VesselServiceImpl implements VesselService {

    /*Mandatori
    - code
    - name
    - selarSignCode
    - engineCode
    - TransmiterId --> di Transmiter*/

    @Autowired
    private VesselDao vesselDao;

    @Autowired
    private VesselTransmitterDao vesselTransmitterDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private VesselTypeDao vesselTypeDao;

    @Autowired
    private VesselMaterialDao vesselMaterialDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(vesselDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(Vessel vessel) {
        if (vessel.getCode().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Vessel vCode = vesselDao.findByCode(vessel.getCode());
            Vessel vName = vesselDao.findByName(vessel.getName());
            Vessel vSelarSign = vesselDao.findBySelarSignCode(vessel.getSelarSignCode());
            Vessel vEngineCode = vesselDao.findByEngineCode(vessel.getEngineCode());
            Vessel vTransmitter = vesselDao.findByTransmitter(vessel.getVesselTransmitter().getId());

            VesselTransmitter getTransmitter = vesselTransmitterDao.findId(vessel.getVesselTransmitter().getId());
            Company getCompany = companyDao.findId(vessel.getCompany().getId());
            VesselType getType = vesselTypeDao.findId(vessel.getVesselType().getId());
            VesselMaterial getMaterial = vesselMaterialDao.findId(vessel.getVesselMaterial().getId());


            if (getCompany != null) {
                if (getTransmitter != null) {
                    if (getType != null) {
                        if (getMaterial != null) {
                            if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                return responMessage.BAD_REUQEST();
                            } else {
                                if (vCode != null) {
                                    return responMessage.DUPLICATE("KODE");
                                } else {
                                    if (vName != null) {
                                        return responMessage.DUPLICATE("NAMA");
                                    } else {
                                        if (vSelarSign != null) {
                                            return responMessage.DUPLICATE("NOMOR SELAR");
                                        } else {
                                            if (vEngineCode != null) {
                                                return responMessage.DUPLICATE("NOMOR MESIN");
                                            } else {
                                                if (vTransmitter != null) {
                                                    return responMessage.DUPLICATE("TRANSMITTER");
                                                } else {
                                                    vesselDao.save(vessel);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            return responMessage.NOT_FOUND("BAHAN KAPAL");
                        }
                    } else {
                        return responMessage.NOT_FOUND("TYPE KAPAL");
                    }
                } else {
                    return responMessage.NOT_FOUND("TRANSMITTER");
                }
            } else {
                return responMessage.NOT_FOUND("PERUSAHAAN");
            }
        }

    }

    @Override
    public Object edit(Vessel vessel) {
        if (vessel.getId() == null) {
            return responMessage.BAD_REUQEST();
        } else {
            Vessel vId = vesselDao.findId(vessel.getId());
            if (vId != null) {
                Vessel vCode = vesselDao.findByCode(vessel.getCode());
                Vessel vName = vesselDao.findByName(vessel.getName());
                Vessel vSelarSign = vesselDao.findBySelarSignCode(vessel.getSelarSignCode());
                Vessel vEngineCode = vesselDao.findByEngineCode(vessel.getEngineCode());
                Vessel vTransmitter = vesselDao.findByTransmitter(vessel.getVesselTransmitter().getId());

                VesselTransmitter getTransmitter = vesselTransmitterDao.findId(vessel.getVesselTransmitter().getId());
                Company getCompany = companyDao.findId(vessel.getCompany().getId());
                VesselType getType = vesselTypeDao.findId(vessel.getVesselType().getId());
                VesselMaterial getMaterial = vesselMaterialDao.findId(vessel.getVesselMaterial().getId());
                if (getCompany != null) {
                    if (getTransmitter != null) {
                        if (getType != null) {
                            if (getMaterial != null) {
                                if (!vId.getCode().equals(vessel.getCode())) {
                                    if (vCode != null) {
                                        return responMessage.DUPLICATE("KODE");
                                    } else {
                                        if (!vId.getName().equals(vessel.getName())) {
                                            if (vName != null) {
                                                return responMessage.DUPLICATE("NAMA");
                                            } else {
                                                if (!vId.getSelarSignCode().equals(vessel.getSelarSignCode())) {
                                                    if (vSelarSign != null) {
                                                        return responMessage.DUPLICATE("NOMOR SELAR");
                                                    } else {
                                                        if (!vId.getEngineCode().equals(vessel.getEngineCode())) {
                                                            if (vEngineCode != null) {
                                                                return responMessage.DUPLICATE("NOMOR MESIN");
                                                            } else {
                                                                if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                                    if (vTransmitter != null) {
                                                                        return responMessage.DUPLICATE("TRANSMITTER");
                                                                    } else {
                                                                        if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                            return responMessage.NOT_ALLOW();
                                                                        } else {
                                                                            vesselDao.save(vessel);
                                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                                        }
                                                                    }
                                                                } else {
                                                                    vesselDao.save(vessel);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            }
                                                        } else {
                                                            if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                                if (vTransmitter != null) {
                                                                    return responMessage.DUPLICATE("TRANSMITTER");
                                                                } else {
                                                                    if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                        return responMessage.NOT_ALLOW();
                                                                    } else {
                                                                        vesselDao.save(vessel);
                                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                                    }
                                                                }
                                                            } else {
                                                                vesselDao.save(vessel);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (!vId.getEngineCode().equals(vessel.getEngineCode())) {
                                                        if (vEngineCode != null) {
                                                            return responMessage.DUPLICATE("NOMOR MESIN");
                                                        } else {
                                                            if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                                if (vTransmitter != null) {
                                                                    return responMessage.DUPLICATE("TRANSMITTER");
                                                                } else {
                                                                    if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                        return responMessage.NOT_ALLOW();
                                                                    } else {
                                                                        vesselDao.save(vessel);
                                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                                    }
                                                                }
                                                            } else {
                                                                vesselDao.save(vessel);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                            if (vTransmitter != null) {
                                                                return responMessage.DUPLICATE("TRANSMITTER");
                                                            } else {
                                                                if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                    return responMessage.NOT_ALLOW();
                                                                } else {
                                                                    vesselDao.save(vessel);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            }
                                                        } else {
                                                            vesselDao.save(vessel);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!vId.getSelarSignCode().equals(vessel.getSelarSignCode())) {
                                                if (vSelarSign != null) {
                                                    return responMessage.DUPLICATE("NOMOR SELAR");
                                                } else {
                                                    if (!vId.getEngineCode().equals(vessel.getEngineCode())) {
                                                        if (vEngineCode != null) {
                                                            return responMessage.DUPLICATE("NOMOR MESIN");
                                                        } else {
                                                            if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                                if (vTransmitter != null) {
                                                                    return responMessage.DUPLICATE("TRANSMITTER");
                                                                } else {
                                                                    if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                        return responMessage.NOT_ALLOW();
                                                                    } else {
                                                                        vesselDao.save(vessel);
                                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                                    }
                                                                }
                                                            } else {
                                                                vesselDao.save(vessel);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                            if (vTransmitter != null) {
                                                                return responMessage.DUPLICATE("TRANSMITTER");
                                                            } else {
                                                                if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                    return responMessage.NOT_ALLOW();
                                                                } else {
                                                                    vesselDao.save(vessel);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            }
                                                        } else {
                                                            vesselDao.save(vessel);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!vId.getEngineCode().equals(vessel.getEngineCode())) {
                                                    if (vEngineCode != null) {
                                                        return responMessage.DUPLICATE("NOMOR MESIN");
                                                    } else {
                                                        if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                            if (vTransmitter != null) {
                                                                return responMessage.DUPLICATE("TRANSMITTER");
                                                            } else {
                                                                if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                    return responMessage.NOT_ALLOW();
                                                                } else {
                                                                    vesselDao.save(vessel);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            }
                                                        } else {
                                                            vesselDao.save(vessel);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                        if (vTransmitter != null) {
                                                            return responMessage.DUPLICATE("TRANSMITTER");
                                                        } else {
                                                            if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                return responMessage.NOT_ALLOW();
                                                            } else {
                                                                vesselDao.save(vessel);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        vesselDao.save(vessel);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (!vId.getName().equals(vessel.getName())) {
                                        if (vName != null) {
                                            return responMessage.DUPLICATE("NAMA");
                                        } else {
                                            if (!vId.getSelarSignCode().equals(vessel.getSelarSignCode())) {
                                                if (vSelarSign != null) {
                                                    return responMessage.DUPLICATE("NOMOR SELAR");
                                                } else {
                                                    if (!vId.getEngineCode().equals(vessel.getEngineCode())) {
                                                        if (vEngineCode != null) {
                                                            return responMessage.DUPLICATE("NOMOR MESIN");
                                                        } else {
                                                            if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                                if (vTransmitter != null) {
                                                                    return responMessage.DUPLICATE("TRANSMITTER");
                                                                } else {
                                                                    if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                        return responMessage.NOT_ALLOW();
                                                                    } else {
                                                                        vesselDao.save(vessel);
                                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                                    }
                                                                }
                                                            } else {
                                                                vesselDao.save(vessel);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                            if (vTransmitter != null) {
                                                                return responMessage.DUPLICATE("TRANSMITTER");
                                                            } else {
                                                                if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                    return responMessage.NOT_ALLOW();
                                                                } else {
                                                                    vesselDao.save(vessel);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            }
                                                        } else {
                                                            vesselDao.save(vessel);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!vId.getEngineCode().equals(vessel.getEngineCode())) {
                                                    if (vEngineCode != null) {
                                                        return responMessage.DUPLICATE("NOMOR MESIN");
                                                    } else {
                                                        if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                            if (vTransmitter != null) {
                                                                return responMessage.DUPLICATE("TRANSMITTER");
                                                            } else {
                                                                if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                    return responMessage.NOT_ALLOW();
                                                                } else {
                                                                    vesselDao.save(vessel);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            }
                                                        } else {
                                                            vesselDao.save(vessel);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                        if (vTransmitter != null) {
                                                            return responMessage.DUPLICATE("TRANSMITTER");
                                                        } else {
                                                            if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                return responMessage.NOT_ALLOW();
                                                            } else {
                                                                vesselDao.save(vessel);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        vesselDao.save(vessel);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (!vId.getSelarSignCode().equals(vessel.getSelarSignCode())) {
                                            if (vSelarSign != null) {
                                                return responMessage.DUPLICATE("NOMOR SELAR");
                                            } else {
                                                if (!vId.getEngineCode().equals(vessel.getEngineCode())) {
                                                    if (vEngineCode != null) {
                                                        return responMessage.DUPLICATE("NOMOR MESIN");
                                                    } else {
                                                        if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                            if (vTransmitter != null) {
                                                                return responMessage.DUPLICATE("TRANSMITTER");
                                                            } else {
                                                                if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                    return responMessage.NOT_ALLOW();
                                                                } else {
                                                                    vesselDao.save(vessel);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            }
                                                        } else {
                                                            vesselDao.save(vessel);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                        if (vTransmitter != null) {
                                                            return responMessage.DUPLICATE("TRANSMITTER");
                                                        } else {
                                                            if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                return responMessage.NOT_ALLOW();
                                                            } else {
                                                                vesselDao.save(vessel);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        vesselDao.save(vessel);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!vId.getEngineCode().equals(vessel.getEngineCode())) {
                                                if (vEngineCode != null) {
                                                    return responMessage.DUPLICATE("NOMOR MESIN");
                                                } else {
                                                    if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                        if (vTransmitter != null) {
                                                            return responMessage.DUPLICATE("TRANSMITTER");
                                                        } else {
                                                            if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                                return responMessage.NOT_ALLOW();
                                                            } else {
                                                                vesselDao.save(vessel);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        vesselDao.save(vessel);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!vId.getVesselTransmitter().getId().equals(vessel.getVesselTransmitter().getId())) {
                                                    if (vTransmitter != null) {
                                                        return responMessage.DUPLICATE("TRANSMITTER");
                                                    } else {
                                                        if (vessel.getGt() >= 30 && vessel.getVesselTransmitter().getId() == null) {
                                                            return responMessage.NOT_ALLOW();
                                                        } else {
                                                            vesselDao.save(vessel);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    vesselDao.save(vessel);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                return responMessage.NOT_FOUND("BAHAN KAPAL");
                            }
                        } else {
                            return responMessage.NOT_FOUND("TYPE KAPAL");
                        }
                    } else {
                        return responMessage.NOT_FOUND("TRANSMITTER");
                    }
                } else {
                    return responMessage.NOT_FOUND("PERUSAHAAN");
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
            Vessel vId = vesselDao.findId(id);
            if (vId != null) {
                vesselDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        Vessel vId = vesselDao.findId(id);
        if (vId != null) {
            return responMessage.SUCCESS_GET(vId);
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
