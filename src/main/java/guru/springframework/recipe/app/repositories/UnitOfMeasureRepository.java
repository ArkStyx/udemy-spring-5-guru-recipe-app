package guru.springframework.recipe.app.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.recipe.app.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

}
