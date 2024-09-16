package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.request.CategoryRequest;
import bootcampragma.emazon.aplication.dto.response.CategoryResponse;
import bootcampragma.emazon.aplication.handler.interfaces.ICategoryHandler;
import bootcampragma.emazon.aplication.mapper.request.CategoryRequestMapper;
import bootcampragma.emazon.aplication.mapper.response.CategoryResponseMapper;
import bootcampragma.emazon.aplication.util.AplicationConstants;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.exception.category.InvalidSortDirectionException;
import bootcampragma.emazon.domain.util.CustomPage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
            throw new IllegalArgumentException(AplicationConstants.CATEGORY_CANNOT_BE_NULL);
        }
        categoryServicePort.saveCategory(categoryRequestMapper.toRequest(categoryRequest));
    }

    @Override
    public CustomPage<CategoryResponse> getAllCategory(Integer page, Integer size, String sortDirection) {
        if (page < 0) {//MOVER VALIDACIONES
            throw new IllegalArgumentException(AplicationConstants.PAGE_NUMBER_ERROR);
        }
        if (!sortDirection.equalsIgnoreCase(AplicationConstants.ASC) && !sortDirection.equalsIgnoreCase(AplicationConstants.DESC)) {
            throw new InvalidSortDirectionException(AplicationConstants.INVALID_SORT_DIRECTION);
        }
        if(size == null || size < 0){
            throw new IllegalArgumentException(AplicationConstants.SIZE_NUMBER_EXCEPTION);
        }

        CustomPage<Category> categoryPage = categoryServicePort.getAllCategory(page, size, sortDirection);


        return categoryResponseMapper.toResponseList(categoryPage);
    }
}
