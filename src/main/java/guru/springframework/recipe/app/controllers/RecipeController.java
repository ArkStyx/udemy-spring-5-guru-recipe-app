package guru.springframework.recipe.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.app.services.RecipeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class RecipeController {

	private final RecipeService recipeService;
	
	@RequestMapping(value = "/recipe/show/{idRecupereDansUrl}")
	public String getRecipeById(Model model, @PathVariable("idRecupereDansUrl") Long id) {
		String nomAttributDansTemplateThymeleaf = "recette";
		model.addAttribute(nomAttributDansTemplateThymeleaf, recipeService.getRecipeById(id));
		
		String nomRepertoireThymeleaf = "recettes";
		String nomTemplateThymeleaf = "voirRecetteParId";
		return nomRepertoireThymeleaf + "/" + nomTemplateThymeleaf;
	}

}
