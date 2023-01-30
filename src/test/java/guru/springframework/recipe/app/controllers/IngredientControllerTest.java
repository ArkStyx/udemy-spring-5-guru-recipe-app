package guru.springframework.recipe.app.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.recipe.app.commands.IngredientCommand;
import guru.springframework.recipe.app.commands.RecipeCommand;
import guru.springframework.recipe.app.services.IngredientService;
import guru.springframework.recipe.app.services.RecipeService;

public class IngredientControllerTest {

	@Mock
	IngredientService ingredientService;
	
	@Mock
	RecipeService recipeService;
	
	@InjectMocks
	IngredientController ingredientController;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}
	
	// TODO correspondance nom methode JAVA GURU - John Thompson : testListIngredients()
	@Test
	void testRecupererListeIngredients() throws Exception {

		/* Given */
		Long idRecette = 1L;
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(idRecette);
		when(recipeService.getRecipeCommandById(anyLong())).thenReturn(recipeCommand);
		
		/* When */
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/1/ingredients")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recettes/ingredients/listeIngredients")).
				andExpect(model().attributeExists("recette"));
		
		/* Then */
		verify(recipeService, times(1)).getRecipeCommandById(anyLong());

	}
	
	@Test
	void testRecupererParIdRecetteEtIdIngredient() throws Exception {
		/* Given */
		Long idIngredient = 1L;
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(idIngredient);
		
		/* When */
		when(ingredientService.recupererParIdRecetteEtIdIngredient(anyLong(), anyLong())).thenReturn(ingredientCommand);
		
		/* Then */
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/1/ingredients/2/show")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recettes/ingredients/montrerIngredient")).
				andExpect(model().attributeExists("ingredient"));
	}
	
}
