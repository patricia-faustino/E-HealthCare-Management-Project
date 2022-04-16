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
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hospital extends BaseAudit {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Crm")
    private String crm;

    @Column(name = "Specialty")
    private SPECIALTY specialty;

    @ManyToOne
    @JoinColumn(name = "IdAddress")
    private Address address;

    @Column(name = "TotalBeds")
    private Long totalBeds;

    @Column(name = "AvailableBeds")
    private Long availableBeds;
}
