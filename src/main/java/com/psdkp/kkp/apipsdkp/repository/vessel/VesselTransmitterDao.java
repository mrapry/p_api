package com.psdkp.kkp.apipsdkp.repository.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.VesselTransmitter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmitterDao extends PagingAndSortingRepository<VesselTransmitter, Integer> {

    @Query(value = "SELECT * FROM vessel_transmitter t WHERE t.transmitter_code LIKE %?1% OR t.skat_code LIKE %?1% OR t.skat_exp LIKE %?1%", nativeQuery = true)
    Page<VesselTransmitter> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM vessel_transmitter t WHERE t.id=?1", nativeQuery = true)
    VesselTransmitter findId(Integer id);

    VesselTransmitter findBySkatCode(String skatCode);

    VesselTransmitter findByTransmitterCode(String transmitterCode);
}
