package com.sa.healntrack.account_payable_service.service_type.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.service_type.domain.ServiceType;
import com.sa.healntrack.account_payable_service.service_type.infrastructure.adapter.out.persistence.entity.ServiceTypeEntity;

@Mapper(componentModel = "spring")
public interface ServiceTypePersistenceMapper {
    
    ServiceType toDomain(ServiceTypeEntity entity);

    @Mapping(target = "id", source = "serviceType.id.value")
    @Mapping(target = "name", source = "serviceType.name.value")
    ServiceTypeEntity toEntity(ServiceType serviceType);

}
