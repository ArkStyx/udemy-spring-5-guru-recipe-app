package guru.springframework.recipe.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.recipe.app.commands.RecipeCommand;
import guru.springframework.recipe.app.services.RecipeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class RecipeController {

	private final RecipeService recipeService;
	
	private static final String NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF = "recette";
	private static final String NOM_REPERTOIRE_THYMELEAF = "recettes";
	private static final String SEPARATEUR_REPERTOIRE_ET_TEMPLATE_THYMELEAF = "/";
	private static final String NOM_ACTION_FORM_THYMELEAF_DANS_TEMPLATE = "formulaireRecette";
	
	@RequestMapping(value = "/recipe/show/{idRecupereDansUrl}")
	public String getRecipeById(Model model, @PathVariable("idRecupereDansUrl") Long id) {
		model.addAttribute(NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF, recipeService.getRecipeById(id));
		return NOM_REPERTOIRE_THYMELEAF + SEPARATEUR_REPERTOIRE_ET_TEMPLATE_THYMELEAF + "voirRecetteParId";
	}
	
	@RequestMapping(value = "/recipe/new")
	public String createRecipe(Model model) {
		model.addAttribute(NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF, new RecipeCommand());
		return NOM_REPERTOIRE_THYMELEAF + SEPARATEUR_REPERTOIRE_ET_TEMPLATE_THYMELEAF + "formulaireNouvelleRecette";
	}
	
	/*
	TODO ANCIENNE FACON DE FAIRE UNE METHODE POST
	@RequestMapping(name = "recipe", method = RequestMethod.POST)
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand recetteSauvegardee = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/show/" + recetteSauvegardee.getId();
	}
	*/
	
	@PostMapping(NOM_ACTION_FORM_THYMELEAF_DANS_TEMPLATE)
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand recetteSauvegardee = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/show/" + recetteSauvegardee.getId();
	}
	
}
