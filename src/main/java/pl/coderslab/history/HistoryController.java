package pl.coderslab.history;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.mealNutrition.MealNutritionService;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;
    private final MealNutritionService mealNutritionService;

    public HistoryController(HistoryService historyService, MealNutritionService mealNutritionService) {
        this.historyService = historyService;
        this.mealNutritionService = mealNutritionService;
    }

    @RequestMapping("delete_meal/{id}")
    public String deleteMeal(@PathVariable long id){
        History history = historyService.findById(id);
        mealNutritionService.deleteByHistory(history);
        historyService.delete(history);
        return "redirect:/mealNutritions/table/" + history.getDate().toString();
    }






}
