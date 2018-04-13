package com.psdkp.kkp.apipsdkp.service.account.impl;

import com.psdkp.kkp.apipsdkp.domain.account.Account;
import com.psdkp.kkp.apipsdkp.domain.account.RoleGroup;
import com.psdkp.kkp.apipsdkp.repository.account.AccountDao;
import com.psdkp.kkp.apipsdkp.repository.account.RoleGroupDao;
import com.psdkp.kkp.apipsdkp.service.account.AccountService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private RoleGroupDao roleGroupDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(accountDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(Account account) {
        if (account.getEmail().equals("") || account.getPassword().equals("") || account.getPhoneNumber().equals("") || account.getFname().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Account pMail = accountDao.findByEmail(account.getEmail());
            if (pMail != null) {
                return responMessage.DUPLICATE("EMAIL");
            } else {
                Account pPhone = accountDao.findByPhoneNumber(account.getPhoneNumber());
                if (pPhone != null) {
                    return responMessage.DUPLICATE("PHONE NUMBER");
                } else {
                    RoleGroup pRole = roleGroupDao.findId(account.getRoleGroup().getId());
                    if (pRole != null) {
                        accountDao.save(account);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    } else {
                        return responMessage.NOT_FOUND("ROLE GROUP ID");
                    }
                }
            }
        }
    }

    @Override
    public Object edit(Account account) {
        if (account.getId() == null || account.getEmail().equals("") || account.getPassword().equals("") || account.getPhoneNumber().equals("") || account.getFname().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Account pAccount = accountDao.findId(account.getId());
            if (pAccount != null) {
                if (!account.getEmail().equals(pAccount.getEmail())) {
                    Account pMail = accountDao.findByEmail(account.getEmail());
                    if (pMail != null) {
                        return responMessage.DUPLICATE("EMAIL");
                    } else {
                        Account pPhone = accountDao.findByPhoneNumber(account.getPhoneNumber());
                        if (pPhone != null) {
                            return responMessage.DUPLICATE("PHONE NUMBER");
                        } else {
                            RoleGroup pRole = roleGroupDao.findId(account.getRoleGroup().getId());
                            if (pRole != null) {
                                accountDao.save(account);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            } else {
                                return responMessage.NOT_FOUND("ROLE GROUP ID");
                            }
                        }
                    }
                } else {
                    Account pPhone = accountDao.findByPhoneNumber(account.getPhoneNumber());
                    if (pPhone != null) {
                        return responMessage.DUPLICATE("PHONE NUMBER");
                    } else {
                        RoleGroup pRole = roleGroupDao.findId(account.getRoleGroup().getId());
                        if (pRole != null) {
                            accountDao.save(account);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        } else {
                            return responMessage.NOT_FOUND("ROLE GROUP ID");
                        }
                    }
                }
            } else {
                return responMessage.NOT_FOUND("ID ACCOUNT");
            }
        }
    }

    @Override
    public Object del(Integer id) {
        if (id == null) {
            return responMessage.BAD_REUQEST();
        } else {
            Account pCode = accountDao.findId(id);
            if (pCode != null) {
                accountDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        Account pCode = accountDao.findId(id);
        if (pCode != null) {
            return responMessage.SUCCESS_GET(accountDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }

    @Override
    public Object findByStatus(String status, Pageable pageable) {
        return responMessage.SUCCESS_GET(accountDao.findByStatus(status, pageable));
    }

    @Override
    public Object updateStatus(String status, Integer id) {
        if (status.equals("") || id == null){
            return responMessage.BAD_REUQEST();
        } else {
            Account pAccount = accountDao.findId(id);
            if (pAccount!=null){
                accountDao.updateStatus(status,id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }
}
