package lv.acnbootcamp.team6.banking.controller;

import jakarta.validation.Valid;
import lv.acnbootcamp.team6.banking.dto.TransactionResponse;
import lv.acnbootcamp.team6.banking.dto.TransferRequest;
import lv.acnbootcamp.team6.banking.service.TransferServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferServiceImpl transferService;

    public TransferController(TransferServiceImpl transferService) {
        this.transferService = transferService;
    }


    @PostMapping
    public List<TransactionResponse> transfer(@Valid @RequestBody TransferRequest request) {
        return transferService.transfer(request);
    }

    @GetMapping("/account/{accountId}")
    public List<TransactionResponse> getTransactions(@PathVariable Long accountId) {
        return transferService.getTransactionsByAccount(accountId);
    }
}
