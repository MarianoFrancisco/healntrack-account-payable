package com.sa.healntrack.account_payable_service.service_type.infrastructure.adapter.out.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Data
@Table(name = "service_type")
@Entity
public class ServiceTypeEntity {
    
    @Id
    private UUID id;
    private String name;

}
