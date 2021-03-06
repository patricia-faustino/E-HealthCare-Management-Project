package com.api.hospital.model.entities;

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

    @Column(name = "Cnpj")
    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "IdAddress")
    private Address address;

    @Column(name = "TotalBeds")
    private Long totalBeds;

    @Column(name = "AvailableBeds")
    private Long availableBeds;
}
