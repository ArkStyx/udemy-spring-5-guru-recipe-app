package guru.springframework.recipe.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.app.services.IngredientService;
import guru.springframework.recipe.app.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
public class IngredientController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	
	@GetMapping
	@RequestMapping(value = "/recipe/{idRecetteDansUrl}/ingredients")
	public String recupererListeIngredients(Model model, @PathVariable("idRecetteDansUrl") Long id) {
		log.debug("Id de la recette pour les ingredients recherches : " + id);
		model.addAttribute("recette", recipeService.getRecipeCommandById(id));
		return "recettes/ingredients/listeIngredients";
	}
	
	@GetMapping
	@RequestMapping(value = "/recipe/{idRecette}/ingredients/{idIngredient}/show")
	public String afficherIngredientDansRecette(Model model, @PathVariable Long idRecette, @PathVariable Long idIngredient) {
		model.addAttribute("ingredient", ingredientService.recupererParIdRecetteEtIdIngredient(idRecette, idIngredient));
		return "recettes/ingredients/montrerIngredient";
	}

	
	
	
	public String modifierIngredientDansRecette() {
		
		// TODO CODE
		
		return "recettes/ingredients/formulaireIngredient";
	}
	public String sauvegarderOuModifierIngredientDansRecette() {
		
		// TODO CODE
		

		return "redirect:/recettes/" + idRecette + "/ingredients/" + recetteIngredient + "/montrerIngredient";
	}
	
	
	/*
	TODO templates/recipe/ingredient/ingredientform.html			====>	FIXME TODO recettes/ingredients/formulaireIngredient
	TODO templates/recipe/ingredient/show.html						====>	FIXME TODO recettes/ingredients/montrerIngredient.html
	TODO UnitOfMeasureService
	TODO UnitOfMeasureServiceImpl
	TODO MODIFIER IngredientController
	TODO MODIFIER IngredientServiceImpl
	
	TODO UnitOfMeasureServiceImplTest
	TODO MODIFIER IngredientServiceImplTest
	TODO MODIFIER IngredientControllerTest
	
	*/
	
	
	
}
