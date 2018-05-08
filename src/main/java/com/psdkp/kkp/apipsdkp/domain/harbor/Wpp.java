package com.psdkp.kkp.apipsdkp.domain.harbor;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Wpp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "wpp_location_ref",
            joinColumns = @JoinColumn(name = "wpp_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id"))
    private Set<WppLocation> locations;
}
