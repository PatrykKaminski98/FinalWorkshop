package pl.coderslab.history;

import org.springframework.stereotype.Service;
import pl.coderslab.account.appUser.User;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }


    public History createAndSaveHistory(LocalDate date, User user){
        History history = new History();
        history.setUser(user);
        history.setDate(date);

        return historyRepository.save(history);
    }


    public void delete(History history){
        historyRepository.delete(history);
    }

    public History findById(long id){
        return historyRepository.findHistoryById(id);
    }

}
