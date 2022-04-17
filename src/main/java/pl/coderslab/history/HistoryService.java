package pl.coderslab.history;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {

        this.historyRepository = historyRepository;
    }


    public History save(History history){
        return historyRepository.save(history);
    }

    /*
    public LocalDate findDatebyIngredient(long ingredient_id){
        History history = historyRepository.findHistoryByIngredientsId(ingredient_id);
        return history.getDate();
    }*/

    public void delete(History history){
        historyRepository.delete(history);
    }

    public History findById(long id){
        return historyRepository.findHistoryById(id);
    }

}
