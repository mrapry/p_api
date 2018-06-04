package com.psdkp.kkp.apipsdkp.repository.harbor;

import com.psdkp.kkp.apipsdkp.domain.harbor.Harbor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarborDao extends PagingAndSortingRepository<Harbor, Integer> {

    @Query(value = "SELECT * FROM harbor h WHERE h.name LIKE %?1% OR h.code LIKE %?1% OR h.address LIKE %?1% OR h.city_id LIKE %?1% OR h.harbor_type_id LIKE %?1%",
            nativeQuery = true)
    Page<Harbor> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM harbor h WHERE h.id=?1", nativeQuery = true)
    Harbor findId(Integer id);

    Harbor findByCode(String code);

    Harbor findByName(String name);

    @Query(value = "SELECT * FROM harbor h WHERE h.harbor_type_id=?1", nativeQuery = true)
    Harbor findByType(Integer typeId);
}
