package guru.springframework.recipe.app.converters.fromdomain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.recipe.app.commands.IngredientCommand;
import guru.springframework.recipe.app.domain.Ingredient;
import guru.springframework.recipe.app.domain.Recipe;
import lombok.AllArgsConstructor;
import lombok.Synchronized;

@AllArgsConstructor
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		if (source == null) {
			return null;
		}
		
		Recipe recette = source.getRecipe();
		
		IngredientCommand destination = new IngredientCommand();
		destination.setId(source.getId());
        if (recette != null) {
        	destination.setRecipeId(recette.getId());
        }
		destination.setDescription(source.getDescription());
		destination.setAmount(source.getAmount());
		destination.setUnitOfMeasure(unitOfMeasureToUnitOfMeasureCommand.convert(source.getUnitOfMeasure()));
		return destination;
	}

}
