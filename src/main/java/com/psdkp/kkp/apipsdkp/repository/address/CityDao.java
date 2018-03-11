package com.psdkp.kkp.apipsdkp.repository.address;

import org.springframework.data.domain.Pageable;

import com.psdkp.kkp.apipsdkp.domain.address.City;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends PagingAndSortingRepository<City, Integer>{

    Page<City> findByCode(String code, Pageable pageable);
    Page<City> findByName(String name, Pageable pageable);
}
