package com.api.hospital.helper;

import com.api.hospital.model.entities.Consultation;
import com.api.hospital.model.request.SaveConsultationRequest;

public class ConsultationHelper {

    public static SaveConsultationRequest buildSaveConsultationRequest() {
        return SaveConsultationRequest.builder()
                .cpf("123.456.789-00")
                .crm("1234567890")
                .cnpj("85.086.201/0001-06")
                .symptoms("Dor de cabeça, febre")
                .build();
    }

    public static Consultation buildConsultation() {
        return Consultation.builder()
                .hospital(HospitalHelper.buildHospital())
                .patient(PatientHelper.buildPatientActive())
                .doctor(DoctorHelper.buildDoctor())
                .symptoms("Dor de cabeça, febre")
                .build();
    }
}