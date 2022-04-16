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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consultation extends BaseAudit {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="IdPatient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name ="IdHospital")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name ="IdDoctor")
    private Doctor doctor;
}
