package com.psdkp.kkp.apipsdkp.domain.vessel;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class VesselTransmitter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String transmitterCode;
    private String skatCode;
    private String skatExp;
}
