package com.psdkp.kkp.apipsdkp.repository.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.FishingGearGt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingGearGtDao extends PagingAndSortingRepository<FishingGearGt, Integer>{

    @Query(value = "SELECT * FROM fishing_gear_gt fg WHERE fg.value_up LIKE %?1% OR fg.value_down LIKE %?1%", nativeQuery = true)
    Page<FishingGearGt> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM fishing_gear_gt fg WHERE fg.id=?1", nativeQuery = true)
    FishingGearGt findId(Integer id);

    FishingGearGt findByValueUp(Integer up);
    FishingGearGt findByValueDown(Integer down);
}
