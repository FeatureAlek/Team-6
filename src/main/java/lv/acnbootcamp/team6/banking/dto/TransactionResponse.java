package lv.acnbootcamp.team6.banking.dto;

//imports
import lombok.*;
import lv.acnbootcamp.team6.banking.model.TransactionType;
import java.time.LocalDateTime;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor

//Transaction Response
public class TransactionResponse
{
    private Long id;
    private Long accountId;
    private TransactionType type;
    private double amount;
    private LocalDateTime createdAt;
    private String note;
}