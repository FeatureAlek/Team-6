package lv.acnbootcamp.team6.banking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {
    @NotBlank
    private String ownerName;
    @PositiveOrZero
    private double initialBalance;
}
