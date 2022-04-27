package pl.coderslab.history;

import lombok.Data;
import pl.coderslab.account.appUser.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private LocalDate date;
}
