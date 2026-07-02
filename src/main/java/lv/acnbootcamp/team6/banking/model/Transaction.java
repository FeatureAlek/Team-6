package lv.acnbootcamp.team6.banking.model;

//imports
import lombok.*;
import java.time.LocalDateTime;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction
{
    private Long id;
    private Account account;
    private TransactionType type;
    private double amount;
    private LocalDateTime createdAt;
    private String note;
}