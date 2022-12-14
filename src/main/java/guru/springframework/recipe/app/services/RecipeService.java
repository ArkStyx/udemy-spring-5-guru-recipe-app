package guru.springframework.recipe.app.services;

import java.util.Set;

import guru.springframework.recipe.app.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();
	
	Recipe getRecipeById(Long id);
}
