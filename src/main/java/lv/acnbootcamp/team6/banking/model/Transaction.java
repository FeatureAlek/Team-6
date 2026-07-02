package lv.acnbootcamp.team6.banking.model;

//imports
import lombok.*;
import lv.acnbootcamp.team6.banking.dto.AccountResponse;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Transaction
{
    private Long id;
    private AccountResponse fromAccount;
    private AccountResponse toAccount;
    private TransactionType type;
    private double amount;
    private LocalDateTime createdAt;
    private String note;
}