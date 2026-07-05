package lv.acnbootcamp.team6.banking.service;

import lv.acnbootcamp.team6.banking.dto.AccountResponse;
import lv.acnbootcamp.team6.banking.dto.CreateAccountRequest;
import lv.acnbootcamp.team6.banking.dto.TransactionResponse;
import lv.acnbootcamp.team6.banking.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class AccountServiceImpl implements AccountService {

    private final Map<Long, Account> accounts = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong();

    @Override
    public AccountResponse create(CreateAccountRequest request) {
        Account account = new Account();
        account.setId(idSequence.incrementAndGet());
        account.setOwnerName(request.getOwnerName());
        account.setBalance(request.getInitialBalance());
        account.setIban(generateIban());
        accounts.put(account.getId(), account);
        return toResponse(account);
    }

    @Override
    public AccountResponse getById(Long id) {
        return toResponse(requireAccount(id));
    }

    @Override
    public List<TransactionResponse> getTransactions(Long id) {
        requireAccount(id);
        return List.of();   // no transactions yet — populated once the transfer feature exists
    }

    private Account requireAccount(Long id) {
        Account account = accounts.get(id);
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found: " + id);
        }
        return account;
    }

    private AccountResponse toResponse(Account account) {
        return new AccountResponse(account.getId(), account.getIban(),
                account.getOwnerName(), account.getBalance());
    }

    private String generateIban() {
        return "LV%018d".formatted(ThreadLocalRandom.current().nextLong(1_000_000_000_000_000_000L));
    }

    //Actual Money Transfer
    public void transferMoney(Long fromId, Long toId, double amount)
    {
        Account from = requireAccount(fromId);
        Account to = requireAccount(toId);

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
    }
}