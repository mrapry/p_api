package com.psdkp.kkp.apipsdkp.repository.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends PagingAndSortingRepository<Company, Integer> {

    @Query(value = "SELECT * FROM company c WHERE " +
            "c.code LIKE %?1% OR " +
            "c.name LIKE %?1% OR " +
            "c.address LIKE %?1% OR " +
            "c.sub_district_id LIKE %?1% OR " +
            "c.zip_code LIKE %?1% OR " +
            "c.pic_name LIKE %?1% OR " +
            "c.pic_identity LIKE %?1% OR " +
            "c.company_phone LIKE %?1% OR " +
            "c.facsimile LIKE %?1% OR " +
            "c.email LIKE %?1% OR " +
            "c.siup_code LIKE %?1% OR " +
            "c.siup_date_start LIKE %?1% OR " +
            "c.siup_date_exp LIKE %?1% OR " +
            "c.status LIKE %?1%", nativeQuery = true)
    Page<Company> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM company c WHERE c.id=?1", nativeQuery = true)
    Company findId(Integer id);

    Company findByCode(String code);

    Company findByName(String name);

    Company findBySiupCode(String code);

    Company findByEmail(String email);
}
