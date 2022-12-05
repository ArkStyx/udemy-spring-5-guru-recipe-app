package guru.springframework.recipe.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import guru.springframework.recipe.app.services.RecipeService;

class IndexControllerTest {

	IndexController indexController;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		indexController = new IndexController(recipeService);	
	}

	@Test
	void testGetIndexPage() {
		String retourModel = indexController.getIndexPage(model);
		assertEquals("index", retourModel);
		
		/*
		 * TODO ON NE PEUT FAIRE DES verify QUE SUR DES MOCKS !!!!
		 */
//		verify(indexController, Mockito.times(1)).getIndexPage(model);
		
		verify(recipeService, Mockito.times(1)).getRecipes();
		verify(model, Mockito.times(1)).addAttribute(eq("toutesLesRecettes"), anySet());
	}

}
