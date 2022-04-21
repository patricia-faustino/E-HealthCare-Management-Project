package com.api.hospital.service;

import com.api.hospital.model.entities.Consultation;
import com.api.hospital.model.entities.Doctor;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.SaveConsultationRequest;
import com.api.hospital.repository.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository repository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;

    public void save(SaveConsultationRequest request) {
        Patient patient = patientService.findByCpf(request.getCpf());
        Doctor doctor = doctorService.findDoctorByCrm(request.getCrm());
        Hospital hospital = hospitalService.findByCnpj(request.getCnpj());
        Consultation consultation = new Consultation();
        consultation.setDoctor(doctor);
        consultation.setHospital(hospital);
        consultation.setPatient(patient);
        consultation.setSymptoms(request.getSymptoms());
        repository.save(consultation);
    }
}