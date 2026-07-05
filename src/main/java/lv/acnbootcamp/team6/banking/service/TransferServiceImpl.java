package lv.acnbootcamp.team6.banking.service;

import lv.acnbootcamp.team6.banking.dto.AccountResponse;
import lv.acnbootcamp.team6.banking.dto.TransactionResponse;
import lv.acnbootcamp.team6.banking.dto.TransferRequest;
import lv.acnbootcamp.team6.banking.model.Transaction;
import lv.acnbootcamp.team6.banking.model.TransactionType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransferServiceImpl implements TransferService {
    private final AccountServiceImpl accountService;
    private static final AtomicLong TRANSACTION_ID = new AtomicLong();
    private static final AtomicLong TRANSFER_GROUP_ID = new AtomicLong();

    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public TransferServiceImpl(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @Override
    public List<TransactionResponse> transfer(TransferRequest request) {
        AccountResponse from = accountService.getById(request.getFromAccountId());
        AccountResponse to = accountService.getById(request.getToAccountId());

        if (from == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sender account");
        }

        if (to == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid receiver account");
        }

        if (from.getId().equals(to.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot transfer to the same account");
        }

        double amount = request.getAmount().doubleValue();

        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than zero");
        }

        if (from.getBalance() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }

        accountService.transferMoney(request.getFromAccountId(), request.getToAccountId(), amount);

        Long groupId = TRANSFER_GROUP_ID.incrementAndGet();
        LocalDateTime createdAt = LocalDateTime.now();

        Transaction withdrawal = Transaction.builder()
                .id(TRANSACTION_ID.incrementAndGet())
                .account(from)
                .type(TransactionType.WITHDRAWAL)
                .amount(amount)
                .createdAt(createdAt)
                .note(request.getNote())
                .transferGroupId(groupId)
                .build();

        Transaction deposit = Transaction.builder()
                .id(TRANSACTION_ID.incrementAndGet())
                .account(to)
                .type(TransactionType.DEPOSIT)
                .amount(amount)
                .createdAt(createdAt)
                .note(request.getNote())
                .transferGroupId(groupId)
                .build();

        transactions.add(withdrawal);
        transactions.add(deposit);

        return List.of(
                TransactionResponse.builder()
                        .id(withdrawal.getId())
                        .accountId(withdrawal.getAccount().getId())
                        .type(withdrawal.getType())
                        .amount(withdrawal.getAmount())
                        .createdAt(withdrawal.getCreatedAt())
                        .note(withdrawal.getNote())
                        .transferGroupId(groupId)
                        .build(),

                TransactionResponse.builder()
                        .id(deposit.getId())
                        .accountId(deposit.getAccount().getId())
                        .type(deposit.getType())
                        .amount(deposit.getAmount())
                        .createdAt(deposit.getCreatedAt())
                        .note(deposit.getNote())
                        .transferGroupId(groupId)
                        .build()
        );
    }

    @Override
    public List<TransactionResponse> getTransactionsByAccount(Long accountId) {
        return transactions.stream()
                .filter(t -> t.getAccount().getId().equals(accountId))
                .map(t -> TransactionResponse.builder()
                        .id(t.getId())
                        .accountId(t.getAccount().getId())
                        .type(t.getType())
                        .amount(t.getAmount())
                        .createdAt(t.getCreatedAt())
                        .note(t.getNote())
                        .transferGroupId(t.getTransferGroupId())
                        .build())
                .toList();
    }
}