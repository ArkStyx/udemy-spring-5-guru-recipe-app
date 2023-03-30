package guru.springframework.recipe.app.bootstrap;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.recipe.app.domain.Category;
import guru.springframework.recipe.app.domain.UnitOfMeasure;
import guru.springframework.recipe.app.repositories.CategoryRepository;
import guru.springframework.recipe.app.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
@Profile({"dev", "prod"})
public class MySQLRecipeBootStrapData implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
        if (categoryRepository.count() == 0L) {
            log.debug("Loading Categories");
            chargerCategories();
        }

        if (unitOfMeasureRepository.count() == 0L) {
            log.debug("Loading UOMs");
            chargerUnitesDeMesure();
        }
	}
	
	 private void chargerCategories(){
        Category cuisineAmericaine = new Category();
        cuisineAmericaine.setDescription("American");
        categoryRepository.save(cuisineAmericaine);

        Category cuisineItalienne = new Category();
        cuisineItalienne.setDescription("Italian");
        categoryRepository.save(cuisineItalienne);

        Category cuisineMexicaine = new Category();
        cuisineMexicaine.setDescription("Mexican");
        categoryRepository.save(cuisineMexicaine);

        Category fastFood = new Category();
        fastFood.setDescription("Fast Food");
        categoryRepository.save(fastFood);
    }

    private void chargerUnitesDeMesure(){
        UnitOfMeasure uniteDeMesure01 = new UnitOfMeasure();
        uniteDeMesure01.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uniteDeMesure01);

        UnitOfMeasure uniteDeMesure02 = new UnitOfMeasure();
        uniteDeMesure02.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uniteDeMesure02);

        UnitOfMeasure uniteDeMesure03 = new UnitOfMeasure();
        uniteDeMesure03.setDescription("Cup");
        unitOfMeasureRepository.save(uniteDeMesure03);

        UnitOfMeasure uniteDeMesure04 = new UnitOfMeasure();
        uniteDeMesure04.setDescription("Pinch");
        unitOfMeasureRepository.save(uniteDeMesure04);

        UnitOfMeasure uniteDeMesure05 = new UnitOfMeasure();
        uniteDeMesure05.setDescription("Ounce");
        unitOfMeasureRepository.save(uniteDeMesure05);

        UnitOfMeasure uniteDeMesure06 = new UnitOfMeasure();
        uniteDeMesure06.setDescription("Each");
        unitOfMeasureRepository.save(uniteDeMesure06);

        UnitOfMeasure uniteDeMesure07 = new UnitOfMeasure();
        uniteDeMesure07.setDescription("Pint");
        unitOfMeasureRepository.save(uniteDeMesure07);

        UnitOfMeasure uniteDeMesure08 = new UnitOfMeasure();
        uniteDeMesure08.setDescription("Dash");
        unitOfMeasureRepository.save(uniteDeMesure08);
    }
	
}
