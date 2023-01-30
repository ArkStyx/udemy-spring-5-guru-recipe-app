package guru.springframework.recipe.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.recipe.app.commands.IngredientCommand;
import guru.springframework.recipe.app.converters.fromdomain.IngredientToIngredientCommand;
import guru.springframework.recipe.app.converters.fromdomain.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipe.app.domain.Ingredient;
import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.repositories.RecipeRepository;


public class IngredientServiceImplTestJupiter {

	IngredientService ingredientService;
	
	IngredientToIngredientCommand ingredientToIngredientCommand;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@BeforeEach
	protected void setUp() throws Exception {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
		MockitoAnnotations.openMocks(this);
		ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);
	}

	@Test
	void testRecupererParIdRecetteEtIdIngredient() {

		/* Given */
		Long idRecette = 1L;
		Long idIngredient = 3L;
		
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        
		Recipe recette = new Recipe();
		recette.setId(idRecette);
        recette.addIngredient(ingredient1);
        recette.addIngredient(ingredient2);
        recette.addIngredient(ingredient3);

		Optional<Recipe> optionalRecipe = Optional.of(recette);
		when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
		
		/* When */
		IngredientCommand ingredientCommand = ingredientService.recupererParIdRecetteEtIdIngredient(idRecette, idIngredient);
		
		/* Then */
		assertNotNull(ingredientCommand);
		assertEquals(idIngredient, ingredientCommand.getId());
		assertEquals(idRecette, ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
	}
	
}
