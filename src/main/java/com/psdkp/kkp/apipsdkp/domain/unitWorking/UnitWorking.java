package com.psdkp.kkp.apipsdkp.domain.unitWorking;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UnitWorking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; /*not duplicate*/
    private String code; /*not duplicate*/
    private String name; /*not duplicate*/
    private String address;
    private String phone; /*not duplicate*/
    private String faxmail; /*not duplicate*/
    private String email; /*not duplicate*/
    private String langitude; /*not duplicate*/
    private String longitude; /*not duplicate*/
    private String serviceLocation;

    @ManyToOne
    @JoinColumn(name = "typeUnitId", referencedColumnName = "id", updatable = false)
    private TypeUnit typeUnit;

}
