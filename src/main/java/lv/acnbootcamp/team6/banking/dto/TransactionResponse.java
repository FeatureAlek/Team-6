package lv.acnbootcamp.team6.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.acnbootcamp.team6.banking.model.TransactionType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long id;
    private Long accountId;
    private TransactionType type;
    private double amount;
    private LocalDateTime createdAt;
    private String note;
}
