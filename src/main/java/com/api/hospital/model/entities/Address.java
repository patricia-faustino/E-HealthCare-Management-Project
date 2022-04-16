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
public class Address extends BaseAudit {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Cep")
    private String cep;

    @Column(name = "Street")
    private String street;

    @Column(name = "District")
    private String district;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;
}
