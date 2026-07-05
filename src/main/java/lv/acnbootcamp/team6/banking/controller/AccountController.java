package lv.acnbootcamp.team6.banking.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lv.acnbootcamp.team6.banking.dto.AccountResponse;
import lv.acnbootcamp.team6.banking.dto.CreateAccountRequest;
import lv.acnbootcamp.team6.banking.dto.TransactionResponse;
import lv.acnbootcamp.team6.banking.service.AccountService;
import lv.acnbootcamp.team6.banking.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@Valid @RequestBody CreateAccountRequest request){
        return accountService.create(request);
    }

    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionResponse> transactions(@PathVariable Long id) {
        accountService.getById(id); // throws 404 if account not found
        return transferService.getTransactionsByAccount(id);
    }
}
