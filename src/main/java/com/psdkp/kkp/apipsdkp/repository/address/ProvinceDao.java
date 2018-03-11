package com.psdkp.kkp.apipsdkp.repository.address;

import com.psdkp.kkp.apipsdkp.domain.address.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceDao extends PagingAndSortingRepository<Province, Integer> {

    @Query(value = "select * from province p where p.name like %?1% or p.code like %?1%", nativeQuery = true)
    Page<Province> findByNameOrCode(String name, Pageable pageable);
    Page<Province> findByCode(String code, Pageable pageable);
    Page<Province> findById(Integer id, Pageable pageable);
}
