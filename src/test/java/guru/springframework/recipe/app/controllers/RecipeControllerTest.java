package guru.springframework.recipe.app.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.recipe.app.commands.RecipeCommand;
import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.exceptions.NotFoundException;
import guru.springframework.recipe.app.services.RecipeService;

class RecipeControllerTest {

	@Mock
	RecipeService recipeService;
	
	@InjectMocks
	RecipeController recipeController;

	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).
				setControllerAdvice(new ControllerExceptionHandler()).
				build();
	}

	@Test
	void getRecipe() throws Exception {
		
		Long idRecette = 1L;
		
		Recipe recette = new Recipe();
		recette.setId(idRecette);
		
		when(recipeService.findById(anyLong())).thenReturn(recette);

		String rootContext = "/recipe/" + idRecette + "/show/";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(rootContext);
		ResultMatcher resultMatcherStatusOk = status().isOk();
		ResultMatcher resultMatcherViewNameIndex = view().name("recipe/show");
		ResultMatcher resultMatcherModelAttributeExists = model().attributeExists("recette");
		
		mockMvc.perform(requestBuilder)
				.andExpect(resultMatcherStatusOk)
				.andExpect(resultMatcherViewNameIndex)
				.andExpect(resultMatcherModelAttributeExists);
	}
	
	@Test
	void getNewRecipeForm() throws Exception {
		
		/* Given */

		/* When */

		/* Then */
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/new")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recipe/recipeform")).
				andExpect(model().attributeExists("recette"));
		
	}

	@Test
	void postNewRecipeForm() throws Exception {
		
		/* Given */
		Long idRecette = 2L;
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(idRecette);

		/* When */
		when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
		
		/* Then */
		mockMvc.perform(
				MockMvcRequestBuilders.post("/formulaireRecette")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "some string")
			).
			andExpect(status().is3xxRedirection()).
			andExpect(view().name("redirect:/recipe/2/show"));
	}
	
	@Test
	void updateView() throws Exception {
		
		/* Given */
		Long idRecette = 2L;
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(idRecette);
		
		/* When */
		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
		
		/* Then */
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/1/update/")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recipe/recipeform")).
				andExpect(model().attributeExists("recette"));
	}
	
	@Test
	void deleteAction() throws Exception {
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/1/delete")
				).
				andExpect(status().is3xxRedirection()).
				andExpect(view().name("redirect:/"));
		
		verify(recipeService, times(1)).deleteById(anyLong());
	}
	
	@Test
	void handleNotFound() throws Exception {
		when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
		
		mockMvc.perform(
					get("/recipe/1/show/")
				).
				andExpect(status().isNotFound()).
				andExpect(view().name("404error"));
	}
	
	@Test
	void handleNumberFormatException() throws Exception {

		mockMvc.perform(
					get("/recipe/azerty/show/")
				).
				andExpect(status().isBadRequest()).
				andExpect(view().name("400error"));
	}
	
}
