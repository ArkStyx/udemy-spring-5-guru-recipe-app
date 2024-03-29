package guru.springframework.recipe.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import guru.springframework.recipe.app.commands.RecipeCommand;
import guru.springframework.recipe.app.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
public class RecipeController {

	private final RecipeService recipeService;
	
	private static final String NOM_REPERTOIRE_THYMELEAF = "recipe";
	private static final String SEPARATEUR_REPERTOIRE_ET_TEMPLATE_THYMELEAF = "/";
	
    private static final String RECIPE_RECIPEFORM_URL = NOM_REPERTOIRE_THYMELEAF + SEPARATEUR_REPERTOIRE_ET_TEMPLATE_THYMELEAF + "recipeform";
	
	private static final String NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF = "recipe";

	private static final String NOM_ACTION_FORM_THYMELEAF_DANS_TEMPLATE = "recipe";
	
	private static final String REDIRECTION = "redirect:/";
	
    @GetMapping(value = "/recipe/{idRecupereDansUrl}/show")
	public String showById(Model model, @PathVariable("idRecupereDansUrl") Long id) {
		model.addAttribute(NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF, recipeService.findById(id));
		return NOM_REPERTOIRE_THYMELEAF + SEPARATEUR_REPERTOIRE_ET_TEMPLATE_THYMELEAF + "show";
	}
	
    @GetMapping(value = "/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute(NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF, new RecipeCommand());
		return RECIPE_RECIPEFORM_URL;
	}
	
    @GetMapping(value ="/recipe/{idRecupereDansUrl}/update")
	public String updateRecipe(Model model, @PathVariable("idRecupereDansUrl") Long id) {
		model.addAttribute(NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF, recipeService.findCommandById(id));
		return RECIPE_RECIPEFORM_URL;
	}
	
	@PostMapping(value = NOM_ACTION_FORM_THYMELEAF_DANS_TEMPLATE)
	public String saveOrUpdate(@Validated @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return RECIPE_RECIPEFORM_URL;
		}
		
		RecipeCommand recetteSauvegardee = recipeService.saveRecipeCommand(command);
		return REDIRECTION + "recipe/" + recetteSauvegardee.getId() + "/show";
	}

    @GetMapping("recipe/{idPourSuppression}/delete")
	public String deleteById(@PathVariable("idPourSuppression") Long id) {
		log.info("Id de la recette supprimée : " + id);
		recipeService.deleteById(id);
		return REDIRECTION;
	}
    
}
