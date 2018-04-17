package com.psdkp.kkp.apipsdkp.service.unitWorking.impl;

import com.psdkp.kkp.apipsdkp.domain.unitWorking.TypeUnit;
import com.psdkp.kkp.apipsdkp.domain.unitWorking.UnitWorking;
import com.psdkp.kkp.apipsdkp.repository.unitWorking.TypeUnitDao;
import com.psdkp.kkp.apipsdkp.repository.unitWorking.UnitWorkingDao;
import com.psdkp.kkp.apipsdkp.service.unitWorking.UnitWorkingService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UnitWorkingServiceImpl implements UnitWorkingService {

    @Autowired
    private UnitWorkingDao unitWorkingDao;

    @Autowired
    private TypeUnitDao typeUnitDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(unitWorkingDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(UnitWorking unitWorking) {
        if (unitWorking.getName().equals("") || unitWorking.getCode().equals("") || unitWorking.getEmail().equals("") || unitWorking.getPhone().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            UnitWorking u1 = unitWorkingDao.findByCode(unitWorking.getCode());
            UnitWorking u2 = unitWorkingDao.findByName(unitWorking.getName());
            UnitWorking u3 = unitWorkingDao.findByPhone(unitWorking.getPhone());
            UnitWorking u4 = unitWorkingDao.findByFaxmail(unitWorking.getFaxmail());
            UnitWorking u5 = unitWorkingDao.findByEmail(unitWorking.getEmail());
            UnitWorking u8 = unitWorkingDao.findByServiceLocation(unitWorking.getServiceLocation());
            TypeUnit u9 = typeUnitDao.findId(unitWorking.getTypeUnit().getId());

            if (u9 == null) {
                return responMessage.NOT_FOUND("UNIT TYPE");
            } else if (u1 != null) {
                return responMessage.DUPLICATE("KODE");
            } else if (u2 != null){
                return responMessage.DUPLICATE("NAME");
            } else if (u3 != null){
                return responMessage.DUPLICATE("PHONE");
            } else if (u4 != null){
                return responMessage.DUPLICATE("FAXIMAIL");
            } else if (u5 !=null){
                return responMessage.DUPLICATE("EMAIL");
            } else if (u8 != null){
                return responMessage.DUPLICATE("SERVICE LOCATION");
            } else {
                unitWorkingDao.save(unitWorking);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(UnitWorking unitWorking) {
        if (unitWorking.getId() == null || unitWorking.getCode().equals("") || unitWorking.getName().equals("") || unitWorking.getPhone().equals("") || unitWorking.getFaxmail().equals("") || unitWorking.getEmail().equals("") || unitWorking.getLangitude().equals("") || unitWorking.getLongitude().equals("") || unitWorking.getServiceLocation().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            UnitWorking unitId = unitWorkingDao.findId(unitWorking.getId());

            if (unitId != null) {

                TypeUnit uw0 = typeUnitDao.findId(unitWorking.getTypeUnit().getId());
                UnitWorking uw1 = unitWorkingDao.findByCode(unitWorking.getCode());
                UnitWorking uw2 = unitWorkingDao.findByName(unitWorking.getName());
                UnitWorking uw3 = unitWorkingDao.findByPhone(unitWorking.getPhone());
                UnitWorking uw4 = unitWorkingDao.findByFaxmail(unitWorking.getFaxmail());
                UnitWorking uw5 = unitWorkingDao.findByEmail(unitWorking.getEmail());
                UnitWorking uw6 = unitWorkingDao.findByServiceLocation(unitWorking.getServiceLocation());

                if (uw0 == null) {
                    return responMessage.NOT_FOUND("UNIT TYPE");
                } else {
                    if (!unitId.getCode().equals(unitWorking.getCode())) {
                        if (uw1 != null) {
                            return responMessage.DUPLICATE("CODE");
                        } else {
                            if (!unitId.getName().equals(unitWorking.getName())) {
                                if (uw2 != null) {
                                    return responMessage.DUPLICATE("NAME");
                                } else {
                                    if (!unitId.getPhone().equals(unitWorking.getPhone())) {
                                        if (uw3 != null) {
                                            return responMessage.DUPLICATE("PHONE");
                                        } else {
                                            if (!unitWorking.getFaxmail().equals("-") && !unitId.getFaxmail().equals(unitWorking.getFaxmail())) {
                                                if (uw4 != null) {
                                                    return responMessage.DUPLICATE("FAXIMAIL");
                                                } else {
                                                    if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                        if (uw5 != null) {
                                                            return responMessage.DUPLICATE("EMAIL");
                                                        } else {
                                                            if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                                if (uw6 != null) {
                                                                    return responMessage.DUPLICATE("SERVICE LOCATION");
                                                                } else {
                                                                    unitWorkingDao.save(unitWorking);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            } else {
                                                                unitWorkingDao.save(unitWorking);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                            if (uw6 != null) {
                                                                return responMessage.DUPLICATE("SERVICE LOCATION");
                                                            } else {
                                                                unitWorkingDao.save(unitWorking);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                    if (uw5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                            if (uw6 != null) {
                                                                return responMessage.DUPLICATE("SERVICE LOCATION");
                                                            } else {
                                                                unitWorkingDao.save(unitWorking);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                        if (uw6 != null) {
                                                            return responMessage.DUPLICATE("SERVICE LOCATION");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (!unitWorking.getFaxmail().equals("-") && !unitId.getFaxmail().equals(unitWorking.getFaxmail())) {
                                            if (uw4 != null) {
                                                return responMessage.DUPLICATE("FAXIMAIL");
                                            } else {
                                                if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                    if (uw5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                            if (uw6 != null) {
                                                                return responMessage.DUPLICATE("SERVICE LOCATION");
                                                            } else {
                                                                unitWorkingDao.save(unitWorking);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                        if (uw6 != null) {
                                                            return responMessage.DUPLICATE("SERVICE LOCATION");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                        if (uw6 != null) {
                                                            return responMessage.DUPLICATE("SERVICE LOCATION");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                    if (uw6 != null) {
                                                        return responMessage.DUPLICATE("SERVICE LOCATION");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!unitId.getPhone().equals(unitWorking.getPhone())) {
                                    if (uw3 != null) {
                                        return responMessage.DUPLICATE("PHONE");
                                    } else {
                                        if (!unitWorking.getFaxmail().equals("-") && !unitId.getFaxmail().equals(unitWorking.getFaxmail())) {
                                            if (uw4 != null) {
                                                return responMessage.DUPLICATE("FAXIMAIL");
                                            } else {
                                                if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                    if (uw5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                            if (uw6 != null) {
                                                                return responMessage.DUPLICATE("SERVICE LOCATION");
                                                            } else {
                                                                unitWorkingDao.save(unitWorking);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                        if (uw6 != null) {
                                                            return responMessage.DUPLICATE("SERVICE LOCATION");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                        if (uw6 != null) {
                                                            return responMessage.DUPLICATE("SERVICE LOCATION");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                    if (uw6 != null) {
                                                        return responMessage.DUPLICATE("SERVICE LOCATION");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (!unitWorking.getFaxmail().equals("-") && !unitId.getFaxmail().equals(unitWorking.getFaxmail())) {
                                        if (uw4 != null) {
                                            return responMessage.DUPLICATE("FAXIMAIL");
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                        if (uw6 != null) {
                                                            return responMessage.DUPLICATE("SERVICE LOCATION");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                    if (uw6 != null) {
                                                        return responMessage.DUPLICATE("SERVICE LOCATION");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    } else {
                                        if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                            if (uw5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                    if (uw6 != null) {
                                                        return responMessage.DUPLICATE("SERVICE LOCATION");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                if (uw6 != null) {
                                                    return responMessage.DUPLICATE("SERVICE LOCATION");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!unitId.getName().equals(unitWorking.getName())) {
                            if (uw2.getName() != null) {
                                return responMessage.DUPLICATE("NAME");
                            } else {
                                if (!unitId.getPhone().equals(unitWorking.getPhone())) {
                                    if (uw3 != null) {
                                        return responMessage.DUPLICATE("PHONE");
                                    } else {
                                        if (!unitWorking.getFaxmail().equals("-") && !unitId.getFaxmail().equals(unitWorking.getFaxmail())) {
                                            if (uw4 != null) {
                                                return responMessage.DUPLICATE("FAXIMAIL");
                                            } else {
                                                if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                    if (uw5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                            if (uw6 != null) {
                                                                return responMessage.DUPLICATE("SERVICE LOCATION");
                                                            } else {
                                                                unitWorkingDao.save(unitWorking);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                        if (uw6 != null) {
                                                            return responMessage.DUPLICATE("SERVICE LOCATION");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                        if (uw6 != null) {
                                                            return responMessage.DUPLICATE("SERVICE LOCATION");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                    if (uw6 != null) {
                                                        return responMessage.DUPLICATE("SERVICE LOCATION");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (!unitWorking.getFaxmail().equals("-") && !unitId.getFaxmail().equals(unitWorking.getFaxmail())) {
                                        if (uw4 != null) {
                                            return responMessage.DUPLICATE("FAXIMAIL");
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                        if (uw6 != null) {
                                                            return responMessage.DUPLICATE("SERVICE LOCATION");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                    if (uw6 != null) {
                                                        return responMessage.DUPLICATE("SERVICE LOCATION");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    } else {
                                        if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                            if (uw5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                    if (uw6 != null) {
                                                        return responMessage.DUPLICATE("SERVICE LOCATION");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                if (uw6 != null) {
                                                    return responMessage.DUPLICATE("SERVICE LOCATION");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!unitId.getPhone().equals(unitWorking.getPhone())) {
                                if (uw3 != null) {
                                    return responMessage.DUPLICATE("PHONE");
                                } else {
                                    if (!unitWorking.getFaxmail().equals("-") && !unitId.getFaxmail().equals(unitWorking.getFaxmail())) {
                                        if (uw4 != null) {
                                            return responMessage.DUPLICATE("FAXIMAIL");
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                        if (uw6 != null) {
                                                            return responMessage.DUPLICATE("SERVICE LOCATION");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                    if (uw6 != null) {
                                                        return responMessage.DUPLICATE("SERVICE LOCATION");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    } else {
                                        if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                            if (uw5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                    if (uw6 != null) {
                                                        return responMessage.DUPLICATE("SERVICE LOCATION");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                if (uw6 != null) {
                                                    return responMessage.DUPLICATE("SERVICE LOCATION");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!unitWorking.getFaxmail().equals("-") && !unitId.getFaxmail().equals(unitWorking.getFaxmail())) {
                                    if (uw4 != null) {
                                        return responMessage.DUPLICATE("FAXIMAIL");
                                    } else {
                                        if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                            if (uw5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                    if (uw6 != null) {
                                                        return responMessage.DUPLICATE("SERVICE LOCATION");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                if (uw6 != null) {
                                                    return responMessage.DUPLICATE("SERVICE LOCATION");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                } else {
                                    if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                        if (uw5 != null) {
                                            return responMessage.DUPLICATE("EMAIL");
                                        } else {
                                            if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                                if (uw6 != null) {
                                                    return responMessage.DUPLICATE("SERVICE LOCATION");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    } else {
                                        if (!unitId.getServiceLocation().equals(unitWorking.getServiceLocation())) {
                                            if (uw6 != null) {
                                                return responMessage.DUPLICATE("SERVICE LOCATION");
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        } else {
                                            unitWorkingDao.save(unitWorking);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    }
                                }
                            }
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
            UnitWorking unitWorking = unitWorkingDao.findId(id);
            if (unitWorking != null) {
                unitWorkingDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        UnitWorking unitWorking = unitWorkingDao.findId(id);
        if (unitWorking != null) {
            return responMessage.SUCCESS_GET(unitWorkingDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
