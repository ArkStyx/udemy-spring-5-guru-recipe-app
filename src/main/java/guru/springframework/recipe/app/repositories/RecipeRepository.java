package guru.springframework.recipe.app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.recipe.app.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
