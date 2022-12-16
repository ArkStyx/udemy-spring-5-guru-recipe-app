package guru.springframework.recipe.app.services;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.app.commands.RecipeCommand;
import guru.springframework.recipe.app.converters.fromcommand.RecipeCommandToRecipe;
import guru.springframework.recipe.app.converters.fromdomain.RecipeToRecipeCommand;
import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new LinkedHashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe getRecipeById(Long id) {
		return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucune recette trouvée"));
	}

	@Override
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
		
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		log.debug("savedRecipe.getId : " + savedRecipe.getId());
		
		return recipeToRecipeCommand.convert(savedRecipe);
	}

}
