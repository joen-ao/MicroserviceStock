package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.request.CategoryRequest;
import bootcampragma.emazon.aplication.dto.response.CategoryResponse;
import bootcampragma.emazon.aplication.handler.interfaces.ICategoryHandler;
import bootcampragma.emazon.aplication.mapper.request.CategoryRequestMapper;
import bootcampragma.emazon.aplication.mapper.response.CategoryResponseMapper;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.exception.category.InvalidSortDirectionException;
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
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            throw new IllegalArgumentException("Brand request cannot be null");
        }
        categoryServicePort.saveCategory(categoryRequestMapper.toRequest(categoryRequest));
    }

    @Override
    public CustomPage<CategoryResponse> getAllCategory(Integer page, Integer size, String sortDirection) {
        if (page < 0) {//MOVER VALIDACIONES
            throw new IllegalArgumentException("Page number cannot be negative");
        }
        if (!sortDirection.equalsIgnoreCase("asc") && !sortDirection.equalsIgnoreCase("desc")) {
            throw new InvalidSortDirectionException("Invalid sort direction");
        }
        if(size == null || size < 0){
            throw new IllegalArgumentException("Size number cannot be negative and null");
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
