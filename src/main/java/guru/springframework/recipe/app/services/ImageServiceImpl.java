package guru.springframework.recipe.app.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

	@Override
	public void saveImageFile(Long recipeId, MultipartFile file) {
		log.debug("Reception d'un fichier : " + file.getName());
	}

}
