package guru.springframework.recipe.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.repositories.RecipeRepository;

class RecipeServiceImplTestJupiter {

	RecipeServiceImpl recipeServiceImpl;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
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

}
