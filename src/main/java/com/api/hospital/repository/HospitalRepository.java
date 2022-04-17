package com.api.hospital.repository;

import com.api.hospital.model.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findByName(String name);

    Optional<Hospital> findByCnpj(String cnpj);
}
