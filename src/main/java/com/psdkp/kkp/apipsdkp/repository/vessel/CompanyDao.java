package com.psdkp.kkp.apipsdkp.repository.vessel;

import com.psdkp.kkp.apipsdkp.domain.vessel.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends PagingAndSortingRepository<Company, Integer> {

    @Query(value = "SELECT * FROM company c WHERE c.code LIKE %?1% OR c.name LIKE %?1% OR c.address LIKE %?1% OR c.post_code LIKE %?1% OR c.phone LIKE %?1% OR c.faxmail LIKE %?1% OR c.email LIKE %?1% OR c.siup LIKE %?1% OR c.siup_date LIKE %?1% OR c.pic LIKE %?1% OR c.active LIKE %?1%", nativeQuery = true)
    Page<Company> findAllByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM company c WHERE c.id=?1", nativeQuery = true)
    Company findId(Integer id);

    Company findByCode(String code);

    Company findByName(String name);

    Company findByAddress(String address);

    Company findByPostCode(String code);

    Company findByPhone(String phone);

    Company findByFaxmail(String faxmail);

    Company findByEmail(String email);

    Company findBySiup(String siup);

    Company findBySiupDate(String siupDate);

    Company findByPic(String pic);

    Company findByActive(String active);
}
