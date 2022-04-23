package com.api.hospital.service;

import com.api.hospital.helper.ConsultationHelper;
import com.api.hospital.helper.DoctorHelper;
import com.api.hospital.helper.HospitalHelper;
import com.api.hospital.helper.PatientHelper;
import com.api.hospital.model.entities.Consultation;
import com.api.hospital.model.entities.Doctor;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.SaveConsultationRequest;
import com.api.hospital.model.response.GetConsultationResponse;
import com.api.hospital.repository.ConsultationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConsultationServiceTest {

    @InjectMocks
    private ConsultationService consultationService;

    @Mock
    private ConsultationRepository repository;

    @Mock
    private PatientService patientService;

    @Mock
    private DoctorService doctorService;

    @Mock
    private HospitalService hospitalService;

    private SaveConsultationRequest saveConsultationRequest;
    private Patient patient;
    private Hospital hospital;
    private Doctor doctor;
    private Consultation consultation;
    private static final String cpf = "123.456.789-00";
    private final String cnpj = "85.086.201/0001-06";
    private final String crm = "1234567890";
    List<Consultation> consultations = new ArrayList<>();

    @Before
    public void before() {
        patient = PatientHelper.buildPatientActive();
        hospital = HospitalHelper.buildHospital();
        doctor = DoctorHelper.buildDoctor();
        saveConsultationRequest = ConsultationHelper.buildSaveConsultationRequest();
        consultation = ConsultationHelper.buildConsultation();
        consultations.add(consultation);
    }

    @Test
    public void shouldSaveConsultation() {
        when(patientService.findByCpf(cpf)).thenReturn(patient);
        when(doctorService.findDoctorByCrm(crm)).thenReturn(doctor);
        when(hospitalService.findByCnpj(cnpj)).thenReturn(hospital);

        consultationService.save(saveConsultationRequest);

        verify(patientService, times(1)).findByCpf(cpf);
        verify(doctorService, times(1)).findDoctorByCrm(crm);
        verify(hospitalService, times(1)).findByCnpj(cnpj);
        verify(repository, times(1)).save(consultation);
    }

    @Test
    public void getByCrmShouldReturnGetConsultationResponse() {
        when(repository.findByDoctorCrm(crm)).thenReturn(consultations);

        List<GetConsultationResponse> response = consultationService.getByCrm(crm);

        verify(repository, times(1)).findByDoctorCrm(crm);
        assertEquals(response.get(0).getCrm(), consultations.get(0).getDoctor().getCrm());
        assertEquals(response.get(0).getDoctorName(), consultations.get(0).getDoctor().getName());
        assertEquals(response.get(0).getHospitalCnpj(), consultations.get(0).getHospital().getCnpj());
        assertEquals(response.get(0).getHospitalName(), consultations.get(0).getHospital().getName());
        assertEquals(response.get(0).getPatientCpf(), consultations.get(0).getPatient().getCpf());
        assertEquals(response.get(0).getPatientName(), consultations.get(0).getPatient().getName());
        assertEquals(response.get(0).getSymptoms(), consultations.get(0).getSymptoms());
    }

    @Test
    public void getByCrmShouldReturnResponseEmptyWhenConsultationNotFound() {
        List<GetConsultationResponse> response = consultationService.getByCrm(crm);

        assertTrue(response.isEmpty());
    }

    @Test
    public void getByCpfShouldReturnGetConsultationResponse() {
        when(repository.findByPatientCpf(cpf)).thenReturn(consultations);

        List<GetConsultationResponse> response = consultationService.getByCpf(cpf);

        verify(repository, times(1)).findByPatientCpf(cpf);
        assertEquals(response.get(0).getCrm(), consultations.get(0).getDoctor().getCrm());
        assertEquals(response.get(0).getDoctorName(), consultations.get(0).getDoctor().getName());
        assertEquals(response.get(0).getHospitalCnpj(), consultations.get(0).getHospital().getCnpj());
        assertEquals(response.get(0).getHospitalName(), consultations.get(0).getHospital().getName());
        assertEquals(response.get(0).getPatientCpf(), consultations.get(0).getPatient().getCpf());
        assertEquals(response.get(0).getPatientName(), consultations.get(0).getPatient().getName());
        assertEquals(response.get(0).getSymptoms(), consultations.get(0).getSymptoms());
    }

    @Test
    public void getByCpfShouldReturnResponseEmptyWhenConsultationNotFound() {
        List<GetConsultationResponse> response = consultationService.getByCpf(cpf);

        assertTrue(response.isEmpty());
    }

    @Test
    public void getByCnpjShouldReturnGetConsultationResponse() {
        when(repository.findByHospitalCnpj(cnpj)).thenReturn(consultations);

        List<GetConsultationResponse> response = consultationService.getByCnpj(cnpj);

        verify(repository, times(1)).findByHospitalCnpj(cnpj);
        assertEquals(response.get(0).getCrm(), consultations.get(0).getDoctor().getCrm());
        assertEquals(response.get(0).getDoctorName(), consultations.get(0).getDoctor().getName());
        assertEquals(response.get(0).getHospitalCnpj(), consultations.get(0).getHospital().getCnpj());
        assertEquals(response.get(0).getHospitalName(), consultations.get(0).getHospital().getName());
        assertEquals(response.get(0).getPatientCpf(), consultations.get(0).getPatient().getCpf());
        assertEquals(response.get(0).getPatientName(), consultations.get(0).getPatient().getName());
        assertEquals(response.get(0).getSymptoms(), consultations.get(0).getSymptoms());
    }

    @Test
    public void getByCnpjShouldReturnResponseEmptyWhenConsultationNotFound() {
        List<GetConsultationResponse> response = consultationService.getByCnpj(cpf);

        assertTrue(response.isEmpty());
    }
}