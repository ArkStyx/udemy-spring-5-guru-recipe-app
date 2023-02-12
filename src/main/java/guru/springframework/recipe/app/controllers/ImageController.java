package guru.springframework.recipe.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.recipe.app.services.ImageService;
import guru.springframework.recipe.app.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
public class ImageController {

	private final ImageService imageService;
	private final RecipeService recipeService;
	
	@GetMapping(value = "recipe/{id}/image")
	public String getImageForm(Model model, @PathVariable String id) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/imageuploadform";
	}
	
	@PostMapping("recipe/{idRecette}/image")
	public String handleImagePost(@PathVariable("idRecette") Long id, @RequestParam("file") MultipartFile file) {
		log.debug("Id Recette : " + id);
		imageService.saveImageFile(id, file);
		return "/recipe/" + id + "/show";
	}
	
	
	
	// TODO CREER imageuploadform.html
	// TODO MODIFIER show.html - AJOUTER BOUTON QUI POINTE VERS imageuploadform.html + AJOUTER LES IMAGES ISSUES DE resources/static/images
	
}
