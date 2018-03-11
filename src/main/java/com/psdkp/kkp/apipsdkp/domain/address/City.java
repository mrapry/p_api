package com.psdkp.kkp.apipsdkp.domain.address;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "provinceId", referencedColumnName = "id")
    private Province province;

    private String code;
    private String name;
}
