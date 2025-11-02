package com.sa.healntrack.account_payable_service.account_payable.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.AccountPayableId;
import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.HospitalizationId;
import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.Money;
import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.Status;

import lombok.Getter;

@Getter
public class AccountPayable {

    private final AccountPayableId id;
    private final HospitalizationId hospitalizationId;
    private Money totalFee;
    private Status status;
    private LocalDate closingDate;
    private final List<AccountPayableItem> items;

    public AccountPayable(UUID id, UUID hospitalizationId, BigDecimal totalFee,
            Status status, LocalDate closingDate, List<AccountPayableItem> items) {
        this.id = new AccountPayableId(id);
        this.hospitalizationId = new HospitalizationId(hospitalizationId);
        this.totalFee = new Money(totalFee);
        this.status = status;
        this.closingDate = closingDate;
        this.items = items;
    }

    private void recalculateTotalFee() {
        BigDecimal sum = items.stream()
                .map(item -> item.getFee().value())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.totalFee = new Money(sum);
    }

    public void addItem(AccountPayableItem item) {
        if (this.status == Status.CLOSED) {
            throw new IllegalStateException("No se pueden agregar items a una cuenta cerrada");
        }
        this.items.add(item);
        this.recalculateTotalFee();
    }

    public void removeItem(AccountPayableItem item) {
        if (this.status == Status.CLOSED) {
            throw new IllegalStateException("No se pueden remover items de una cuenta cerrada");
        }
        boolean removed = this.items.removeIf(i -> i.getId().equals(item.getId()));
        if (!removed) {
            throw new IllegalArgumentException(
                    "No se encontró un item con id: " + item.getId().value());
        }
        this.recalculateTotalFee();
    }

    public void close() {
        this.status = Status.CLOSED;
        this.closingDate = LocalDate.now();
    }
    
}
