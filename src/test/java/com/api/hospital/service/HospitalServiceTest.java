package com.api.hospital.service;


import com.api.hospital.helper.HospitalHelper;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.PutHospitalRequest;
import com.api.hospital.model.response.GetHospitalByNameResponse;
import com.api.hospital.repository.HospitalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HospitalServiceTest {

    @InjectMocks
    private HospitalService hospitalService;

    @Mock
    private HospitalRepository hospitalRepository;

    private Hospital hospital;
    private PutHospitalRequest putHospitalRequest;
    private final String name = "Hospital Test";
    private final String cnpj = "85.086.201/0001-06";

    @Captor
    private ArgumentCaptor<Hospital> hospitalCaptor;

    @Before
    public void before() {
        hospital = HospitalHelper.buildHospital();
        putHospitalRequest = HospitalHelper.buildPutHospitalRequest();
    }

    @Test
    public void findHospitalByNameShouldReturnGetHospitalByNameResponseWhenHospitalFound() {
        when(hospitalRepository.findByName(name)).thenReturn(Optional.of(hospital));

        GetHospitalByNameResponse response = hospitalService.findHospitalByName(name);

        verify(hospitalRepository, times( 1)).findByName(name);
        assertEquals(response.getId(), hospital.getId());
        assertEquals(response.getAvailableBeds(), hospital.getAvailableBeds());
        assertEquals(response.getTotalBeds(), hospital.getTotalBeds());
        assertEquals(response.getName(), hospital.getName());
        assertEquals(response.getCnpj(), hospital.getCnpj());
        assertEquals(response.getCep(), hospital.getAddress().getCep());
        assertEquals(response.getDistrict(), hospital.getAddress().getDistrict());
        assertEquals(response.getCity(), hospital.getAddress().getCity());
        assertEquals(response.getState(), hospital.getAddress().getState());
        assertEquals(response.getStreet(), hospital.getAddress().getStreet());
    }

    @Test
    public void findHospitalByNameShouldReturnEntityNotFoundExceptionWhenHospitalNotFound() {
        when(hospitalRepository.findByName(name)).thenReturn(Optional.empty());

        assertThrows( EntityNotFoundException.class,
                () -> hospitalService.findHospitalByName(name),
                "Entity not found!") ;
    }

    @Test
    public void changeNumberAvailableBeds() {
        when(hospitalRepository.findByCnpj(cnpj)).thenReturn(Optional.of(hospital));
        Long availableBeds = hospital.getAvailableBeds() - putHospitalRequest.getBedsOccupation();

        hospitalService.changeNumberAvailableBeds(putHospitalRequest);

        verify(hospitalRepository, times( 1)).findByCnpj(cnpj);
        verify(hospitalRepository, times( 1)).save(hospital);
        assertEquals(hospital.getAvailableBeds(), availableBeds);
    }

    @Test
    public void changeNumberAvailableBedsShouldEntityNotFoundExceptionWhenHospitalNotFound() {
        when(hospitalRepository.findByCnpj(cnpj)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> hospitalService.changeNumberAvailableBeds(putHospitalRequest)
                , "Entity not found!");
    }
}
