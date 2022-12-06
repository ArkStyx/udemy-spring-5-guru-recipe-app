package guru.springframework.recipe.app.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.recipe.app.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryIntegrationTest {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindByDescription() throws Exception {
		String uniteDeMesureCherchee = "Teaspoon";
		Optional<UnitOfMeasure> optionalTeaspoon = unitOfMeasureRepository.findByDescription(uniteDeMesureCherchee);
		assertEquals(uniteDeMesureCherchee, optionalTeaspoon.get().getDescription());
	}

}
