package guru.springframework.recipe.app.services;

import java.util.LinkedHashSet;
import java.util.Set;

import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.repositories.RecipeRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new LinkedHashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

}
