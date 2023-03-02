package guru.springframework.recipe.app.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import guru.springframework.recipe.app.domain.enums.Difficulty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = {"ingredients", "categories"})
@ToString(exclude = {"ingredients", "categories"})
@Entity
@Table(name = "tb_recipe")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	
	@Lob
	private String directions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new LinkedHashSet<>();
	
	@Lob
	private Byte[] image;
	
	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;
	
	@ManyToMany
	@JoinTable(name = "tb_recipe_category", 
		joinColumns = @JoinColumn(name = "tb_recipe_id"), 
		inverseJoinColumns = @JoinColumn(name = "tb_category_id")
	)
	private Set<Category> categories = new LinkedHashSet<>();
	
	
	/* Setter Custom qui vont remplacer les setter définis par Lombok */
	public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
	}
	
	
	/* Autres methodes custom */
	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
		return this;
	}
	
}
