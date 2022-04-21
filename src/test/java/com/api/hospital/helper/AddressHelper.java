package com.api.hospital.helper;

import com.api.hospital.model.entities.Address;
import com.api.hospital.model.request.SaveAddressRequest;

public class AddressHelper {

    public static Address buildAddress() {
        return Address.builder()
                .cep("54753-782")
                .city("Manaus")
                .state("Amazonas")
                .district("Alvorada")
                .street("Rua C 11")
                .build();
    }

    public static SaveAddressRequest buildSaveAddressRequest() {
        return SaveAddressRequest.builder()
                .cep("54753-782")
                .city("Manaus")
                .state("Amazonas")
                .district("Alvorada")
                .street("Rua C 11")
                .build();
    }
}