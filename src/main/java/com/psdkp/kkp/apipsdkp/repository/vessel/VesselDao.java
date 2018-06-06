package com.psdkp.kkp.apipsdkp.repository.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VesselDao extends PagingAndSortingRepository<Vessel, Integer> {

    @Query(value = "SELECT * FROM vessel", nativeQuery = true)
    Page<Vessel> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM vessel v WHERE v.id=?1", nativeQuery = true)
    Vessel findId(Integer id);

    Vessel findByCode(String code);

    Vessel findByName(String name);

    Vessel findBySelarSignCode(String selarSignCode);

    Vessel findByEngineCode(String engineCode);

    @Query(value = "SELECT * FROM vessel v WHERE v.transmitter_id=?1", nativeQuery = true)
    Vessel findByTransmitter(Integer transmitterId);

    @Query(value = "SELECT * FROM vessel v WHERE v.company_id=?1", nativeQuery = true)
    Vessel findByCompany(Integer companyId);

    @Query(value = "SELECT * FROM vessel v WHERE v.type_id=?1", nativeQuery = true)
    Vessel findByType(Integer typeId);

    @Query(value = "SELECT * FROM vessel v WHERE v.material_id=?1", nativeQuery = true)
    Vessel findByMaterial(Integer materialId);

}