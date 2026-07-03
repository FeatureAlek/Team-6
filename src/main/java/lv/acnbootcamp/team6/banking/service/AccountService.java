package lv.acnbootcamp.team6.banking.service;

import lv.acnbootcamp.team6.banking.dto.AccountResponse;
import lv.acnbootcamp.team6.banking.dto.CreateAccountRequest;
import lv.acnbootcamp.team6.banking.dto.TransactionResponse;

import java.util.List;

public interface AccountService {
    AccountResponse create(CreateAccountRequest request);
    AccountResponse getById(Long id);
    List<TransactionResponse> getTransactions(Long id);
}
