package com.psdkp.kkp.apipsdkp.repository.address;

import com.psdkp.kkp.apipsdkp.domain.address.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceDao extends JpaRepository<Province, Integer> {

    @Query(value = "select * from province p where p.name like %?1% or p.code like %?1%", nativeQuery = true)
    Page<Province> findAllByName(String name, Pageable pageable);

    Province findByCode(String code);
    Province findByName(String name);

    @Query(value = "select * from province p where p.id=?1", nativeQuery = true)
    Province findId(Integer id);
}
