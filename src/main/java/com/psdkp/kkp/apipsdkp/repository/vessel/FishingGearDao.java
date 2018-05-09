package com.psdkp.kkp.apipsdkp.repository.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.FishingGear;
import com.psdkp.kkp.apipsdkp.domain.vessel.VesselType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingGearDao extends PagingAndSortingRepository<FishingGear, Integer>{

    @Query(value = "SELECT * FROM fishing_gear fg WHERE fg.code LIKE %?1% OR fg.name LIKE %?1% OR fg.nick LIKE %?1% OR fg.feature LIKE %?1% OR fg.english_name LIKE %?1% OR fg.status LIKE %?1%", nativeQuery = true)
    Page<FishingGear> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM fishing_gear fg WHERE fg.id=?1", nativeQuery = true)
    FishingGear findId(Integer id);

    FishingGear findByCode(String code);

    FishingGear findByName(String name);

    FishingGear findByNick(String nick);

    FishingGear findByEnglishName(String englishName);
}
