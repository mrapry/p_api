package com.psdkp.kkp.apipsdkp.repository.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.VesselMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VesselMaterialDao extends PagingAndSortingRepository<VesselMaterial, Integer> {

    @Query(value = "SELECT * FROM vessel_material vm WHERE vm.name LIKE %?1%", nativeQuery = true)
    Page<VesselMaterial> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM vessel_material vm WHERE vm.id=?1", nativeQuery = true)
    VesselMaterial findId(Integer id);

    VesselMaterial findByName(String name);
}
