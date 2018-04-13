package com.psdkp.kkp.apipsdkp.repository.account;

import com.psdkp.kkp.apipsdkp.domain.account.RoleGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleGroupDao extends JpaRepository<RoleGroup, Integer> {

    @Query(value = "select * from role_group p where p.name like %?1% ", nativeQuery = true)
    Page<RoleGroup> findAllByName(String name, Pageable pageable);

    RoleGroup findByName(String name);

    @Query(value = "select * from role_group p where p.id=?1", nativeQuery = true)
    RoleGroup findId(Integer id);
}
