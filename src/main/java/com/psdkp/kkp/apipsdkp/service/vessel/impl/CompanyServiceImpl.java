package com.psdkp.kkp.apipsdkp.service.vessel.impl;

import com.psdkp.kkp.apipsdkp.domain.address.SubDistrict;
import com.psdkp.kkp.apipsdkp.domain.vessel.Company;
import com.psdkp.kkp.apipsdkp.repository.address.SubDistrictDao;
import com.psdkp.kkp.apipsdkp.repository.vessel.CompanyDao;
import com.psdkp.kkp.apipsdkp.service.vessel.CompanyService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private SubDistrictDao subDistrictDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(companyDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(Company company) {

        if (company.getCode().equals("") ||
                company.getName().equals("") ||
                company.getAddress().equals("") ||
                company.getZipCode().equals("") ||
                company.getPicName().equals("") ||
                company.getPicIdentity().equals("") ||
                company.getCompanyPhone().length == 0 ||
                company.getFacsimile().equals("") ||
                company.getFacsimile().equals("") ||
                company.getEmail().equals("") ||
                company.getSiupCode().equals("") ||
                company.getSiupDateStart().equals("") ||
                company.getSiupDateExp().equals("")
                ) {
            return responMessage.BAD_REUQEST();
        } else {
            Company cCode = companyDao.findByCode(company.getCode());
            Company cName = companyDao.findByName(company.getName());
            Company cSiupCode = companyDao.findBySiupCode(company.getSiupCode());
            Company cEmail = companyDao.findByEmail(company.getEmail());
            SubDistrict subDistrict = subDistrictDao.findId(company.getSubDistrict().getId());
            if (cCode != null) {
                return responMessage.DUPLICATE("KODE");
            } else if (cName != null) {
                return responMessage.DUPLICATE("NAMA");
            } else if (cSiupCode != null) {
                return responMessage.DUPLICATE("NOMOR SIUP");
            } else if (cEmail != null) {
                return responMessage.DUPLICATE("ALAMAT EMAIL");
            } else if (subDistrict == null) {
                return responMessage.NOT_FOUND("SUB DISTRICT");
            } else {
                companyDao.save(company);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(Company company) {
        if (company.getId() == null ||
                company.getCode().equals("") ||
                company.getName().equals("") ||
                company.getAddress().equals("") ||
                company.getZipCode().equals("") ||
                company.getPicName().equals("") ||
                company.getPicIdentity().equals("") ||
                company.getCompanyPhone().length == 0 ||
                company.getFacsimile().equals("") ||
                company.getFacsimile().equals("") ||
                company.getEmail().equals("") ||
                company.getSiupCode().equals("") ||
                company.getSiupDateStart().equals("") ||
                company.getSiupDateExp().equals("")
                ) {
            return responMessage.BAD_REUQEST();
        } else {
            Company cCode = companyDao.findByCode(company.getCode());
            Company cName = companyDao.findByName(company.getName());
            Company cSiupCode = companyDao.findBySiupCode(company.getSiupCode());
            Company cEmail = companyDao.findByEmail(company.getEmail());
            Company cId = companyDao.findId(company.getId());
            SubDistrict subDistrict = subDistrictDao.findId(company.getSubDistrict().getId());

            if (cId != null) {
                if (subDistrict == null) {
                    return responMessage.NOT_FOUND("KELURAHAN");
                } else {
                    if (!cId.getCode().equals(company.getCode())) {
                        if (cCode != null) {
                            return responMessage.DUPLICATE("KODE");
                        } else {
                            if (!cId.getName().equals(company.getName())) {
                                if (cName != null) {
                                    return responMessage.DUPLICATE("NAMA");
                                } else {
                                    if (!cId.getSiupCode().equals(company.getSiupCode())) {
                                        if (cSiupCode != null) {
                                            return responMessage.DUPLICATE("NOMOR SIUP");
                                        } else {
                                            if (!cId.getEmail().equals(company.getEmail())) {
                                                if (cEmail != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
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
                                        if (!cId.getEmail().equals(company.getEmail())) {
                                            if (cEmail != null) {
                                                return responMessage.DUPLICATE("EMAIL");
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
                                if (!cId.getSiupCode().equals(company.getSiupCode())) {
                                    if (cSiupCode != null) {
                                        return responMessage.DUPLICATE("NOMOR SIUP");
                                    } else {
                                        if (!cId.getEmail().equals(company.getEmail())) {
                                            if (cEmail != null) {
                                                return responMessage.DUPLICATE("EMAIL");
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
                                    if (!cId.getEmail().equals(company.getEmail())) {
                                        if (cEmail != null) {
                                            return responMessage.DUPLICATE("EMAIL");
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
                        if (!cId.getName().equals(company.getName())) {
                            if (cName != null) {
                                return responMessage.DUPLICATE("NAMA");
                            } else {
                                if (!cId.getSiupCode().equals(company.getSiupCode())) {
                                    if (cSiupCode != null) {
                                        return responMessage.DUPLICATE("NOMOR SIUP");
                                    } else {
                                        if (!cId.getEmail().equals(company.getEmail())) {
                                            if (cEmail != null) {
                                                return responMessage.DUPLICATE("EMAIL");
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
                                    if (!cId.getEmail().equals(company.getEmail())) {
                                        if (cEmail != null) {
                                            return responMessage.DUPLICATE("EMAIL");
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
                            if (!cId.getSiupCode().equals(company.getSiupCode())) {
                                if (cSiupCode != null) {
                                    return responMessage.DUPLICATE("NOMOR SIUP");
                                } else {
                                    if (!cId.getEmail().equals(company.getEmail())) {
                                        if (cEmail != null) {
                                            return responMessage.DUPLICATE("EMAIL");
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
                                if (!cId.getEmail().equals(company.getEmail())) {
                                    if (cEmail != null) {
                                        return responMessage.DUPLICATE("EMAIL");
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

    @Override
    public Object findSubDistrict(Integer subDistrictId, Pageable pageable) {
        SubDistrict subDistrict = subDistrictDao.findId(subDistrictId);
        if (subDistrict != null) {
            return responMessage.SUCCESS_GET(subDistrictDao.findId(subDistrictId));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}