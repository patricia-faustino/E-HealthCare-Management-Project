package com.api.hospital.helper;

import com.api.hospital.model.entities.Address;

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
}