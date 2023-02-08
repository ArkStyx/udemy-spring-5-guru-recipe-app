package guru.springframework.recipe.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.recipe.app.services.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
public class ImageController {

	private final ImageService imageService;
	
	@PostMapping("recipe/{idRecette}/image")
	public String handleImagePost(@PathVariable("idRecette") Long id, @RequestParam("file") MultipartFile file) {
		log.debug("Id Recette : " + id);
		imageService.saveImageFile(id, file);
		return "/recipe/" + id + "/show";
	}
}
