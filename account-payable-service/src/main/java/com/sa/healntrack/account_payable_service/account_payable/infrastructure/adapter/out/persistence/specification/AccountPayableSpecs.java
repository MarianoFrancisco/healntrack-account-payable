package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.specification;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.Status;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.entity.AccountPayableEntity;

public class AccountPayableSpecs {

    public static Specification<AccountPayableEntity> hasPatientId(UUID patientId) {
        return (root, query, criteriaBuilder) -> (patientId == null)
                ? null
                : criteriaBuilder.equal(root.get("patientId"), patientId);
    }

    public static Specification<AccountPayableEntity> hasHospitalizationId(UUID hospitalizationId) {
        return (root, query, criteriaBuilder) -> (hospitalizationId == null)
                ? null
                : criteriaBuilder.equal(root.get("hospitalizationId"), hospitalizationId);
    }

    public static Specification<AccountPayableEntity> hasStatus(Status status) {
        return (root, query, criteriaBuilder) -> (status == null)
                ? null
                : criteriaBuilder.equal(root.get("status"), status);
    }
    
}
