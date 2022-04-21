package com.api.hospital.service;

import com.api.hospital.helper.PatientHelper;
import com.api.hospital.model.entities.Patient;
import com.api.hospital.model.request.PutPatientRequest;
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
    private Patient patientInactive;
    private PutPatientRequest putPatientRequest;
    private static final String cpf = "123.456.789-00";

    @Before
    public void before() {
        patient = PatientHelper.buildPatientActive();
        putPatientRequest = PatientHelper.buildPutPatientRequest();
        patientInactive = PatientHelper.buildPatientInactive();
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

    @Test
    public void shouldDeletePatient() {
        when(patientRepository.findByCpf(cpf)).thenReturn(Optional.of(patient));
        Boolean oldStatus = patient.getStatus();

        patientService.delete(cpf);

        verify(patientRepository, times(1)).findByCpf(cpf);
        verify(patientRepository, times(1)).save(patient);
        assertNotEquals(patient.getStatus(), oldStatus);
    }

    @Test
    public void deleteShouldReturnEntityNotFoundExceptionWhenPatientNotFound() {
        when(patientRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> patientService.delete(cpf),
                "Entity not found!");
    }

    @Test
    public void deleteShouldReturnUnsupportedOperationExceptionWhenPatientIsInactive() {
        when(patientRepository.findByCpf(cpf)).thenReturn(Optional.of(patientInactive));

        assertThrows(UnsupportedOperationException.class,
                () -> patientService.delete(cpf),
                "Patient is already inactive!");
    }
}