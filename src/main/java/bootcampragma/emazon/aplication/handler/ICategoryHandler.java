package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.CategoryRequest;
import bootcampragma.emazon.aplication.dto.CategoryResponse;
import bootcampragma.emazon.domain.util.CustomPage;


public interface ICategoryHandler {

    CategoryResponse saveCategory(CategoryRequest categoryRequest);
    CustomPage<CategoryResponse> getAllCategory(Integer page, Integer size, String sortDirection);

}