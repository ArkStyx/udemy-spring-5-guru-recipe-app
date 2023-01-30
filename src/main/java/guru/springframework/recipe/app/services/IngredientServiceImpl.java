package guru.springframework.recipe.app.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.app.commands.IngredientCommand;
import guru.springframework.recipe.app.converters.fromdomain.IngredientToIngredientCommand;
import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {
	
	private final RecipeRepository recipeRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	
	@Override
	public IngredientCommand recupererParIdRecetteEtIdIngredient(Long idRecette, Long idIngredient) {
		
		Optional<Recipe> optionalRecipe = recipeRepository.findById(idRecette);
		if (!optionalRecipe.isPresent()){
			log.error("Aucune recette trouvee pour cet idRecette : " + idRecette);
		}
		
		Recipe recette = optionalRecipe.get();
		
		Optional<IngredientCommand> optionalIngredientCommand = recette.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(idIngredient))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
				.findFirst();
		
		if (!optionalIngredientCommand.isPresent()){
			log.error("Aucune ingredient trouve pour cet idIngredient : " + idIngredient);
		}

		return optionalIngredientCommand.get();
	}

}
