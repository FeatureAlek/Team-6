package lv.acnbootcamp.team6.banking.model;

//imports
import lombok.*;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Account
{
    private Long id;
    private String iban;
    private String ownerName;
    private double balance;
}
