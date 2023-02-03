package guru.springframework.recipe.app.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.LinkedHashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.recipe.app.commands.IngredientCommand;
import guru.springframework.recipe.app.commands.RecipeCommand;
import guru.springframework.recipe.app.services.IngredientService;
import guru.springframework.recipe.app.services.RecipeService;
import guru.springframework.recipe.app.services.UnitOfMeasureService;

public class IngredientControllerTest {

	@Mock
	IngredientService ingredientService;
	
	@Mock
	UnitOfMeasureService unitOfMeasureService;
	
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
	void testAfficherIngredientDansRecette() throws Exception {
		/* Given */
		Long idIngredient = 1L;
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(idIngredient);
		
		when(ingredientService.recupererParIdRecetteEtIdIngredient(anyLong(), anyLong())).thenReturn(ingredientCommand);
		
		/* When */
		
		/* Then */
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/1/ingredients/2/show")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recettes/ingredients/montrerIngredient")).
				andExpect(model().attributeExists("ingredient"));
	}
	
	@Test
	void testModifierIngredientDansRecette() throws Exception {
		
		/* Given */
		Long idIngredient = 1L;
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(idIngredient);
		
        when(ingredientService.recupererParIdRecetteEtIdIngredient(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.recupererToutesLesUnitesDeMesure()).thenReturn(new LinkedHashSet<>());
        
		/* When */

		/* Then */
        mockMvc.perform(
        		MockMvcRequestBuilders.get("/recipe/1/ingredient/2/update")
    		).
    		andExpect(status().isOk()).
    		andExpect(view().name("recettes/ingredients/formulaireIngredient")).
    		andExpect(model().attributeExists("ingredient")).
    		andExpect(model().attributeExists("listeUnitesDeMesure"));
	}
	
	@Test
	void testSauvegarderOuModifierIngredientDansRecette() throws Exception {

		/* Given */
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(3L);
		ingredientCommand.setRecipeId(2L);
		
		when(ingredientService.sauvegarderIngredient(any())).thenReturn(ingredientCommand);
		
		/* When */

		/* Then */
		mockMvc.perform(
					MockMvcRequestBuilders.post("/recipe/1/ingredient").
					contentType(MediaType.APPLICATION_FORM_URLENCODED).
	                param("id", "").
	                param("description", "some string")
				).
				andExpect(status().is3xxRedirection()).
				andExpect(view().name("redirect:/recipe/2/ingredients/3/show"));
	}
	
	
	
	
}
