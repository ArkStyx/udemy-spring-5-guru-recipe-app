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
	public String getImageForm(Model model, @PathVariable Long id) {
		log.info("getImageForm - id recette recherchee : " + id);
		model.addAttribute("recipe", recipeService.findCommandById(id));
		return "recipe/imageuploadform";
	}
	
	@PostMapping("recipe/{idRecette}/image")
	public String handleImagePost(@PathVariable("idRecette") Long id, @RequestParam("imagefile") MultipartFile file) {
		log.info("handleImagePost - id recette recherchee : " + id);
		imageService.saveImageFile(id, file);
		return "redirect:/recipe/" + id + "/show";
	}
	
	
	
	
	// TODO ImageServiceImplTest - CREER METHODE saveImageFile
	// TODO ImageServiceImpl - COMPLETER METHODE saveImageFile
	
	
	
}
