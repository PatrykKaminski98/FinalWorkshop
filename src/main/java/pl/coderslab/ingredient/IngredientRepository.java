package pl.coderslab.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findById(long id);
}
