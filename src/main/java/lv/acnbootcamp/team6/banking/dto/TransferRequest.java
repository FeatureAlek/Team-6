package lv.acnbootcamp.team6.banking.dto;

//imports
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor

// DTO for transferring money between two accounts
public class TransferRequest
{
    @NotNull(message = "Source account ID is required")
    private Long fromAccountId;

    @NotNull(message = "Destination account ID is required")
    private Long toAccountId;

    @Min(value = 1, message = "Amount must be greater than 0")
    private double amount;

    private String note;
}