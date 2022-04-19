package com.api.hospital.service;

import com.api.hospital.helper.DoctorHelper;
import com.api.hospital.helper.HospitalHelper;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.SaveDoctorRequest;
import com.api.hospital.repository.AddressRepository;
import com.api.hospital.repository.DoctorRepository;
import com.api.hospital.repository.HospitalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private HospitalRepository hospitalRepository;

    @Mock
    private AddressRepository addressRepository;

    private SaveDoctorRequest request;
    private Hospital hospital;

    @Before
    public void before() {
        request = DoctorHelper.buildSaveDoctorRequest();
        hospital = HospitalHelper.buildHospital();
    }

    @Test
    public void saveDoctorShouldReturnEntityNotFoundExceptionHospitalNotFound() {
        when(hospitalRepository.findByCnpj(request.getHospitalCnpj())).thenReturn(Optional.of(hospital));

        doctorService.saveDoctor(request);

        verify(hospitalRepository, times(1)).findByCnpj(request.getHospitalCnpj());
        verify(doctorRepository, times(1)).save(any());
        verify(addressRepository, times(1)).save(any());
    }

    @Test
    public void saveDoctorShouldSaveWhenHospitalFound() {
        when(hospitalRepository.findByCnpj(request.getHospitalCnpj())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () ->  doctorService.saveDoctor(request),
                "Entity not found!");
    }
}