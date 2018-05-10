package com.psdkp.kkp.apipsdkp.repository.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.Abpi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbpiDao extends PagingAndSortingRepository<Abpi, Integer> {

    @Query(value = "SELECT * FROM abpi a WHERE a.name LIKE %?1%", nativeQuery = true)
    Page<Abpi> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM abpi a WHERE a.id=?1", nativeQuery = true)
    Abpi findId(Integer id);

    Abpi findByName(String name);
}
