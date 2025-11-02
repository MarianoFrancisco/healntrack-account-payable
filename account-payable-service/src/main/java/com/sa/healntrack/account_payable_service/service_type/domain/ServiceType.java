package com.sa.healntrack.account_payable_service.service_type.domain;

import java.util.UUID;

import com.sa.healntrack.account_payable_service.service_type.domain.value_object.Name;
import com.sa.healntrack.account_payable_service.service_type.domain.value_object.ServiceTypeId;

import lombok.Getter;

@Getter
public class ServiceType {

    private final ServiceTypeId id;
    private final Name name;

    public ServiceType(UUID id, String name) {
        this.id = new ServiceTypeId(id);
        this.name = new Name(name);
    }

}
