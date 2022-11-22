package guru.springframework.recipe.app.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.recipe.app.domain.Category;
import guru.springframework.recipe.app.domain.Ingredient;
import guru.springframework.recipe.app.domain.Notes;
import guru.springframework.recipe.app.domain.Recipe;
import guru.springframework.recipe.app.domain.UnitOfMeasure;
import guru.springframework.recipe.app.domain.enums.Difficulty;
import guru.springframework.recipe.app.repositories.CategoryRepository;
import guru.springframework.recipe.app.repositories.RecipeRepository;
import guru.springframework.recipe.app.repositories.UnitOfMeasureRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class BootStrapData implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		saveRecipeGuacamole();
		saveRecipeGrilledChickenTacos();
	}
	
	private void saveRecipeGuacamole() {
		Recipe recetteGuacamole = new Recipe();
		
		UnitOfMeasure teaspoon = recupererUniteDeMesure("Teaspoon");
		UnitOfMeasure tablespoon = recupererUniteDeMesure("Tablespoon");
		UnitOfMeasure pinch = recupererUniteDeMesure("Pinch");

		Ingredient avocado = new Ingredient();
		avocado.setDescription("avocado");
		avocado.setAmount(BigDecimal.valueOf(2L));
		avocado.setRecipe(recetteGuacamole);
		
		Ingredient kosherSalt = new Ingredient();
		kosherSalt.setDescription("kosher salt");
		kosherSalt.setAmount(BigDecimal.valueOf(0.25));
		kosherSalt.setUnitOfMeasure(teaspoon);
		kosherSalt.setRecipe(recetteGuacamole);
		
		Ingredient lemonJuice = new Ingredient();
		lemonJuice.setDescription("lemon juice");
		lemonJuice.setAmount(BigDecimal.valueOf(1.0));
		lemonJuice.setUnitOfMeasure(tablespoon);
		lemonJuice.setRecipe(recetteGuacamole);
		
		Ingredient redOnion = new Ingredient();
		redOnion.setDescription("red onion");
		redOnion.setAmount(BigDecimal.valueOf(2.0));
		redOnion.setUnitOfMeasure(tablespoon);
		redOnion.setRecipe(recetteGuacamole);
		
		Ingredient jalapenoChili = new Ingredient();
		jalapenoChili.setDescription("jalapeno chili");
		jalapenoChili.setAmount(BigDecimal.valueOf(2.0));
		jalapenoChili.setRecipe(recetteGuacamole);
		
		Ingredient cilantro = new Ingredient();
		cilantro.setDescription("cilantro");
		cilantro.setAmount(BigDecimal.valueOf(2.0));
		cilantro.setUnitOfMeasure(tablespoon);
		cilantro.setRecipe(recetteGuacamole);
		
		Ingredient blackPepper = new Ingredient();
		blackPepper.setDescription("black pepper");
		blackPepper.setAmount(BigDecimal.valueOf(1.0));
		blackPepper.setUnitOfMeasure(pinch);
		blackPepper.setRecipe(recetteGuacamole);
		
		Ingredient ripeTomato = new Ingredient();
		ripeTomato.setDescription("ripe tomato");
		ripeTomato.setAmount(BigDecimal.valueOf(0.5));
		ripeTomato.setRecipe(recetteGuacamole);
		
		Ingredient redRadish = new Ingredient();
		redRadish.setDescription("red radish");	
		redRadish.setAmount(BigDecimal.valueOf(1.0));
		redRadish.setRecipe(recetteGuacamole);
		
		Ingredient tortillaChip = new Ingredient();
		tortillaChip.setDescription("tortilla chip");	
		tortillaChip.setAmount(BigDecimal.valueOf(2.0));
		tortillaChip.setRecipe(recetteGuacamole);
		
		Set<Ingredient> guacamoleIngredient = new LinkedHashSet<>();
		guacamoleIngredient.add(avocado);
		guacamoleIngredient.add(kosherSalt);
		guacamoleIngredient.add(lemonJuice);
		guacamoleIngredient.add(redOnion);
		guacamoleIngredient.add(jalapenoChili);
		guacamoleIngredient.add(cilantro);
		guacamoleIngredient.add(blackPepper);
		guacamoleIngredient.add(ripeTomato);
		guacamoleIngredient.add(redRadish);
		guacamoleIngredient.add(tortillaChip);

		Category cuisineMexicaine = recupererCategorie("Mexican");
		Category cuisineAmericaine = recupererCategorie("American");

		Set<Category> categorieGuacamole = new LinkedHashSet<>();
		categorieGuacamole.add(cuisineMexicaine);
		categorieGuacamole.add(cuisineAmericaine);

		StringBuffer directionGuacamoleBuffer = new StringBuffer();
		directionGuacamoleBuffer.append("1 - Cut the avocado: ");
		directionGuacamoleBuffer.append("Cut the avocados in half. Remove the pit. ");
		directionGuacamoleBuffer.append("Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. ");
		directionGuacamoleBuffer.append("Place in a bowl. ");
		directionGuacamoleBuffer.append("");
		directionGuacamoleBuffer.append("2 - Mash the avocado flesh: ");
		directionGuacamoleBuffer.append("Using a fork, roughly mash the avocado. ");
		directionGuacamoleBuffer.append("Don't overdo it! The guacamole should be a little chunky. ");
		directionGuacamoleBuffer.append("");
		directionGuacamoleBuffer.append("3 - Add the remaining ingredients to taste: ");
		directionGuacamoleBuffer.append("Sprinkle with salt and lime (or lemon) juice. ");
		directionGuacamoleBuffer.append("The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown. ");
		directionGuacamoleBuffer.append("Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. ");
		directionGuacamoleBuffer.append("So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat. ");
		directionGuacamoleBuffer.append("Remember that much of this is done to taste because of the variability in the fresh ingredients. ");
		directionGuacamoleBuffer.append("Start with this recipe and adjust to your taste. ");
		directionGuacamoleBuffer.append("");
		directionGuacamoleBuffer.append("4 - Serve immediately: ");
		directionGuacamoleBuffer.append("If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. ");
		directionGuacamoleBuffer.append("Garnish with slices of red radish or jigama strips. ");
		directionGuacamoleBuffer.append("Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips. ");
		directionGuacamoleBuffer.append("Refrigerate leftover guacamole up to 3 days. ");

		StringBuffer noteGuacamoleBuffer = new StringBuffer();
		noteGuacamoleBuffer.append("careful handling chilis! If using, it's best to wear food-safe gloves. ");
		noteGuacamoleBuffer.append("If no gloves are available, wash your hands thoroughly after handling, ");
		noteGuacamoleBuffer.append("and do not touch your eyes or the area near your eyes for several hours afterwards.");

		Notes noteGuacamole = new Notes();
		noteGuacamole.setRecipe(recetteGuacamole);
		noteGuacamole.setRecipeNotes(noteGuacamoleBuffer.toString());
		
		recetteGuacamole.setCategories(categorieGuacamole);
		recetteGuacamole.setCookTime(10);
		recetteGuacamole.setDescription("Perfect Guacamole");
		recetteGuacamole.setDifficulty(Difficulty.EASY);
		recetteGuacamole.setDirections(directionGuacamoleBuffer.toString());
		recetteGuacamole.setIngredients(guacamoleIngredient);
		recetteGuacamole.setNotes(noteGuacamole);
		recetteGuacamole.setPrepTime(10);
		recetteGuacamole.setServing(2);
		recetteGuacamole.setSource("Simply Recipes");
		recetteGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		
		recipeRepository.save(recetteGuacamole);
	}

	private void saveRecipeGrilledChickenTacos() {
		Recipe recetteTacos = new Recipe();
		
		UnitOfMeasure teaspoon = recupererUniteDeMesure("Teaspoon");
		UnitOfMeasure tablespoon = recupererUniteDeMesure("Tablespoon");
		UnitOfMeasure cup = recupererUniteDeMesure("Cup");
		UnitOfMeasure pint = recupererUniteDeMesure("Pint");
		UnitOfMeasure pound = recupererUniteDeMesure("Pound");
		
		Ingredient anchoChili = new Ingredient();
		anchoChili.setDescription("ancho chili");
		anchoChili.setAmount(BigDecimal.valueOf(2.0));
		anchoChili.setUnitOfMeasure(tablespoon);
		anchoChili.setRecipe(recetteTacos);
		
		Ingredient driedOregano = new Ingredient();
		driedOregano.setDescription("dried oregano");
		driedOregano.setAmount(BigDecimal.valueOf(1.0));
		driedOregano.setUnitOfMeasure(teaspoon);
		driedOregano.setRecipe(recetteTacos);
		
		Ingredient driedCumin = new Ingredient();
		driedCumin.setDescription("dried cumin");
		driedCumin.setAmount(BigDecimal.valueOf(1.0));
		driedCumin.setUnitOfMeasure(teaspoon);
		driedCumin.setRecipe(recetteTacos);
		
		Ingredient sugar = new Ingredient();
		sugar.setDescription("sugar");
		sugar.setAmount(BigDecimal.valueOf(1.0));
		sugar.setUnitOfMeasure(teaspoon);
		sugar.setRecipe(recetteTacos);

		Ingredient kosherSalt = new Ingredient();
		kosherSalt.setDescription("kosher salt");
		kosherSalt.setAmount(BigDecimal.valueOf(0.50));
		kosherSalt.setUnitOfMeasure(teaspoon);
		kosherSalt.setRecipe(recetteTacos);

		Ingredient garlic = new Ingredient();
		garlic.setDescription("garlic");
		garlic.setAmount(BigDecimal.valueOf(2.0));
		garlic.setRecipe(recetteTacos);

		Ingredient gratedOrangeZest = new Ingredient();
		gratedOrangeZest.setDescription("grated orange zest");
		gratedOrangeZest.setAmount(BigDecimal.valueOf(2.0));
		gratedOrangeZest.setUnitOfMeasure(tablespoon);
		gratedOrangeZest.setRecipe(recetteTacos);
		
		Ingredient orangeJuice = new Ingredient();
		orangeJuice.setDescription("fresh-squeezed orange juice");
		orangeJuice.setAmount(BigDecimal.valueOf(2.0));
		orangeJuice.setUnitOfMeasure(tablespoon);
		orangeJuice.setRecipe(recetteTacos);
		
		Ingredient oliveOil = new Ingredient();
		oliveOil.setDescription("olive oil");
		oliveOil.setAmount(BigDecimal.valueOf(2.0));
		oliveOil.setUnitOfMeasure(tablespoon);
		oliveOil.setRecipe(recetteTacos);
		
		Ingredient bonelessChickenThigh = new Ingredient();
		bonelessChickenThigh.setDescription("boneless chicken thighs");
		bonelessChickenThigh.setAmount(BigDecimal.valueOf(1.25));
		bonelessChickenThigh.setUnitOfMeasure(pound);
		bonelessChickenThigh.setRecipe(recetteTacos);

		Ingredient cornTortilla = new Ingredient();
		cornTortilla.setDescription("corn tortillas");
		cornTortilla.setAmount(BigDecimal.valueOf(8.0));
		cornTortilla.setRecipe(recetteTacos);
		
		Ingredient babyArugula = new Ingredient();
		babyArugula.setDescription("baby arugula");
		babyArugula.setAmount(BigDecimal.valueOf(3.0));
		babyArugula.setUnitOfMeasure(cup);
		babyArugula.setRecipe(recetteTacos);
		
		Ingredient ripeAvocado = new Ingredient();
		ripeAvocado.setDescription("ripe avocados");
		ripeAvocado.setAmount(BigDecimal.valueOf(2.0));
		ripeAvocado.setRecipe(recetteTacos);
		
		Ingredient radish = new Ingredient();
		radish.setDescription("radishes");
		radish.setAmount(BigDecimal.valueOf(4.0));
		radish.setRecipe(recetteTacos);
		
		Ingredient cherryTomato = new Ingredient();
		cherryTomato.setDescription("cherry tomatoes");
		cherryTomato.setAmount(BigDecimal.valueOf(0.50));
		cherryTomato.setUnitOfMeasure(pint);
		cherryTomato.setRecipe(recetteTacos);
		
		Ingredient redOnion = new Ingredient();
		redOnion.setDescription("red onion");
		redOnion.setAmount(BigDecimal.valueOf(0.25));
		redOnion.setRecipe(recetteTacos);
		
		Ingredient cilantro = new Ingredient();
		cilantro.setDescription("cilantro");
		cilantro.setAmount(BigDecimal.valueOf(1.0));
		cilantro.setRecipe(recetteTacos);
		
		Ingredient sourCream = new Ingredient();
		sourCream.setDescription("sour cream");
		sourCream.setAmount(BigDecimal.valueOf(0.50));
		sourCream.setUnitOfMeasure(cup);
		sourCream.setRecipe(recetteTacos);
		
		Ingredient milk = new Ingredient();
		milk.setDescription("milk");
		milk.setAmount(BigDecimal.valueOf(0.25));
		milk.setUnitOfMeasure(cup);
		milk.setRecipe(recetteTacos);
		
		Ingredient lime = new Ingredient();
		lime.setDescription("lime");
		lime.setAmount(BigDecimal.valueOf(1.0));
		lime.setRecipe(recetteTacos);

		Set<Ingredient> tacosIngredient = new LinkedHashSet<>();
		tacosIngredient.add(anchoChili);
		tacosIngredient.add(driedOregano);
		tacosIngredient.add(driedCumin);
		tacosIngredient.add(sugar);
		tacosIngredient.add(kosherSalt);
		tacosIngredient.add(garlic);
		tacosIngredient.add(gratedOrangeZest);
		tacosIngredient.add(orangeJuice);
		tacosIngredient.add(oliveOil);
		tacosIngredient.add(bonelessChickenThigh);
		tacosIngredient.add(cornTortilla);
		tacosIngredient.add(babyArugula);
		tacosIngredient.add(ripeAvocado);
		tacosIngredient.add(radish);
		tacosIngredient.add(cherryTomato);
		tacosIngredient.add(redOnion);
		tacosIngredient.add(cilantro);
		tacosIngredient.add(sourCream);
		tacosIngredient.add(milk);
		tacosIngredient.add(lime);
		
		Category cuisineMexicaine = recupererCategorie("Mexican");
		Category cuisineAmericaine = recupererCategorie("American");

		Set<Category> categorieTacos = new LinkedHashSet<>();
		categorieTacos.add(cuisineMexicaine);
		categorieTacos.add(cuisineAmericaine);
		
		StringBuffer directionTacosBuffer = new StringBuffer();
		directionTacosBuffer.append("1 - Prepare the grill: ");
		directionTacosBuffer.append("Prepare either a gas or charcoal grill for medium-high, direct heat. ");
		directionTacosBuffer.append("");
		directionTacosBuffer.append("2 - Make the marinade and coat the chicken: ");
		directionTacosBuffer.append("In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. ");
		directionTacosBuffer.append("Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over. ");
		directionTacosBuffer.append("Set aside to marinate while the grill heats and you prepare the rest of the toppings. ");
		directionTacosBuffer.append("");
		directionTacosBuffer.append("3 - Grill the chicken: ");
		directionTacosBuffer.append("Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. ");
		directionTacosBuffer.append("Transfer to a plate and rest for 5 minutes. ");
		directionTacosBuffer.append("");
		directionTacosBuffer.append("4 - Thin the sour cream with milk: ");
		directionTacosBuffer.append("Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle. ");
		directionTacosBuffer.append("");
		directionTacosBuffer.append("5 - Assemble the tacos: ");
		directionTacosBuffer.append("Slice the chicken into strips. On each tortilla, place a small handful of arugula. ");
		directionTacosBuffer.append("Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. ");
		directionTacosBuffer.append("Drizzle with the thinned sour cream. Serve with lime wedges. ");
		directionTacosBuffer.append("");
		directionTacosBuffer.append("6 - Warm the tortillas: ");
		directionTacosBuffer.append("Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. ");
		directionTacosBuffer.append("As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side. ");
		directionTacosBuffer.append("Wrap warmed tortillas in a tea towel to keep them warm until serving. ");

		StringBuffer noteTacosBuffer = new StringBuffer();
		noteTacosBuffer.append("for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. ");
		noteTacosBuffer.append("If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, ");
		noteTacosBuffer.append("though the flavor won't be quite the same.");

		Notes noteTacos = new Notes();
		noteTacos.setRecipe(recetteTacos);
		noteTacos.setRecipeNotes(noteTacosBuffer.toString());
		
		recetteTacos.setCategories(categorieTacos);
		recetteTacos.setCookTime(15);
		recetteTacos.setDescription("Chicken Tacos");
		recetteTacos.setDifficulty(Difficulty.MODERATE);
		recetteTacos.setDirections(directionTacosBuffer.toString());
		recetteTacos.setIngredients(tacosIngredient);
		recetteTacos.setNotes(noteTacos);
		recetteTacos.setPrepTime(20);
		recetteTacos.setServing(4);
		recetteTacos.setSource("Simply Recipes");
		recetteTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

		recipeRepository.save(recetteTacos);
	}
	
	private UnitOfMeasure recupererUniteDeMesure(String uniteDeMesure) {
		Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription(uniteDeMesure);
		if (optionalUnitOfMeasure.isEmpty()) {
			throw new NoSuchElementException("Ingredient non present en BDD");
		}
		return optionalUnitOfMeasure.get();
	}

	private Category recupererCategorie(String categorie) {	
		Optional<Category> optionalCategory = categoryRepository.findByDescription(categorie);
		if (optionalCategory.isEmpty()) {
			throw new NoSuchElementException("Categorie non presente en BDD");
		}
		return optionalCategory.get();
	}
	
}
