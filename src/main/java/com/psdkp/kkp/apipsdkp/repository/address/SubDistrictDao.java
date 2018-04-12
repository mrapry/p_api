package com.psdkp.kkp.apipsdkp.repository.address;

import org.springframework.data.domain.Pageable;

import com.psdkp.kkp.apipsdkp.domain.address.SubDistrict;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubDistrictDao extends PagingAndSortingRepository<SubDistrict, Integer> {

    @Query(value = "select * from sub_district p where p.name like %?1% or p.code like %?1%", nativeQuery = true)
    Page<SubDistrict> findAllByName(String name, Pageable pageable);

    SubDistrict findByCode(String code);
    SubDistrict findByName(String name);

    @Query(value = "select * from sub_district p where p.id=?1", nativeQuery = true)
    SubDistrict findId(Integer id);
}
