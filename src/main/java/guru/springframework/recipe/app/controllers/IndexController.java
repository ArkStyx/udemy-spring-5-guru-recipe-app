package guru.springframework.recipe.app.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.app.domain.Category;
import guru.springframework.recipe.app.domain.UnitOfMeasure;
import guru.springframework.recipe.app.repositories.CategoryRepository;
import guru.springframework.recipe.app.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {

	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	
	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage() {
		
		String chosenCategory = "American";
		String chosenUnitOfMeasure = "Teaspoon";
		
		Optional<Category> categoryOptional = categoryRepository.findByDescription(chosenCategory);
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(chosenUnitOfMeasure);
		
		System.out.println("Category ID for " + chosenCategory + " is : " + categoryOptional.get().getId());
		System.out.println("UnitOfMeasure ID for " + chosenUnitOfMeasure + " is : " + unitOfMeasureOptional.get().getId());
		
		return "index";
	}
}
