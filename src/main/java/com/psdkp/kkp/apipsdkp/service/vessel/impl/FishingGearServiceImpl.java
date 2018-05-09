package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.vessel.FishingGear;
import com.psdkp.kkp.apipsdkp.domain.vessel.FishingGearGt;
import com.psdkp.kkp.apipsdkp.repository.vessel.FishingGearDao;
import com.psdkp.kkp.apipsdkp.repository.vessel.FishingGearGtDao;
import com.psdkp.kkp.apipsdkp.repository.vessel.VesselTypeDao;
import com.psdkp.kkp.apipsdkp.service.vessel.FishingGearService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FishingGearServiceImpl implements FishingGearService {

    @Autowired
    private FishingGearDao fishingGearDao;

    @Autowired
    private FishingGearGtDao fishingGearGtDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(fishingGearDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(FishingGear fishingGear) {
        if (fishingGear.getCode().equals("") || fishingGear.getName().equals("") || fishingGear.getNick().equals("") || fishingGear.getEnglishName().equals("") || fishingGear.getStatus().equals("") || fishingGear.getFeature().equals("") || fishingGear.getFishingGearGts() == null || fishingGear.getParentId() == null) {
            return responMessage.BAD_REUQEST();
        } else {
            FishingGear fg2 = fishingGearDao.findByCode(fishingGear.getCode());
            FishingGear fg3 = fishingGearDao.findByName(fishingGear.getName());
            FishingGear fg4 = fishingGearDao.findByNick(fishingGear.getNick());
            FishingGear fg5 = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());

            if (fg2 != null) {
                return responMessage.DUPLICATE("KODE");
            } else if (fg3 != null) {
                return responMessage.DUPLICATE("NAMA");
            } else if (fg4 != null) {
                return responMessage.DUPLICATE("NAMA PANGGILAN");
            } else if (fg5 != null) {
                return responMessage.DUPLICATE("NAMA ASING");
            } else {
                fishingGearDao.save(fishingGear);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(FishingGear fishingGear) {
        if (fishingGear.getId() == null || fishingGear.getCode().equals("") || fishingGear.getName().equals("") || fishingGear.getNick().equals("") || fishingGear.getEnglishName().equals("") || fishingGear.getStatus().equals("") || fishingGear.getFeature().equals("") || fishingGear.getFishingGearGts() == null || fishingGear.getParentId() == null) {
            return responMessage.BAD_REUQEST();
        } else {
            FishingGear fId = fishingGearDao.findId(fishingGear.getId());
            if (fId != null) {
                if (!fId.getCode().equals(fishingGear.getCode())) {
                    FishingGear fCode = fishingGearDao.findByCode(fishingGear.getCode());
                    if (fCode != null) {
                        return responMessage.DUPLICATE("CODE");
                    } else {
                        if (!fId.getName().equals(fishingGear.getName())) {
                            FishingGear fName = fishingGearDao.findByName(fishingGear.getName());
                            if (fName != null) {
                                return responMessage.DUPLICATE("NAMA");
                            } else {
                                if (!fId.getNick().equals(fishingGear.getNick())) {
                                    FishingGear fNick = fishingGearDao.findByNick(fishingGear.getNick());
                                    if (fNick != null) {
                                        return responMessage.DUPLICATE("NAMA PANGGILAN");
                                    } else {
                                        if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                            FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                            if (fEngName != null) {
                                                return responMessage.DUPLICATE("NAMA ASING");
                                            } else {
                                                fishingGearDao.save(fishingGear);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        } else {
                                            fishingGearDao.save(fishingGear);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    }
                                } else {
                                    if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                        FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                        if (fEngName != null) {
                                            return responMessage.DUPLICATE("NAMA ASING");
                                        } else {
                                            fishingGearDao.save(fishingGear);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                }
                            }
                        } else {
                            if (!fId.getNick().equals(fishingGear.getNick())) {
                                FishingGear fNick = fishingGearDao.findByNick(fishingGear.getNick());
                                if (fNick != null) {
                                    return responMessage.DUPLICATE("NAMA PANGGILAN");
                                } else {
                                    if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                        FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                        if (fEngName != null) {
                                            return responMessage.DUPLICATE("NAMA ASING");
                                        } else {
                                            fishingGearDao.save(fishingGear);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                }
                            } else {
                                if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                    FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                    if (fEngName != null) {
                                        return responMessage.DUPLICATE("NAMA ASING");
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                } else {
                                    fishingGearDao.save(fishingGear);
                                    return responMessage.SUCCESS_PROCESS_DATA();
                                }
                            }
                        }
                    }
                } else {
                    if (!fId.getName().equals(fishingGear.getName())) {
                        FishingGear fName = fishingGearDao.findByName(fishingGear.getName());
                        if (fName != null) {
                            return responMessage.DUPLICATE("NAMA");
                        } else {
                            if (!fId.getNick().equals(fishingGear.getNick())) {
                                FishingGear fNick = fishingGearDao.findByNick(fishingGear.getNick());
                                if (fNick != null) {
                                    return responMessage.DUPLICATE("NAMA PANGGILAN");
                                } else {
                                    if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                        FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                        if (fEngName != null) {
                                            return responMessage.DUPLICATE("NAMA ASING");
                                        } else {
                                            fishingGearDao.save(fishingGear);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                }
                            } else {
                                if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                    FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                    if (fEngName != null) {
                                        return responMessage.DUPLICATE("NAMA ASING");
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                } else {
                                    fishingGearDao.save(fishingGear);
                                    return responMessage.SUCCESS_PROCESS_DATA();
                                }
                            }
                        }
                    } else {
                        if (!fId.getNick().equals(fishingGear.getNick())) {
                            FishingGear fNick = fishingGearDao.findByNick(fishingGear.getNick());
                            if (fNick != null) {
                                return responMessage.DUPLICATE("NAMA PANGGILAN");
                            } else {
                                if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                    FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                    if (fEngName != null) {
                                        return responMessage.DUPLICATE("NAMA ASING");
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                } else {
                                    fishingGearDao.save(fishingGear);
                                    return responMessage.SUCCESS_PROCESS_DATA();
                                }
                            }
                        } else {
                            if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                if (fEngName != null) {
                                    return responMessage.DUPLICATE("NAMA ASING");
                                } else {
                                    fishingGearDao.save(fishingGear);
                                    return responMessage.SUCCESS_PROCESS_DATA();
                                }
                            } else {
                                fishingGearDao.save(fishingGear);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        }
                    }
                }
            } else {
                if (!fId.getCode().equals(fishingGear.getCode())) {
                    FishingGear fCode = fishingGearDao.findByCode(fishingGear.getCode());
                    if (fCode != null) {
                        return responMessage.DUPLICATE("CODE");
                    } else {
                        if (!fId.getName().equals(fishingGear.getName())) {
                            FishingGear fName = fishingGearDao.findByName(fishingGear.getName());
                            if (fName != null) {
                                return responMessage.DUPLICATE("NAMA");
                            } else {
                                if (!fId.getNick().equals(fishingGear.getNick())) {
                                    FishingGear fNick = fishingGearDao.findByNick(fishingGear.getNick());
                                    if (fNick != null) {
                                        return responMessage.DUPLICATE("NAMA PANGGILAN");
                                    } else {
                                        if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                            FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                            if (fEngName != null) {
                                                return responMessage.DUPLICATE("NAMA ASING");
                                            } else {
                                                fishingGearDao.save(fishingGear);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        } else {
                                            fishingGearDao.save(fishingGear);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    }
                                } else {
                                    if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                        FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                        if (fEngName != null) {
                                            return responMessage.DUPLICATE("NAMA ASING");
                                        } else {
                                            fishingGearDao.save(fishingGear);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                }
                            }
                        } else {
                            if (!fId.getNick().equals(fishingGear.getNick())) {
                                FishingGear fNick = fishingGearDao.findByNick(fishingGear.getNick());
                                if (fNick != null) {
                                    return responMessage.DUPLICATE("NAMA PANGGILAN");
                                } else {
                                    if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                        FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                        if (fEngName != null) {
                                            return responMessage.DUPLICATE("NAMA ASING");
                                        } else {
                                            fishingGearDao.save(fishingGear);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                }
                            } else {
                                if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                    FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                    if (fEngName != null) {
                                        return responMessage.DUPLICATE("NAMA ASING");
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                } else {
                                    fishingGearDao.save(fishingGear);
                                    return responMessage.SUCCESS_PROCESS_DATA();
                                }
                            }
                        }
                    }
                } else {
                    if (!fId.getName().equals(fishingGear.getName())) {
                        FishingGear fName = fishingGearDao.findByName(fishingGear.getName());
                        if (fName != null) {
                            return responMessage.DUPLICATE("NAMA");
                        } else {
                            if (!fId.getNick().equals(fishingGear.getNick())) {
                                FishingGear fNick = fishingGearDao.findByNick(fishingGear.getNick());
                                if (fNick != null) {
                                    return responMessage.DUPLICATE("NAMA PANGGILAN");
                                } else {
                                    if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                        FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                        if (fEngName != null) {
                                            return responMessage.DUPLICATE("NAMA ASING");
                                        } else {
                                            fishingGearDao.save(fishingGear);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                }
                            } else {
                                if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                    FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                    if (fEngName != null) {
                                        return responMessage.DUPLICATE("NAMA ASING");
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                } else {
                                    fishingGearDao.save(fishingGear);
                                    return responMessage.SUCCESS_PROCESS_DATA();
                                }
                            }
                        }
                    } else {
                        if (!fId.getNick().equals(fishingGear.getNick())) {
                            FishingGear fNick = fishingGearDao.findByNick(fishingGear.getNick());
                            if (fNick != null) {
                                return responMessage.DUPLICATE("NAMA PANGGILAN");
                            } else {
                                if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                    FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                    if (fEngName != null) {
                                        return responMessage.DUPLICATE("NAMA ASING");
                                    } else {
                                        fishingGearDao.save(fishingGear);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                } else {
                                    fishingGearDao.save(fishingGear);
                                    return responMessage.SUCCESS_PROCESS_DATA();
                                }
                            }
                        } else {
                            if (!fId.getEnglishName().equals(fishingGear.getEnglishName())) {
                                FishingGear fEngName = fishingGearDao.findByEnglishName(fishingGear.getEnglishName());
                                if (fEngName != null) {
                                    return responMessage.DUPLICATE("NAMA ASING");
                                } else {
                                    fishingGearDao.save(fishingGear);
                                    return responMessage.SUCCESS_PROCESS_DATA();
                                }
                            } else {
                                fishingGearDao.save(fishingGear);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Object del(Integer id) {
        if (id == null) {
            return responMessage.BAD_REUQEST();
        } else {
            FishingGear fishingGear = fishingGearDao.findId(id);
            if (fishingGear != null) {
                fishingGearDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        FishingGear fishingGear = fishingGearDao.findId(id);
        if (fishingGear != null) {
            return responMessage.SUCCESS_GET(fishingGearDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }

    @Override
    public Object findByFishingGearGt(Integer id) {
        FishingGearGt fgg = fishingGearGtDao.findId(id);
        if (fgg != null) {
            return responMessage.SUCCESS_GET(fishingGearGtDao.findId(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
