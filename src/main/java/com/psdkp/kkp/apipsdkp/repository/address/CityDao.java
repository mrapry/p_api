package com.psdkp.kkp.apipsdkp.repository.address;

import com.psdkp.kkp.apipsdkp.domain.address.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDao extends PagingAndSortingRepository<City, Integer> {

    @Query(value = "select * from city p where p.name like %?1% or p.code like %?1%", nativeQuery = true)
    Page<City> findAllByName(String name, Pageable pageable);

    City findByCode(String code);
    City findByName(String name);

    List<City> findByProvinceId(Integer id);

    @Query(value = "select * from city p where p.id=?1", nativeQuery = true)
    City findId(Integer id);
}
