package com.api.hospital.service;

import com.api.hospital.helper.DoctorHelper;
import com.api.hospital.helper.HospitalHelper;
import com.api.hospital.model.entities.Doctor;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.PutDoctorRequest;
import com.api.hospital.model.request.SaveDoctorRequest;
import com.api.hospital.model.response.GetByCrmResponse;
import com.api.hospital.repository.DoctorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private HospitalService hospitalService;

    @Mock
    private AddressService addressService;

    private SaveDoctorRequest saveDoctorRequest;
    private Hospital hospital;
    private Doctor doctor;
    private PutDoctorRequest putDoctorRequest;
    private final String crm = "1234567890";

    @Before
    public void before() {
        saveDoctorRequest = DoctorHelper.buildSaveDoctorRequest();
        hospital = HospitalHelper.buildHospital();
        doctor = DoctorHelper.buildDoctor();
        putDoctorRequest = DoctorHelper.buildPutDoctorRequest();
    }

    @Test
    public void saveShouldReturnEntityNotFoundExceptionHospitalNotFound() {
        when(hospitalService.findByCnpj(saveDoctorRequest.getHospitalCnpj())).thenReturn(hospital);

        doctorService.save(saveDoctorRequest);

        verify(hospitalService, times(1)).findByCnpj(saveDoctorRequest.getHospitalCnpj());
        verify(doctorRepository, times(1)).save(any());
        verify(addressService, times(1)).save(any());
    }

    @Test
    public void getByCrmShouldReturnGetByCrmResponseDoctorFound() {
        when(doctorRepository.findByCrm(crm)).thenReturn(Optional.of(doctor));

        GetByCrmResponse response = doctorService.getByCrm(crm);

        verify(doctorRepository, times(1)).findByCrm(crm);
        assertEquals(response.getCrm(), doctor.getCrm());
        assertEquals(response.getHospitalCnpj(), doctor.getHospital().getCnpj());
        assertEquals(response.getId(), doctor.getId());
        assertEquals(response.getName(), doctor.getName());
        assertEquals(response.getSpecialty(), doctor.getSpecialty().name());
    }

    @Test
    public void getByCrmShouldReturnEntityNotFoundExceptionWhenDoctorNotFound() {
        when(doctorRepository.findByCrm(crm)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> doctorService.getByCrm(crm)
                , "Entity not found!");
    }

    @Test
    public void deleteShouldReturnEntityNotFoundExceptionWhenDoctorNotFound() {
        when(doctorRepository.findByCrm(crm)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> doctorService.delete(crm)
                , "Entity not found!");
    }

    @Test
    public void deleteShouldDeleteDoctor() {
        when(doctorRepository.findByCrm(crm)).thenReturn(Optional.of(doctor));

        doctorService.delete(crm);
        verify(doctorRepository, times(1)).findByCrm(crm);
        verify(doctorRepository, times(1)).delete(doctor);
    }

    @Test
    public void putAddressDoctorShouldReturnEntityNotFoundExceptionWhenDoctorNotFound() {
        when(doctorRepository.findByCrm(crm)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> doctorService.putAddressDoctor(putDoctorRequest)
                , "Entity not found!");
    }

    @Test
    public void putAddressDoctorShouldUpdateAddress() {
        when(doctorRepository.findByCrm(crm)).thenReturn(Optional.of(doctor));

        doctorService.putAddressDoctor(putDoctorRequest);
        verify(doctorRepository, times(1)).findByCrm(crm);
        verify(doctorRepository, times(1)).save(doctor);
    }
}