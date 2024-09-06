package bootcampragma.emazon.infrastructure.input.rest;

import bootcampragma.emazon.aplication.dto.request.BrandRequest;
import bootcampragma.emazon.aplication.dto.response.BrandResponse;
import bootcampragma.emazon.aplication.handler.interfaces.IBrandHandler;
import bootcampragma.emazon.domain.util.CustomPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = BrandRestController.class)
@Import(bootcampragma.emazon.infrastructure.configuration.TestConfig.class)
class BrandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IBrandHandler brandHandler;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        BrandRestController brandRestController = new BrandRestController(brandHandler);
        mockMvc = MockMvcBuilders.standaloneSetup(brandRestController).build();
    }

    @Test
    void saveBrand_ShouldReturnBadRequest_WhenInvalidInput() throws Exception {
        // Arrange
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName(""); // Invalid name

        // Act
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brandRequest)));

        // Assert
        result.andExpect(status().isBadRequest());
    }

    @Test
    void saveBrand_ShouldReturnCreated_WhenValidInput() throws Exception {
        // Arrange
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Nike");
        brandRequest.setDescription("Sports brand");

        // Act
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brandRequest)));

        // Assert
        result.andExpect(status().isCreated());
        verify(brandHandler, times(1)).saveBrand(any(BrandRequest.class));
    }



    @Test
    void getAllBrand_ShouldReturnNotFound_WhenNoBrands() throws Exception {
        // Arrange
        CustomPage<BrandResponse> emptyPage = new CustomPage<>(Collections.emptyList(), 0, 10, 0L, 0);
        when(brandHandler.getAllBrand(anyInt(), anyInt(), anyString())).thenReturn(emptyPage);

        // Act
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/brand/all")
                .param("page", "0")
                .param("size", "10")
                .param("sortDirection", "asc")
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isNotFound());
    }


}
