package com.psdkp.kkp.apipsdkp.repository.harbor;

import com.psdkp.kkp.apipsdkp.domain.harbor.HarborType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarborTypeDao extends PagingAndSortingRepository<HarborType, Integer> {

    @Query(value = "SELECT * FROM harbor_type h WHERE h.name LIKE %?1%", nativeQuery = true)
    Page<HarborType> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM harbor_type h WHERE h.id=?1", nativeQuery = true)
    HarborType findId(Integer id);

    HarborType findByName(String name);
}
