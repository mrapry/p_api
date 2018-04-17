package com.psdkp.kkp.apipsdkp.repository.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.VesselType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VesselTypeDao extends PagingAndSortingRepository<VesselType, Integer> {

    @Query(value = "SELECT * FROM vessel_type vt WHERE vt.name LIKE %?1%", nativeQuery = true)
    Page<VesselType> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM vessel_type vt WHERE vt.id=?1", nativeQuery = true)
    VesselType findId(Integer id);

    VesselType findByName(String name);
}
