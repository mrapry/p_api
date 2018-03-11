package com.psdkp.kkp.apipsdkp.domain.address;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private City city;

    private String code;
    private String name;
}
