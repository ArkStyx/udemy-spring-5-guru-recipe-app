package guru.springframework.recipe.app.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {

	private Long id;
	// TODO A SUPPRIMER ????
//	private RecipeCommand recipe;
	private String recipeNotes;
}
