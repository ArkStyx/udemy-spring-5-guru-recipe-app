package guru.springframework.recipe.app.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.app.commands.IngredientCommand;
import guru.springframework.recipe.app.commands.RecipeCommand;
import guru.springframework.recipe.app.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.app.services.IngredientService;
import guru.springframework.recipe.app.services.RecipeService;
import guru.springframework.recipe.app.services.UnitOfMeasureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
public class IngredientController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService unitOfMeasureService;
	
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
	@RequestMapping(value = "/recipe/{idRecette}/ingredient/{idIngredient}/show")
	public String afficherIngredientDansRecette(Model model, @PathVariable Long idRecette, @PathVariable Long idIngredient) {
		model.addAttribute("ingredient", ingredientService.recupererParIdRecetteEtIdIngredient(idRecette, idIngredient));
		return "recettes/ingredients/montrerIngredient";
	}
	
	// TODO correspondance nom methode JAVA GURU - John Thompson : updateRecipeIngredient()
    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
	public String modifierIngredientDansRecette(Model model, @PathVariable("recipeId") Long idRecette, @PathVariable("id") Long idIngredient) {
    	model.addAttribute("ingredient", ingredientService.recupererParIdRecetteEtIdIngredient(idRecette, idIngredient));
    	model.addAttribute("listeUnitesDeMesure", unitOfMeasureService.recupererToutesLesUnitesDeMesure());
		return "recettes/ingredients/formulaireIngredient";
	}

	// TODO correspondance nom methode JAVA GURU - John Thompson : saveOrUpdate()
	@PostMapping("recipe/{recipeId}/ingredient")
	public String sauvegarderOuModifierIngredientDansRecette(@ModelAttribute IngredientCommand ingredientCommand) {
		IngredientCommand ingredientSauvegarde = ingredientService.sauvegarderIngredient(ingredientCommand);
		Long idRecette = ingredientSauvegarde.getRecipeId();
		Long idIngredient = ingredientSauvegarde.getId();
		
		return "redirect:/recipe/" + idRecette + "/ingredient/" + idIngredient + "/show";
	}
	
	// TODO correspondance nom methode JAVA GURU - John Thompson : newIngredient()
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String creerNouvelIngredient(Model model, @PathVariable("recipeId") Long idRecette) {
    	
    	IngredientCommand ingredientCommand = new IngredientCommand();
    	ingredientCommand.setRecipeId(idRecette);
    	ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
    	
    	RecipeCommand recetteTrouvee = recipeService.getRecipeCommandById(idRecette);
    	recetteTrouvee.getIngredients().add(ingredientCommand);
    	
    	Set<UnitOfMeasureCommand> linkedHashSetUnitOfMeasureCommand = unitOfMeasureService.recupererToutesLesUnitesDeMesure();

        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("listeUnitesDeMesure", linkedHashSetUnitOfMeasureCommand);
        
        return "recettes/ingredients/formulaireIngredient";
    }
    
    
    
    
    
	// TODO correspondance nom methode JAVA GURU - John Thompson : deleteIngredient()
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String supprimerIngredient(@PathVariable("recipeId") Long idRecette, @PathVariable("id") Long idIngredient) {
    	
    	
        log.debug("Suppression ingredient dans recette - idRecette : " + idRecette + " / idIngredient : " + idIngredient);
        
        // TODO CODE
//    	ingredientService.supprimerIngredientDansRecetteParId(idRecette, idIngredient);
    	
    	
    	return "redirect:/recipe/" + idRecette + "/ingredients";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

}
