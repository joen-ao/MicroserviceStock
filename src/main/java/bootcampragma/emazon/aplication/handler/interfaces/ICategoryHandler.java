package bootcampragma.emazon.aplication.handler.interfaces;

import bootcampragma.emazon.aplication.dto.request.CategoryRequest;
import bootcampragma.emazon.aplication.dto.response.CategoryResponse;
import bootcampragma.emazon.domain.util.CustomPage;


public interface ICategoryHandler {

    void saveCategory(CategoryRequest categoryRequest);
    CustomPage<CategoryResponse> getAllCategory(Integer page, Integer size, String sortDirection);

}