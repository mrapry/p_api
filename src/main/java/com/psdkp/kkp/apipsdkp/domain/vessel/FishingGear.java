package com.psdkp.kkp.apipsdkp.domain.vessel;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class FishingGear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    private String nick;
    private String name;
    private String englishName;
    private String status;
    private String feature;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "fishing_gear_fg_gt",
            joinColumns = @JoinColumn(name = "fishing_gear_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fishingGearGt_id", referencedColumnName = "id"))
    private Set<FishingGearGt> fishingGearGts;

    private Integer parentId;
}
