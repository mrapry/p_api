package com.psdkp.kkp.apipsdkp.domain.account;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String email;
    @NotNull
    private String password;

    private String fname;
    private String lname;

    private String dob;

    @NotNull
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", updatable = false)
    private RoleGroup roleGroup;

    private String status;
}
