package lv.acnbootcamp.team6.banking.dto;

//imports
import lombok.*;
import lv.acnbootcamp.team6.banking.model.TransactionType;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private TransactionType type;
    private double amount;
    private LocalDateTime createdAt;
    private String note;
}