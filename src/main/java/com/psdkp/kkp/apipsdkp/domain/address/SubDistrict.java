package com.psdkp.kkp.apipsdkp.domain.address;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SubDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "districtId", referencedColumnName = "id")
    private District district;

    private String code;
    private String name;
}
