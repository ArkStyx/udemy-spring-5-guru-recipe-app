package guru.springframework.recipe.app.commands;

import java.util.LinkedHashSet;
import java.util.Set;

import guru.springframework.recipe.app.domain.Category;
import guru.springframework.recipe.app.domain.Ingredient;
import guru.springframework.recipe.app.domain.enums.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
	
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer serving;
	private String source;
	private String url;
	private String directions;
	private Set<IngredientCommand> ingredients = new LinkedHashSet<>();
	private Byte[] image;
	private Difficulty difficulty;
	private NotesCommand notes;
	private Set<CategoryCommand> categories = new LinkedHashSet<>();
}
