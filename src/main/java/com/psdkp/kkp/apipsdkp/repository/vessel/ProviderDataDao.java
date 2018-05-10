package com.psdkp.kkp.apipsdkp.repository.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.ProviderData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderDataDao extends PagingAndSortingRepository<ProviderData, Integer> {

    @Query(value = "SELECT * FROM provider_data p WHERE p.name LIKE %?1%", nativeQuery = true)
    Page<ProviderData> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM provider_data p WHERE p.id=?1", nativeQuery = true)
    ProviderData findId(Integer id);

    ProviderData findByName(String name);
}
