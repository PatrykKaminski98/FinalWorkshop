package pl.coderslab.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.user.User;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History,Long> {

    List<History> findByUserAndDate(User user, LocalDate date);

    History findHistoryByUserAndAndDate(User user, LocalDate date);

    History findHistoryById(long id);
}
