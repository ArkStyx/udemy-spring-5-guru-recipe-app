package guru.springframework.recipe.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
import guru.springframework.recipe.app.converters.fromcommand.IngredientCommandToIngredient;
import guru.springframework.recipe.app.converters.fromcommand.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.recipe.app.converters.fromdomain.IngredientToIngredientCommand;
import guru.springframework.recipe.app.converters.fromdomain.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipe.app.domain.Ingredient;
import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.repositories.RecipeRepository;
import guru.springframework.recipe.app.repositories.UnitOfMeasureRepository;


public class IngredientServiceImplTestJupiter {

	IngredientService ingredientService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	IngredientToIngredientCommand ingredientToIngredientCommand;
	
	IngredientCommandToIngredient ingredientCommandToIngredient;
	
	@BeforeEach
	protected void setUp() throws Exception {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
		this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
		MockitoAnnotations.openMocks(this);
		ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
	}

	@Test
	void testRecupererParIdRecetteEtIdIngredient() {

		/* Given */
		Long idRecette = 1L;
		Long idIngredient = 3L;
		
        Ingredient ingredient01 = new Ingredient();
        ingredient01.setId(1L);

        Ingredient ingredient02 = new Ingredient();
        ingredient02.setId(2L);

        Ingredient ingredient03 = new Ingredient();
        ingredient03.setId(3L);
        
		Recipe recette = new Recipe();
		recette.setId(idRecette);
        recette.addIngredient(ingredient01);
        recette.addIngredient(ingredient02);
        recette.addIngredient(ingredient03);

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
	
	/*
	 * correspondance nom methode JAVA GURU - John Thompson : testSaveRecipeCommand()
	 */
    @Test
    public void testSauvegarderIngredient() throws Exception {
    	
		/* Given */
    	Long idRecette = 2L;
    	Long idIngredient = 3L;
    	
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(idIngredient);
        ingredientCommand.setRecipeId(idRecette);

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(idRecette);
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(idIngredient);

        when(recipeRepository.save(any())).thenReturn(savedRecipe);
    	
        Optional<Recipe> recipeOptional = Optional.of(new Recipe());
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		/* When */
        IngredientCommand ingredientCommandSauvegardee = ingredientService.sauvegarderIngredient(ingredientCommand);

		/* Then */
        assertNotNull(ingredientCommandSauvegardee);
        assertEquals(idIngredient, ingredientCommandSauvegardee.getId());
        assertEquals(idRecette, ingredientCommandSauvegardee.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
    
	/*
	 * correspondance nom methode JAVA GURU - John Thompson : testDeleteById()
	 */
	@Test
    public void testSupprimerIngredientDansRecetteParId() throws Exception {
    	
		Long idRecette = 1L;
		Long idIngredient = 3L;
		
		Recipe recette = new Recipe();
		recette.setId(idRecette);
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(idIngredient);
		ingredient.setRecipe(recette);
		
		recette.addIngredient(ingredient);
		Optional<Recipe> optionalRecipe = Optional.of(recette);
		
		when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
		
		/* When */
		ingredientService.supprimerIngredientDansRecetteParId(idRecette, idIngredient);

		/* Then */
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
    
}
