package com.api.hospital.service;

import com.api.hospital.helper.AddressHelper;
import com.api.hospital.helper.PatientHelper;
import com.api.hospital.model.entities.Address;
import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.PutPatientRequest;
import com.api.hospital.repository.AddressRepository;
import com.api.hospital.repository.PatientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    private Patient patient;
    private PutPatientRequest putPatientRequest;
    private static final String cpf = "123.456.789-00";

    @Before
    public void before() {
        patient = PatientHelper.buildPatient();
        putPatientRequest = PatientHelper.buildPutPatientRequest();
    }

    @Test
    public void shouldPutNameWhenPatientFound() {
        when(patientRepository.findByCpf(cpf)).thenReturn(Optional.of(patient));

        String oldName = patient.getName();

        patientService.putName(putPatientRequest);

        verify(patientRepository, times(1)).findByCpf(cpf);
        verify(patientRepository, times(1)).save(patient);
        assertNotEquals(patient.getName(), oldName);
    }

    @Test
    public void putNameShouldReturnEntityNotFoundExceptionWhenPatientNotFound() {
        when(patientRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> patientService.putName(putPatientRequest),
                 "Entity not found!");
    }
}