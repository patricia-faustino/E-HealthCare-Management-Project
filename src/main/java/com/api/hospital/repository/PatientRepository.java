package com.api.hospital.repository;

import com.api.hospital.model.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    Optional<Patient> findByCpf(String cpf);
}