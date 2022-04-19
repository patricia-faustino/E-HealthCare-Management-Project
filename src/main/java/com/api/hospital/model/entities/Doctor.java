package com.api.hospital.model.entities;

import com.api.hospital.model.enums.SPECIALTY;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor extends BaseAudit {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Crm")
    private String crm;

    @Column(name = "Specialty")
    @Enumerated(EnumType.STRING)
    private SPECIALTY specialty;

    @ManyToOne
    @JoinColumn(name = "IdAddress")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "IdHospital")
    private Hospital hospital;
}
