package guru.springframework.recipe.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.app.services.RecipeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class IndexController {

	private final RecipeService recipeService;

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model model) {
		model.addAttribute("toutesLesRecettes", recipeService.getRecipes());
		return "index";
	}
}
