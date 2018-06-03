package com.psdkp.kkp.apipsdkp.domain.vessel;

import com.psdkp.kkp.apipsdkp.domain.address.SubDistrict;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "subDistrictId", referencedColumnName = "id", updatable = true)
    private SubDistrict subDistrict;

    private String zipCode;
    private String picName;
    private String picIdentity;
    private String[] companyPhone;
    private String facsimile;
    private String email;
    private String siupCode;
    private String siupDateStart;
    private String siupDateExp;
    private String status;


}
