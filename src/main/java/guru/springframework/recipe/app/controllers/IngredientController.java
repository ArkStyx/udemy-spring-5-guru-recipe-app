package guru.springframework.recipe.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.app.commands.IngredientCommand;
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
	
	// TODO correspondance nom methode JAVA GURU - John Thompson : listIngredients()
	@GetMapping
	@RequestMapping(value = "/recipe/{idRecetteDansUrl}/ingredients")
	public String recupererListeIngredients(Model model, @PathVariable("idRecetteDansUrl") Long id) {
		log.debug("Id de la recette pour les ingredients recherches : " + id);
		model.addAttribute("recette", recipeService.getRecipeCommandById(id));
		return "recettes/ingredients/listeIngredients";
	}
	
	// TODO correspondance nom methode JAVA GURU - John Thompson : showRecipeIngredient()
	@GetMapping
	@RequestMapping(value = "/recipe/{idRecette}/ingredients/{idIngredient}/show")
	public String afficherIngredientDansRecette(Model model, @PathVariable Long idRecette, @PathVariable Long idIngredient) {
		model.addAttribute("ingredient", ingredientService.recupererParIdRecetteEtIdIngredient(idRecette, idIngredient));
		return "recettes/ingredients/montrerIngredient";
	}

	

	// TODO correspondance nom methode JAVA GURU - John Thompson : updateRecipeIngredient()
	public String modifierIngredientDansRecette() {
		
		// TODO CODE
		
		return "recettes/ingredients/formulaireIngredient";
	}

	// TODO correspondance nom methode JAVA GURU - John Thompson : saveOrUpdate()
	public String sauvegarderOuModifierIngredientDansRecette(@ModelAttribute IngredientCommand ingredientCommand) {
		IngredientCommand ingredientSauvegarde = ingredientService.sauvegarderIngredient(ingredientCommand);
		Long idRecette = ingredientSauvegarde.getRecipeId();
		Long idIngredient = ingredientSauvegarde.getId();
		
		return "redirect:/recettes/" + idRecette + "/ingredients/" + idIngredient + "/montrerIngredient";
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
