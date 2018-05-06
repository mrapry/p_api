package com.psdkp.kkp.apipsdkp.repository.harbor;

import com.psdkp.kkp.apipsdkp.domain.harbor.Wpp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WppDao extends PagingAndSortingRepository<Wpp, Integer> {

    @Query(value = "SELECT * FROM wpp w WHERE w.code LIKE %?1%", nativeQuery = true)
    Page<Wpp> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM wpp w WHERE w.id=?1", nativeQuery = true)
    Wpp findId(Integer id);

    Wpp findByCode(String code);
}
