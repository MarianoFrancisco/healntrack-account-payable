package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Data
@Table(name = "account_payable")
@Entity
public class AccountPayableEntity {
    
    @Id
    private UUID id;
    private UUID hospitalizationId;
    private BigDecimal totalFee;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate closingDate;
    @OneToMany(
            mappedBy = "accountPayable",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            orphanRemoval = true)
    private List<AccountPayableItemEntity> items;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
