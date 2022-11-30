package guru.springframework.recipe.app.services;

import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Marker;
import org.springframework.stereotype.Service;

import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	@Override
	public Set<Recipe> getRecipes() {
		log.info("Test @Slf4j");
		log.info(Marker.ANY_MARKER, "Test @Slf4j Marker * / Comment ça marche ??????");
//		log.info("Test @Slf4j Throwable", new RuntimeException("Coucou RuntimeException pour @Slf4j"));
		
		Set<Recipe> recipeSet = new LinkedHashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

}
