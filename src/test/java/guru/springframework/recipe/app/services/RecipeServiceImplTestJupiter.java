package guru.springframework.recipe.app.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import guru.springframework.recipe.app.converters.fromcommand.RecipeCommandToRecipe;
import guru.springframework.recipe.app.converters.fromdomain.RecipeToRecipeCommand;
import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.repositories.RecipeRepository;

class RecipeServiceImplTestJupiter {

	RecipeServiceImpl recipeServiceImpl;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}
	
	@Test
	void testGetRecipes() {
		Recipe recetteGuacamole = new Recipe();
		recetteGuacamole.setDescription("Guacamole maison");
		
		Recipe recetteTacos = new Recipe();
		recetteTacos.setDescription("Tacos maison");

		Set<Recipe> fausseListeDeRecettes = new LinkedHashSet<Recipe>();
		fausseListeDeRecettes.add(recetteGuacamole);
		fausseListeDeRecettes.add(recetteTacos);

		when(recipeRepository.findAll()).thenReturn(fausseListeDeRecettes);
		
		Set<Recipe> listeDeRecettes = recipeServiceImpl.getRecipes();
		assertEquals(2, listeDeRecettes.size());
		verify(recipeRepository, Mockito.times(1)).findAll();
	}

	@Test
	void testGetRecipeById() {
		Recipe recette = new Recipe();
		recette.setId(1L);
		
		Optional<Recipe> optionalRecette = Optional.of(recette);
		when(recipeRepository.findById(anyLong())).thenReturn(optionalRecette);
		
		assertNotNull("Null Recipe Returned", recipeServiceImpl.getRecipeById(1L));
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}
}
