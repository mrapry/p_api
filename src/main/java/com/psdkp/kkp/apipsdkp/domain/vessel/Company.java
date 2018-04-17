package com.psdkp.kkp.apipsdkp.domain.vessel;

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
    private String postCode;
    private String phone;
    private String faxmail;
    private String email;
    private String siup;
    private String siupDate;
    private String pic;
    private String active;
}
