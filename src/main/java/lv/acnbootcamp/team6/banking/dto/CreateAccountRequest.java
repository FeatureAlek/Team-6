package lv.acnbootcamp.team6.banking.dto;

//imports
import lombok.*;
import jakarta.validation.constraints.*;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor

//get POST data -> create ACC
public class CreateAccountRequest
{
    @NotBlank(message = "IBAN cannot be empty")
    private String iban;

    @NotBlank(message = "Owner name cannot be empty")
    private String ownerName;

    @Min(value = 0, message = "Balance cannot be negative")
    private double balance;
}