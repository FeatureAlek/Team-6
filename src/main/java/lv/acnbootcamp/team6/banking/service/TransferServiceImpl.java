package lv.acnbootcamp.team6.banking.service;

import lv.acnbootcamp.team6.banking.dto.AccountResponse;
import lv.acnbootcamp.team6.banking.dto.TransactionResponse;
import lv.acnbootcamp.team6.banking.dto.TransferRequest;
import lv.acnbootcamp.team6.banking.model.Transaction;
import lv.acnbootcamp.team6.banking.model.TransactionType;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TransferServiceImpl implements TransferService {
    private final AccountServiceImpl accountService;
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    private final List<Transaction> transactions = new ArrayList<>();

    public TransferServiceImpl(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @Override
    public TransactionResponse transfer(TransferRequest request) {
        AccountResponse from = accountService.getById(request.getFromAccountId());
        AccountResponse to = accountService.getById(request.getToAccountId());

        if (to == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid receiver account");
        }

        if (from == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sender account");
        }

        if (from.getBalance() < request.getAmount()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }

        // update balances
        from.setBalance(from.getBalance() - request.getAmount());
        to.setBalance(to.getBalance() + request.getAmount());

        Transaction transaction = Transaction.builder()
                .id(ID_GENERATOR.incrementAndGet())
                .fromAccount(from)
                .toAccount(to)
                .type(TransactionType.TRANSFER)
                .amount(request.getAmount())
                .createdAt(LocalDateTime.now())
                .note(request.getNote())
                .build();

        transactions.add(transaction);

        return TransactionResponse.builder()
                .id(transaction.getId())
                .fromAccountId(from.getId())
                .toAccountId(to.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .note(transaction.getNote())
                .build();

    }

    @Override
    public List<TransactionResponse> getTransactionsByAccount(Long accountId) {
        return transactions.stream()
                .filter(t ->
                        t.getFromAccount().getId().equals(accountId)
                                || t.getToAccount().getId().equals(accountId))
                .map(t -> TransactionResponse.builder()
                        .id(t.getId())
                        .fromAccountId(t.getFromAccount().getId())
                        .toAccountId(t.getToAccount().getId())
                        .type(t.getType())
                        .amount(t.getAmount())
                        .createdAt(t.getCreatedAt())
                        .note(t.getNote())
                        .build())
                .toList();
    }
}
