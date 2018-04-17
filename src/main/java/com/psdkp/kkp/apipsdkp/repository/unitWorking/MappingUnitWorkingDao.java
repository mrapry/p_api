package com.psdkp.kkp.apipsdkp.repository.unitWorking;

import com.psdkp.kkp.apipsdkp.domain.unitWorking.MappingUnitWorking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingUnitWorkingDao extends JpaRepository<MappingUnitWorking, Integer>{

    @Query(value = "select * from mapping_unit_working tu where tu.parrent_id like %?1%", nativeQuery = true)
    Page<MappingUnitWorking> findAllByType(String id, Pageable pageable);

    @Query(value = "select * from mapping_unit_working tu where tu.id=?1", nativeQuery = true)
    MappingUnitWorking findId(Integer id);

}
