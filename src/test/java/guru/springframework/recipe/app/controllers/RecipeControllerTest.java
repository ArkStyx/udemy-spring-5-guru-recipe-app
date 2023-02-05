package guru.springframework.recipe.app.controllers;

import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.recipe.app.commands.RecipeCommand;
import guru.springframework.recipe.app.domain.Recipe;
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
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
	}

	// XXX correspondance nom methode JAVA GURU - John Thompson : testGetRecipe()
	@Test
	void testGetRecipeById() throws Exception {
		
		Long idRecette = 1L;
		
		Recipe recette = new Recipe();
		recette.setId(idRecette);
		
		when(recipeService.getRecipeById(anyLong())).thenReturn(recette);

		String rootContext = "/recipe/" + idRecette + "/show/";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(rootContext);
		ResultMatcher resultMatcherStatusOk = status().isOk();
		ResultMatcher resultMatcherViewNameIndex = view().name("recettes/voirRecetteParId");
		ResultMatcher resultMatcherModelAttributeExists = model().attributeExists("recette");
		
		mockMvc.perform(requestBuilder)
				.andExpect(resultMatcherStatusOk)
				.andExpect(resultMatcherViewNameIndex)
				.andExpect(resultMatcherModelAttributeExists);
	}
	
	// XXX correspondance nom methode JAVA GURU - John Thompson : testGetNewRecipeForm()
	@Test
	void testCreateRecipe() throws Exception {
		
		/* Given */

		/* When */

		/* Then */
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/new")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recettes/formulaireNouvelleRecette")).
				andExpect(model().attributeExists("recette"));
		
	}

	// XXX correspondance nom methode JAVA GURU - John Thompson : testPostNewRecipeForm()
	@Test
	void testSaveOrUpdate() throws Exception {
		
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
	
	// XXX correspondance nom methode JAVA GURU - John Thompson : testGetUpdateView()
	@Test
	void testUpdateRecipe() throws Exception {
		
		/* Given */
		Long idRecette = 2L;
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(idRecette);
		
		/* When */
		when(recipeService.getRecipeCommandById(anyLong())).thenReturn(recipeCommand);
		
		/* Then */
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/1/update/")
				).
				andExpect(status().isOk()).
				andExpect(view().name("recettes/formulaireNouvelleRecette")).
				andExpect(model().attributeExists("recette"));
	}
	
	// XXX correspondance nom methode JAVA GURU - John Thompson : testDeleteAction()
	void testDeleteById() throws Exception {
		mockMvc.perform(
					MockMvcRequestBuilders.get("/recipe/1/delete")
				).
				andExpect(status().is3xxRedirection()).
				andExpect(view().name("redirect:/"));
		
		verify(recipeService, times(1)).deleteById(anyLong());
	}
	
}
