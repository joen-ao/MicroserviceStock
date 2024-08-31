package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.CategoryRequest;
import bootcampragma.emazon.aplication.dto.CategoryResponse;
import bootcampragma.emazon.aplication.mapper.CategoryResponseMapper;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.util.CustomPage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryResponseMapper categoryResponseMapper;

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        // Logic to save the category and return a CategoryResponse
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(1L); // Example of generated ID
        categoryResponse.setName(categoryRequest.getName());
        categoryResponse.setDescription(categoryRequest.getDescription());
        return categoryResponse;
    }

    @Override
    public CustomPage<CategoryResponse> getAllCategory(Integer page, Integer size, String sortDirection) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }

        CustomPage<Category> categoryPage = categoryServicePort.getAllCategory(page, size, sortDirection);

        List<CategoryResponse> categoryResponses = categoryResponseMapper.toResponseList(categoryPage.getContent());

        return new CustomPage<>(
                categoryResponses,
                categoryPage.getPageNumber(),
                categoryPage.getPageSize(),
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages()
        );
    }
}
