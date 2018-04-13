package com.psdkp.kkp.apipsdkp.repository.account;

import com.psdkp.kkp.apipsdkp.domain.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer>{

//    @Transactional
    @Query(value = "select * from account p where p.fname like %?1% or p.lname like %?1%", nativeQuery = true)
    Page<Account> findAllByName(String name, Pageable pageable);

//    @Transactional
    @Query(value = "select * from account p where p.status like %?1%", nativeQuery = true)
    Page<Account> findByStatus(String name, Pageable pageable);

    Account findByEmail(String email);
    Account findByPhoneNumber(String phoneNumber);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update account a set a.status = ?1 where a.id = ?2" , nativeQuery = true)
    void updateStatus(String name, Integer id);

//    @Transactional
    @Query(value = "select * from account p where p.id=?1", nativeQuery = true)
    Account findId(Integer id);
}
