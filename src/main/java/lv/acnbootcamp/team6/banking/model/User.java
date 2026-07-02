package lv.acnbootcamp.team6.banking.model;

//imports
import lombok.*;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User
{
    private Long id;
    private String username;
    private String passwordHash;
    private Role role;
}
