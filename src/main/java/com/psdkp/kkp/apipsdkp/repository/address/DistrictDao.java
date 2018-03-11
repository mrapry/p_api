package com.psdkp.kkp.apipsdkp.repository.address;

import org.springframework.data.domain.Pageable;

import com.psdkp.kkp.apipsdkp.domain.address.District;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictDao extends PagingAndSortingRepository<District, Integer> {

    Page<District> findByCode(String code, Pageable pageable);
    Page<District> findByName(String name, Pageable pageable);
}
