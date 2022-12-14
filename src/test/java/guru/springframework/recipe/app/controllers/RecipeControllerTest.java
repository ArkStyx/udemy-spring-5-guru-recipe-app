package guru.springframework.recipe.app.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

	@Test
	void testGetRecipeById() throws Exception {
		
		Long idRecette = 1L;
		
		Recipe recette = new Recipe();
		recette.setId(idRecette);
		
		when(recipeService.getRecipeById(anyLong())).thenReturn(recette);

		String rootContext = "/recipe/show/" + idRecette;
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(rootContext);
		ResultMatcher resultMatcherStatusOk = status().isOk();
		ResultMatcher resultMatcherViewNameIndex = view().name("recettes/voirRecetteParId");
		
		mockMvc.perform(requestBuilder).andExpect(resultMatcherStatusOk).andExpect(resultMatcherViewNameIndex);
	}

}
