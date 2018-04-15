package com.psdkp.kkp.apipsdkp.domain.address;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "provinceId", referencedColumnName = "id", updatable = false)
    private Province province;

    private String code;
    private String name;
}
