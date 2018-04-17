package com.psdkp.kkp.apipsdkp.domain.unitWorking;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MappingUnitWorking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "parrentId", referencedColumnName = "id", updatable = false)
    private UnitWorking parrent;

    @ManyToOne
    @JoinColumn (name = "childId", referencedColumnName = "id", updatable = false)
    private UnitWorking child;
}
