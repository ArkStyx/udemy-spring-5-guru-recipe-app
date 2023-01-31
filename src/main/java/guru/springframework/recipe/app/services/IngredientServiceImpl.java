package guru.springframework.recipe.app.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.recipe.app.commands.IngredientCommand;
import guru.springframework.recipe.app.converters.fromcommand.IngredientCommandToIngredient;
import guru.springframework.recipe.app.converters.fromdomain.IngredientToIngredientCommand;
import guru.springframework.recipe.app.domain.Ingredient;
import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.repositories.RecipeRepository;
import guru.springframework.recipe.app.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {
	
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	
	@Override
	public IngredientCommand recupererParIdRecetteEtIdIngredient(Long idRecette, Long idIngredient) {
		
		Optional<Recipe> optionalRecipe = recipeRepository.findById(idRecette);
		if (!optionalRecipe.isPresent()) {
			// TODO IMPLEMENTER ERREUR
			log.error("Aucune recette trouvee pour cet idRecette : " + idRecette);
		}
		
		Recipe recette = optionalRecipe.get();
		
		Optional<IngredientCommand> optionalIngredientCommand = recette.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(idIngredient))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
				.findFirst();
		
		if (!optionalIngredientCommand.isPresent()) {
			// TODO IMPLEMENTER ERREUR
			log.error("Aucune ingredient trouve pour cet idIngredient : " + idIngredient);
		}

		return optionalIngredientCommand.get();
	}
	
	@Transactional
	@Override
	public IngredientCommand sauvegarderIngredient(IngredientCommand ingredientCommand) {

		Optional<Recipe> optionalRecipe = recipeRepository.findById(ingredientCommand.getRecipeId());
		
		if (optionalRecipe.isPresent()) {
			// TODO IMPLEMENTER ERREUR
			log.error("Aucune recette trouvee pour cet idRecette : " + idRecette);
            return new IngredientCommand();
		}
		else {
			Recipe recetteTrouvee = optionalRecipe.get();

			Optional<Ingredient> optionalIngredient = 
					recetteTrouvee.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId())).findFirst();
			
			if (optionalIngredient.isPresent()) {
				Ingredient ingredientTrouve = optionalIngredient.get();
				ingredientTrouve.setDescription(ingredientCommand.getDescription());
				ingredientTrouve.setAmount(ingredientCommand.getAmount());

				/* FIXME ON VERIFIE QUE L'UNITE DE MESURE EXISTE BIEN */
				ingredientTrouve.setUnitOfMeasure(unitOfMeasureRepository
						.findById(ingredientCommand.getUnitOfMeasure().getId())
						.orElseThrow(() -> new RuntimeException("Unite de mesure non trouvee")));
			}
			else {
				recetteTrouvee.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
			}
			
			Recipe recetteSauvegardee = recipeRepository.save(recetteTrouvee);
			
			Optional<Ingredient> optionalIngredientSauvegarde = 
					recetteSauvegardee.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId())).findFirst();
			
			Ingredient ingredientSauvegarde = optionalIngredientSauvegarde.get();

			return ingredientToIngredientCommand.convert(ingredientSauvegarde);
		}
	}

}
