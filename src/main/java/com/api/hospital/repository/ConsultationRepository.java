package com.api.hospital.repository;

import com.api.hospital.model.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByDoctorCrm(String crm);
}