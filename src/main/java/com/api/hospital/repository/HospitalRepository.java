package com.api.hospital.repository;

import com.api.hospital.model.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
