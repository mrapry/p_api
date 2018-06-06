package com.psdkp.kkp.apipsdkp.domain.vessel;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(indexes = {@Index(name = "i_code", columnList = "code")})
public class Vessel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    private String name;
    private String nameOld;
    private String callSign;
    private String selarSignLocation;
    private String selarSignCode;
    private Integer gt;
    private Integer lenght;
    private Integer wide;
    private Integer high;
    private String bootHorsePower;
    private String grosAkteCode;
    private String engineName;
    private String engineCode;
    private String country;
    private String countryOld;
    private String shipyard;
    private String builtLocation;
    private String captain;

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "id", updatable = true)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "id", updatable = true)
    private VesselType vesselType;

    @ManyToOne
    @JoinColumn(name = "transmitterId", referencedColumnName = "id", updatable = true)
    private VesselTransmitter vesselTransmitter;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "vessel_fishing_gear",
            joinColumns = @JoinColumn(name = "vessel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fishingGear_id", referencedColumnName = "id"))
    private Set<FishingGear> fishingGear;

    @ManyToOne
    @JoinColumn(name = "materialId", referencedColumnName = "id", updatable = true)
    private VesselMaterial vesselMaterial;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "vessel_abpi",
            joinColumns = @JoinColumn(name = "vessel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "abpi_id", referencedColumnName = "id"))
    private Set<Abpi> abpi;
}
