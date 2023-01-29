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

import guru.springframework.recipe.app.commands.RecipeCommand;
import guru.springframework.recipe.app.services.RecipeService;

public class IngredientControllerTest {

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

		/* 
		XXX GIVEN
		Explication:	Décrit le contexte initial, une scène qui s'est reproduite dans le passé
		====>	L'initialisation des objets nécessaires au test, mais aussi le mockage d'une méthode et de ce qu'elle doit retourner lors d'un appel : when(...)
		*/
		Long idRecette = 1L;
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(idRecette);
		when(recipeService.getRecipeCommandById(anyLong())).thenReturn(recipeCommand);
		
		/*
		XXX WHEN
		Explication:	Décrit un événement ou une action
		====>	Le vrai test de la méthode : statut HTTP, url testée et objet récupéré
		*/
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/1/ingredients")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recipe/ingredient/list")).
				andExpect(model().attributeExists("recette"));
		/*
		XXX THEN 
		Explication:	Décrit un résultat attendu
		====>	Vérifier que la ou les méthodess appelés ont bien été appelée N fois
		*/
		verify(recipeService, times(1)).getRecipeCommandById(anyLong());

	}
	
}
