package guru.springframework.recipe.app.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = "recipe")
@ToString(exclude = "recipe")
@Entity
@Table(name = "tb_ingredient")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private BigDecimal amount;
	
	@ManyToOne
	private Recipe recipe;
	
	/*
	 * TODO Par defaut le FetchType de @OneToOne est EAGER. Ici c'est juste pour montrer comment modifier le comportement
	 */
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unitOfMeasure;
}
