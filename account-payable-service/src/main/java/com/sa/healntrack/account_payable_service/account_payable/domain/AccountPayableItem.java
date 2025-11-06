package com.sa.healntrack.account_payable_service.account_payable.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.AccountPayableItemId;
import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.Money;
import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.ReferenceId;
import com.sa.healntrack.account_payable_service.service_type.domain.ServiceType;

import lombok.Getter;


@Getter
public class AccountPayableItem {
    
    private final AccountPayableItemId id;
    private final ServiceType serviceType;
    private final ReferenceId referenceId;
    private Money fee;
    private final LocalDate serviceDate;

    public AccountPayableItem(UUID id, ServiceType serviceType,
            UUID referenceId, BigDecimal fee, LocalDate serviceDate) {
        this.id = new AccountPayableItemId(id);
        this.serviceType = serviceType;
        this.referenceId = new ReferenceId(referenceId);
        this.fee = new Money(fee);
        this.serviceDate = serviceDate;
    }

    public void updateFee(BigDecimal newFee) {
        this.fee = new Money(newFee);
    }


}
