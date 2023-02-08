package guru.springframework.recipe.app.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.recipe.app.services.ImageService;

public class ImageControllerTest {

	@Mock
	ImageService imageService;
	
	@InjectMocks
	ImageController imageController;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
	}
	
	@Test
	public void testHandleImagePost() throws Exception {
		String name = "file";
		String originalFileName = "testing.txt";
		String contentType = "text/plain";
		byte[] content = "Spring Framework Guru".getBytes();
		MockMultipartFile mockMultipartFile = new MockMultipartFile(name, originalFileName, contentType, content);
		
		mockMvc.perform(
					MockMvcRequestBuilders.multipart("/recipe/1/image").file(mockMultipartFile)
				).
				andExpect(status().isFound()).
				andExpect(header().string("Location", ""));
	}
		
}
