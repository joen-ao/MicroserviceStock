package bootcampragma.emazon.infrastructure.input.rest;

import bootcampragma.emazon.aplication.dto.request.ArticleRequest;
import bootcampragma.emazon.aplication.handler.interfaces.IArticleHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = ArticleRestController.class)
@Import(bootcampragma.emazon.infrastructure.configuration.TestConfig.class)
class ArticleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArticleHandler articleHandler;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController(articleHandler))
                .build();
    }

    @Test
    void saveArticle_ShouldReturnBadRequest_WhenInvalidInput() throws Exception {
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setName("");
        articleRequest.setDescription("Description of the new article");

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(articleRequest)));

        result.andExpect(status().isBadRequest());
    }


    @Test
    void saveArticle_ShouldReturnUnsupportedMediaType_WhenInvalidContentType() throws Exception {
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setName("New Article");
        articleRequest.setDescription("Description of the new article");

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/article")
                .contentType(MediaType.TEXT_PLAIN)
                .content(objectMapper.writeValueAsString(articleRequest)));

        result.andExpect(status().isUnsupportedMediaType());

        verify(articleHandler, never()).saveArticle(any(ArticleRequest.class));
    }


    @Test
    void saveArticle_ShouldReturnBadRequest_WhenNameIsNull() throws Exception {
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setName(null);
        articleRequest.setDescription("Description of the article with null name");

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(articleRequest)));

        result.andExpect(status().isBadRequest());

        verify(articleHandler, never()).saveArticle(any(ArticleRequest.class));
    }

    @Test
    void saveArticle_ShouldReturnBadRequest_WhenDescriptionIsEmpty() throws Exception {
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setName("Article with empty description");
        articleRequest.setDescription("");

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(articleRequest)));

        result.andExpect(status().isBadRequest());

        verify(articleHandler, never()).saveArticle(any(ArticleRequest.class));
    }

    @Test
    void saveArticle_ShouldReturnBadRequest_WhenPriceIsEmpty() throws Exception {
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setName("Article with empty price");
        articleRequest.setDescription("Description of the article with empty price");
        articleRequest.setPrice(null); // Assuming price is a field in ArticleRequest

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(articleRequest)));

        result.andExpect(status().isBadRequest());

        verify(articleHandler, never()).saveArticle(any(ArticleRequest.class));
    }
    @Test
    void saveArticle_ShouldReturnBadRequest_WhenPriceIsNegative() throws Exception {
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setName("Article with negative price");
        articleRequest.setDescription("Description of the article with negative price");
        articleRequest.setPrice(-10.0); // Assuming price is a field in ArticleRequest

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(articleRequest)));

        result.andExpect(status().isBadRequest());

        verify(articleHandler, never()).saveArticle(any(ArticleRequest.class));
    }
}
