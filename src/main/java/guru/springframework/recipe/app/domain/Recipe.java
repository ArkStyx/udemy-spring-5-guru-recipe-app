package guru.springframework.recipe.app.domain;

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

import guru.springframework.recipe.app.domain.enums.Difficulty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_recipe")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer serving;
	private String source;
	private String url;
	private String directions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients;
	
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
	private Set<Category> categories;
}
