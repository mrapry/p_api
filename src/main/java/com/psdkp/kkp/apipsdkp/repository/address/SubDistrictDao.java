package com.psdkp.kkp.apipsdkp.repository.address;

import org.springframework.data.domain.Pageable;

import com.psdkp.kkp.apipsdkp.domain.address.SubDistrict;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubDistrictDao extends PagingAndSortingRepository<SubDistrict, Integer> {

    Page<SubDistrict> findByCode(String code, Pageable pageable);
    Page<SubDistrict> findByName(String name, Pageable pageable);
}
