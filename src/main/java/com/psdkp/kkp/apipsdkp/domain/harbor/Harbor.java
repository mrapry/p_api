package com.psdkp.kkp.apipsdkp.domain.harbor;

import com.psdkp.kkp.apipsdkp.domain.address.City;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Harbor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    private String name;

    private String address;

    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "id", updatable = true)
    private City city;

    @ManyToOne
    @JoinColumn(name = "harborTypeId", referencedColumnName = "id", updatable = true)
    private HarborType harborType;
}
