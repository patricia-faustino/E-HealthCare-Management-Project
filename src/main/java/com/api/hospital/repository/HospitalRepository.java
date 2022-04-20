package com.api.hospital.repository;

import com.api.hospital.model.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    List<Hospital> findByNameContainingIgnoreCase(@Param(value = "name") String name);

    Optional<Hospital> findByCnpj(String cnpj);
}
