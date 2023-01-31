package guru.springframework.recipe.app.services;

import guru.springframework.recipe.app.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand recupererParIdRecetteEtIdIngredient(Long idRecette, Long idIngredient);

	IngredientCommand sauvegarderIngredient(IngredientCommand ingredientCommand);
}
