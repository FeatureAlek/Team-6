package lv.acnbootcamp.team6.banking.dto;

//imports
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

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

    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    private String note;
}