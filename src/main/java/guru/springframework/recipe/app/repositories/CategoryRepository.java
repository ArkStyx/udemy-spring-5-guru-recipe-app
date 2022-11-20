package guru.springframework.recipe.app.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.recipe.app.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
