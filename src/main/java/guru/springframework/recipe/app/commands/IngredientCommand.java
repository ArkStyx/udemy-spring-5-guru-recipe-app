package guru.springframework.recipe.app.commands;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
	
	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand unitOfMeasure;
	
	private Long recipeId;
}
