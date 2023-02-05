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
import java.util.Set;

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
import guru.springframework.recipe.app.commands.UnitOfMeasureCommand;
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
		
		/* Then */
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/1/ingredients")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recettes/ingredients/listeIngredients")).
				andExpect(model().attributeExists("recette"));
		
		verify(recipeService, times(1)).getRecipeCommandById(anyLong());

	}
	
	// TODO correspondance nom methode JAVA GURU - John Thompson : testShowIngredient()
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
					MockMvcRequestBuilders.get("/recipe/1/ingredient/2/show")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recettes/ingredients/montrerIngredient")).
				andExpect(model().attributeExists("ingredient"));
	}
	
	// TODO correspondance nom methode JAVA GURU - John Thompson : testUpdateIngredientForm()
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
	
	// TODO correspondance nom methode JAVA GURU - John Thompson : testSaveOrUpdate()
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
				andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));
	}
	
	// TODO correspondance nom methode JAVA GURU - John Thompson : testNewIngredientForm()
	@Test
	void testCreerNouvelIngredient() throws Exception {
		
		/* Given */
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        
        Set<UnitOfMeasureCommand> linkedHashSetUnitOfMeasureCommand = new LinkedHashSet<>();
		
        when(recipeService.getRecipeCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.recupererToutesLesUnitesDeMesure()).thenReturn(linkedHashSetUnitOfMeasureCommand);
        
		/* When */
		
		
		/* Then */
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/1/ingredient/new")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recettes/ingredients/formulaireIngredient")).
				andExpect(model().attributeExists("ingredient")).
				andExpect(model().attributeExists("listeUnitesDeMesure"));
	}
	
	
	
	
	
	
	
	// TODO correspondance nom methode JAVA GURU - John Thompson : testDeleteIngredient()
	@Test
	void testSupprimerIngredient() throws Exception {
		/* Given */
		
		
		/* When */
		
		
		/* Then */
		mockMvc.perform(
					MockMvcRequestBuilders.get("recipe/2/ingredient/3/delete")
				).
				andExpect(status().is3xxRedirection()).
				andExpect(view().name("redirect:/recipe/2/ingredients"));
	}
	
	
	
	/*
    TODO POUR REALISER L'EXERCICE
    
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    return "redirect:/recipe/" + recipeId + "/ingredients";
	
	Implementer TDD : 
	01) write your test case first
	02) write the controller action
	03) write the service.
	 */
	
	
	
	
	
}
