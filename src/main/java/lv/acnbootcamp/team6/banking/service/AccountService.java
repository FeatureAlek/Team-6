package lv.acnbootcamp.team6.banking.service;

import lv.acnbootcamp.team6.banking.dto.AccountResponse;
import lv.acnbootcamp.team6.banking.dto.CreateAccountRequest;

public interface AccountService {
    AccountResponse create(CreateAccountRequest request);
    AccountResponse getById(Long id);
}
