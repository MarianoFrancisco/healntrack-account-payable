package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.rest;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.healntrack.account_payable_service.account_payable.application.port.out.rest.hospitalization.CheckHospitalizationExistence;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.rest.client.HospitalizationClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class HospitalizationAPI implements CheckHospitalizationExistence {
    
    private final HospitalizationClient hospitalizationClient;

    @Override
    public void checkExistence(UUID id) {
        hospitalizationClient.findById(id);
    }

}
