package com.psdkp.kkp.apipsdkp.repository.harbor;

import com.psdkp.kkp.apipsdkp.domain.harbor.WppLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WppLocationDao extends PagingAndSortingRepository<WppLocation, Integer>{

    @Query(value = "SELECT * FROM wpp_location wl WHERE wl.location LIKE %?1%", nativeQuery = true)
    Page<WppLocation> findAllByLocation(String location, Pageable pageable);

    @Query(value = "SELECT * FROM wpp_location w WHERE w.id=?1", nativeQuery = true)
    WppLocation findId(Integer id);

    WppLocation findByLocation(String location);
}
