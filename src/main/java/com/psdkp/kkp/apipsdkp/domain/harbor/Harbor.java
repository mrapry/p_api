package com.psdkp.kkp.apipsdkp.domain.harbor;

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
    private Integer cityId;

    @ManyToOne
    @JoinColumn(name = "harborTypeId", referencedColumnName = "id", updatable = true)
    private HarborType harborType;
}
