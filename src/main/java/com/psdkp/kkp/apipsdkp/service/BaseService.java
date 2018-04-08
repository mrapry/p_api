package com.psdkp.kkp.apipsdkp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T1,T2,T3> {

    Page<T2> findAll(String name, Pageable pageable);
    T1 save(T2 t2);
    T1 edit(T2 t2);
    T1 del(T3 id);
}