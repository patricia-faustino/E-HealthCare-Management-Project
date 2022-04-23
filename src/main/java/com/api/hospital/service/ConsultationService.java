package com.api.hospital.service;

import com.api.hospital.model.entities.Consultation;
import com.api.hospital.model.entities.Doctor;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.SaveConsultationRequest;
import com.api.hospital.model.response.GetConsultationResponse;
import com.api.hospital.repository.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<GetConsultationResponse> getByCrm(String crm) {
        List<Consultation> consultations = repository.findByDoctorCrm(crm);
        return buildGetConsultationResponse(consultations);
    }

    public List<GetConsultationResponse> getByCpf(String cpf) {
        List<Consultation> consultations = repository.findByPatientCpf(cpf);
        return buildGetConsultationResponse(consultations);
    }

    public List<GetConsultationResponse> getByCnpj(String cnpj) {
        List<Consultation> consultations = repository.findByHospitalCnpj(cnpj);
        return buildGetConsultationResponse(consultations);
    }

    private List<GetConsultationResponse> buildGetConsultationResponse(List<Consultation> consultations) {
        List<GetConsultationResponse> responses = new ArrayList<>();

        if(consultations != null) {
            consultations.forEach(consultation -> { responses.add(
                    GetConsultationResponse.builder()
                            .crm(consultation.getDoctor().getCrm())
                            .doctorName(consultation.getDoctor().getName())
                            .hospitalCnpj(consultation.getHospital().getCnpj())
                            .hospitalName(consultation.getHospital().getName())
                            .patientCpf(consultation.getPatient().getCpf())
                            .patientName(consultation.getPatient().getName())
                            .symptoms(consultation.getSymptoms())
                            .build());
            });
        }
        return responses;
    }
}