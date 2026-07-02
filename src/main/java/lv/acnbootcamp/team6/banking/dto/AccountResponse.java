package lv.acnbootcamp.team6.banking.dto;

//imports
import lombok.*;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor

//struct stores user data
public class AccountResponse
{
    private Long id;
    private String iban;
    private String ownerName;
    private double balance;
}

