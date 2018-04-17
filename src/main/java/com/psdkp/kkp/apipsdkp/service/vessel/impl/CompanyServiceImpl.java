package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.vessel.Company;
import com.psdkp.kkp.apipsdkp.repository.vessel.CompanyDao;
import com.psdkp.kkp.apipsdkp.service.vessel.CompanyService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;

    private final ResponMessage responMessage;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao, ResponMessage responMessage) {
        this.companyDao = companyDao;
        this.responMessage = responMessage;
    }

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(companyDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(Company company) {

        if (company.getCode().equals("") || company.getName().equals("") || company.getAddress().equals("") || company.getPostCode().equals("") || company.getPhone().equals("") || company.getFaxmail().equals("") || company.getEmail().equals("") || company.getSiup().equals("") || company.getSiupDate().equals("") || company.getPic().equals("") || company.getActive().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Company c0 = companyDao.findByCode(company.getCode());
            Company c1 = companyDao.findByName(company.getName());
            Company c2 = companyDao.findByAddress(company.getAddress());
            Company c3 = companyDao.findByPhone(company.getPhone());
            Company c4 = companyDao.findByFaxmail(company.getFaxmail());
            Company c5 = companyDao.findByEmail(company.getEmail());
            Company c6 = companyDao.findBySiup(company.getSiup());

            if (c0 != null) {
                return responMessage.DUPLICATE("KODE PERUSAHAAN");
            } else if (c1 != null) {
                return responMessage.DUPLICATE("NAMA PERUSAHAAN");
            } else if (c2 != null) {
                return responMessage.DUPLICATE("ALAMAT PERUSAHAAN");
            } else if (c3 != null) {
                return responMessage.DUPLICATE("NOMOR TELEPON");
            } else if (c4 != null) {
                return responMessage.DUPLICATE("FAKSIMILE");
            } else if (c5 != null) {
                return responMessage.DUPLICATE("EMAIL");
            } else if (c6 != null) {
                return responMessage.DUPLICATE("SIUP");
            } else if (company.getPostCode().length() > 5 || company.getPostCode().length() < 5) {
                if (company.getPostCode().length() > 5) {
                    return responMessage.BAD_REUQEST();
                } else {
                    return responMessage.BAD_REUQEST();
                }
            } else {
                companyDao.save(company);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(Company company) {
        if (company.getId() == null || company.getCode().equals("") || company.getName().equals("") || company.getAddress().equals("") || company.getPostCode().equals("") || company.getPhone().equals("") || company.getFaxmail().equals("") || company.getEmail().equals("") || company.getSiup().equals("") || company.getSiupDate().equals("") || company.getPic().equals("") || company.getActive().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Company cId = companyDao.findId(company.getId());
            if (cId != null) {
                Company c1 = companyDao.findByName(company.getName());
                Company c2 = companyDao.findByAddress(company.getAddress());
                Company c3 = companyDao.findByPhone(company.getPhone());
                Company c4 = companyDao.findByFaxmail(company.getFaxmail());
                Company c5 = companyDao.findByEmail(company.getEmail());
                Company c6 = companyDao.findBySiup(company.getSiup());

                if (!cId.getCode().equals(company.getCode())) {
                    Company cCode = companyDao.findByCode(company.getCode());
                    if (cCode != null) {
                        return responMessage.DUPLICATE("KODE");
                    } else {
                        if (!cId.getName().equals(company.getName())) {
                            if (c1 != null) {
                                return responMessage.DUPLICATE("NAMA");
                            } else {
                                if (!cId.getAddress().equals(company.getAddress())) {
                                    if (c2 != null) {
                                        return responMessage.DUPLICATE("ALAMAT");
                                    } else {
                                        if (!cId.getPhone().equals(company.getPhone())) {
                                            if (c3 != null) {
                                                return responMessage.DUPLICATE("TELEPON");
                                            } else {
                                                if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                                    if (c4 != null) {
                                                        return responMessage.DUPLICATE("FAKSMILE");
                                                    } else {
                                                        if (!cId.getEmail().equals(company.getEmail())) {
                                                            if (c5 != null) {
                                                                return responMessage.DUPLICATE("EMAIL");
                                                            } else {
                                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                                    if (c6 != null) {
                                                                        return responMessage.DUPLICATE("SIUP");
                                                                    } else {
                                                                        companyDao.save(company);
                                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                                    }
                                                                } else {
                                                                    companyDao.save(company);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            }
                                                        } else {
                                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                                if (c6 != null) {
                                                                    return responMessage.DUPLICATE("SIUP");
                                                                } else {
                                                                    companyDao.save(company);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getEmail().equals(company.getEmail())) {
                                                        if (c5 != null) {
                                                            return responMessage.DUPLICATE("EMAIL");
                                                        } else {
                                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                                if (c6 != null) {
                                                                    return responMessage.DUPLICATE("SIUP");
                                                                } else {
                                                                    companyDao.save(company);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                                if (c4 != null) {
                                                    return responMessage.DUPLICATE("FAKSMILE");
                                                } else {
                                                    if (!cId.getEmail().equals(company.getEmail())) {
                                                        if (c5 != null) {
                                                            return responMessage.DUPLICATE("EMAIL");
                                                        } else {
                                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                                if (c6 != null) {
                                                                    return responMessage.DUPLICATE("SIUP");
                                                                } else {
                                                                    companyDao.save(company);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!cId.getEmail().equals(company.getEmail())) {
                                                    if (c5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (!cId.getPhone().equals(company.getPhone())) {
                                        if (c3 != null) {
                                            return responMessage.DUPLICATE("TELEPON");
                                        } else {
                                            if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                                if (c4 != null) {
                                                    return responMessage.DUPLICATE("FAKSMILE");
                                                } else {
                                                    if (!cId.getEmail().equals(company.getEmail())) {
                                                        if (c5 != null) {
                                                            return responMessage.DUPLICATE("EMAIL");
                                                        } else {
                                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                                if (c6 != null) {
                                                                    return responMessage.DUPLICATE("SIUP");
                                                                } else {
                                                                    companyDao.save(company);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!cId.getEmail().equals(company.getEmail())) {
                                                    if (c5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                            if (c4 != null) {
                                                return responMessage.DUPLICATE("FAKSMILE");
                                            } else {
                                                if (!cId.getEmail().equals(company.getEmail())) {
                                                    if (c5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (c5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                } /*end*/
                            }
                        } else {
                            if (!cId.getAddress().equals(company.getAddress())) {
                                if (c2 != null) {
                                    return responMessage.DUPLICATE("ALAMAT");
                                } else {
                                    if (!cId.getPhone().equals(company.getPhone())) {
                                        if (c3 != null) {
                                            return responMessage.DUPLICATE("TELEPON");
                                        } else {
                                            if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                                if (c4 != null) {
                                                    return responMessage.DUPLICATE("FAKSMILE");
                                                } else {
                                                    if (!cId.getEmail().equals(company.getEmail())) {
                                                        if (c5 != null) {
                                                            return responMessage.DUPLICATE("EMAIL");
                                                        } else {
                                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                                if (c6 != null) {
                                                                    return responMessage.DUPLICATE("SIUP");
                                                                } else {
                                                                    companyDao.save(company);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!cId.getEmail().equals(company.getEmail())) {
                                                    if (c5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                            if (c4 != null) {
                                                return responMessage.DUPLICATE("FAKSMILE");
                                            } else {
                                                if (!cId.getEmail().equals(company.getEmail())) {
                                                    if (c5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (c5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!cId.getPhone().equals(company.getPhone())) {
                                    if (c3 != null) {
                                        return responMessage.DUPLICATE("TELEPON");
                                    } else {
                                        if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                            if (c4 != null) {
                                                return responMessage.DUPLICATE("FAKSMILE");
                                            } else {
                                                if (!cId.getEmail().equals(company.getEmail())) {
                                                    if (c5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (c5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                        if (c4 != null) {
                                            return responMessage.DUPLICATE("FAKSMILE");
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (c5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    } else {
                                        if (!cId.getEmail().equals(company.getEmail())) {
                                            if (c5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                if (c6 != null) {
                                                    return responMessage.DUPLICATE("SIUP");
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                companyDao.save(company);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (!cId.getName().equals(company.getName())) {
                        if (c1 != null) {
                            return responMessage.DUPLICATE("NAMA");
                        } else {
                            if (!cId.getAddress().equals(company.getAddress())) {
                                if (c2 != null) {
                                    return responMessage.DUPLICATE("ALAMAT");
                                } else {
                                    if (!cId.getPhone().equals(company.getPhone())) {
                                        if (c3 != null) {
                                            return responMessage.DUPLICATE("TELEPON");
                                        } else {
                                            if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                                if (c4 != null) {
                                                    return responMessage.DUPLICATE("FAKSMILE");
                                                } else {
                                                    if (!cId.getEmail().equals(company.getEmail())) {
                                                        if (c5 != null) {
                                                            return responMessage.DUPLICATE("EMAIL");
                                                        } else {
                                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                                if (c6 != null) {
                                                                    return responMessage.DUPLICATE("SIUP");
                                                                } else {
                                                                    companyDao.save(company);
                                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                                }
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        }
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!cId.getEmail().equals(company.getEmail())) {
                                                    if (c5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                            if (c4 != null) {
                                                return responMessage.DUPLICATE("FAKSMILE");
                                            } else {
                                                if (!cId.getEmail().equals(company.getEmail())) {
                                                    if (c5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (c5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!cId.getPhone().equals(company.getPhone())) {
                                    if (c3 != null) {
                                        return responMessage.DUPLICATE("TELEPON");
                                    } else {
                                        if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                            if (c4 != null) {
                                                return responMessage.DUPLICATE("FAKSMILE");
                                            } else {
                                                if (!cId.getEmail().equals(company.getEmail())) {
                                                    if (c5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (c5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                        if (c4 != null) {
                                            return responMessage.DUPLICATE("FAKSMILE");
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (c5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    } else {
                                        if (!cId.getEmail().equals(company.getEmail())) {
                                            if (c5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                if (c6 != null) {
                                                    return responMessage.DUPLICATE("SIUP");
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                companyDao.save(company);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                }
                            } /*end*/
                        }
                    } else {
                        if (!cId.getAddress().equals(company.getAddress())) {
                            if (c2 != null) {
                                return responMessage.DUPLICATE("ALAMAT");
                            } else {
                                if (!cId.getPhone().equals(company.getPhone())) {
                                    if (c3 != null) {
                                        return responMessage.DUPLICATE("TELEPON");
                                    } else {
                                        if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                            if (c4 != null) {
                                                return responMessage.DUPLICATE("FAKSMILE");
                                            } else {
                                                if (!cId.getEmail().equals(company.getEmail())) {
                                                    if (c5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        if (!cId.getSiup().equals(company.getSiup())) {
                                                            if (c6 != null) {
                                                                return responMessage.DUPLICATE("SIUP");
                                                            } else {
                                                                companyDao.save(company);
                                                                return responMessage.SUCCESS_PROCESS_DATA();
                                                            }
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    }
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (c5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                        if (c4 != null) {
                                            return responMessage.DUPLICATE("FAKSMILE");
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (c5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    } else {
                                        if (!cId.getEmail().equals(company.getEmail())) {
                                            if (c5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                if (c6 != null) {
                                                    return responMessage.DUPLICATE("SIUP");
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                companyDao.save(company);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!cId.getPhone().equals(company.getPhone())) {
                                if (c3 != null) {
                                    return responMessage.DUPLICATE("TELEPON");
                                } else {
                                    if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                        if (c4 != null) {
                                            return responMessage.DUPLICATE("FAKSMILE");
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (c5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    if (!cId.getSiup().equals(company.getSiup())) {
                                                        if (c6 != null) {
                                                            return responMessage.DUPLICATE("SIUP");
                                                        } else {
                                                            companyDao.save(company);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    } else {
                                        if (!cId.getEmail().equals(company.getEmail())) {
                                            if (c5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                if (c6 != null) {
                                                    return responMessage.DUPLICATE("SIUP");
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                companyDao.save(company);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!cId.getFaxmail().equals(company.getFaxmail())) {
                                    if (c4 != null) {
                                        return responMessage.DUPLICATE("FAKSMILE");
                                    } else {
                                        if (!cId.getEmail().equals(company.getEmail())) {
                                            if (c5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                if (!cId.getSiup().equals(company.getSiup())) {
                                                    if (c6 != null) {
                                                        return responMessage.DUPLICATE("SIUP");
                                                    } else {
                                                        companyDao.save(company);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                if (c6 != null) {
                                                    return responMessage.DUPLICATE("SIUP");
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                companyDao.save(company);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                } else {
                                    if (!cId.getEmail().equals(company.getEmail())) {
                                        if (c5 != null) {
                                            return responMessage.DUPLICATE("EMAIL");
                                        } else {
                                            if (!cId.getSiup().equals(company.getSiup())) {
                                                if (c6 != null) {
                                                    return responMessage.DUPLICATE("SIUP");
                                                } else {
                                                    companyDao.save(company);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                companyDao.save(company);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    } else {
                                        if (!cId.getSiup().equals(company.getSiup())) {
                                            if (c6 != null) {
                                                return responMessage.DUPLICATE("SIUP");
                                            } else {
                                                companyDao.save(company);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        } else {
                                            companyDao.save(company);
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
            Company company = companyDao.findId(id);
            if (company != null) {
                companyDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        Company company = companyDao.findId(id);
        if (company != null) {
            return responMessage.SUCCESS_GET(companyDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
