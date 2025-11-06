package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.rest.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hospitalization-service", path = "/api/v1/hospitalizations")
public interface HospitalizationClient {
    
    @GetMapping("/{id}")
    void findById(@PathVariable UUID id);

}
