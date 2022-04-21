package com.api.hospital.service;


import com.api.hospital.helper.HospitalHelper;
import com.api.hospital.model.entities.Hospital;
import com.api.hospital.model.request.PutHospitalRequest;
import com.api.hospital.model.response.GetHospitalsByNameResponse;
import com.api.hospital.repository.HospitalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HospitalServiceTest {

    @InjectMocks
    private HospitalService hospitalService;

    @Mock
    private HospitalRepository hospitalRepository;

    private Hospital hospital;
    private List<Hospital> hospitals = new ArrayList<>();
    private PutHospitalRequest putHospitalRequest;
    private final String name = "Hospital Test";
    private final String cnpj = "85.086.201/0001-06";

    @Before
    public void before() {
        hospital = HospitalHelper.buildHospital();
        putHospitalRequest = HospitalHelper.buildPutHospitalRequest();
        hospitals.add(hospital);
    }

    @Test
    public void findHospitalsByNameShouldReturnGetHospitalsByNameResponseWhenHospitalFound() {
        when(hospitalRepository.findByNameContainingIgnoreCase(name)).thenReturn(hospitals);

        List<GetHospitalsByNameResponse> response = hospitalService.findHospitalsByName(name);

        verify(hospitalRepository, times( 1)).findByNameContainingIgnoreCase(name);
        assertEquals(response.get(0).getId(), hospital.getId());
        assertEquals(response.get(0).getAvailableBeds(), hospital.getAvailableBeds());
        assertEquals(response.get(0).getTotalBeds(), hospital.getTotalBeds());
        assertEquals(response.get(0).getName(), hospital.getName());
        assertEquals(response.get(0).getCnpj(), hospital.getCnpj());
        assertEquals(response.get(0).getCep(), hospital.getAddress().getCep());
        assertEquals(response.get(0).getDistrict(), hospital.getAddress().getDistrict());
        assertEquals(response.get(0).getCity(), hospital.getAddress().getCity());
        assertEquals(response.get(0).getState(), hospital.getAddress().getState());
        assertEquals(response.get(0).getStreet(), hospital.getAddress().getStreet());
    }

    @Test
    public void findHospitalsByNameShouldReturnEntityNotFoundExceptionWhenHospitalNotFound() {
        when(hospitalRepository.findByNameContainingIgnoreCase(name)).thenReturn(null);

        List<GetHospitalsByNameResponse> response = hospitalService.findHospitalsByName(name);

        assertTrue(response.isEmpty());
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
