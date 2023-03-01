package guru.springframework.recipe.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.recipe.app.commands.RecipeCommand;
import guru.springframework.recipe.app.exceptions.NotFoundException;
import guru.springframework.recipe.app.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
public class RecipeController {

	private final RecipeService recipeService;
	
	private static final String NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF = "recette";
	
	private static final String NOM_REPERTOIRE_THYMELEAF = "recipe";
	private static final String SEPARATEUR_REPERTOIRE_ET_TEMPLATE_THYMELEAF = "/";
	
	private static final String NOM_ACTION_FORM_THYMELEAF_DANS_TEMPLATE = "formulaireRecette";
	
	private static final String REDIRECTION = "redirect:/";
	
    @GetMapping(value = "/recipe/{idRecupereDansUrl}/show")
	public String getRecipe(Model model, @PathVariable("idRecupereDansUrl") Long id) {
		model.addAttribute(NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF, recipeService.findById(id));
		return NOM_REPERTOIRE_THYMELEAF + SEPARATEUR_REPERTOIRE_ET_TEMPLATE_THYMELEAF + "show";
	}
	
    @GetMapping(value = "/recipe/new")
	public String getNewRecipeForm(Model model) {
		model.addAttribute(NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF, new RecipeCommand());
		return NOM_REPERTOIRE_THYMELEAF + SEPARATEUR_REPERTOIRE_ET_TEMPLATE_THYMELEAF + "recipeform";
	}
	
    @GetMapping(value ="/recipe/{idRecupereDansUrl}/update")
	public String updateView(Model model, @PathVariable("idRecupereDansUrl") Long id) {
		model.addAttribute(NOM_ATTRIBUT_DANS_TEMPLATE_THYMELEAF, recipeService.findCommandById(id));
		return NOM_REPERTOIRE_THYMELEAF + SEPARATEUR_REPERTOIRE_ET_TEMPLATE_THYMELEAF + "recipeform";
	}
	
	@PostMapping(value = NOM_ACTION_FORM_THYMELEAF_DANS_TEMPLATE)
	public String postNewRecipeForm(@ModelAttribute RecipeCommand command) {
		RecipeCommand recetteSauvegardee = recipeService.saveRecipeCommand(command);
		return REDIRECTION + "recipe/" + recetteSauvegardee.getId() + "/show";
	}

    @GetMapping("recipe/{idPourSuppression}/delete")
	public String deleteAction(@PathVariable("idPourSuppression") Long id) {
		log.info("Id de la recette supprimée : " + id);
		recipeService.deleteById(id);
		return REDIRECTION;
	}
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
    	log.error("Handling NotFoundException");
        log.error(exception.getMessage());
    	
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("404error");
    	modelAndView.addObject("exception", exception);
    	modelAndView.addObject("detailException", exception);
    	
    	return modelAndView;
    }
    
    
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception) {
    	log.error("Handling NumberFormatException");
        log.error(exception.getMessage());
    	
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("400error");
    	modelAndView.addObject("detailException", exception);
    	
    	return modelAndView;
    }
    
    
}
