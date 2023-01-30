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

}
