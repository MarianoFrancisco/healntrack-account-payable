package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.close_account_payable.CloseAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.get_all_accounts_payable.GetAllAccountsPayable;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.dto.AccountPayableResponseDTO;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.dto.SearchAccountsPayableRequestDTO;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.mapper.AccountPayableRestMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts-payable")
@RestController
public class AccountPayableController {

    private final AccountPayableRestMapper mapper;
    private final GetAllAccountsPayable getAllAccountsPayable;
    private final CloseAccountPayable closeAccountPayable;

    @GetMapping
    public ResponseEntity<List<AccountPayableResponseDTO>> getAll(@Valid SearchAccountsPayableRequestDTO requestDTO) {
        List<AccountPayableResponseDTO> accountsPayable = getAllAccountsPayable
                .getAll(mapper.toQuery(requestDTO))
                .stream()
                .map(mapper::fromDomain)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(accountsPayable);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> close(@PathVariable UUID id) {
        closeAccountPayable.close(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    
}
