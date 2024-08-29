package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.CategoryRequest;
import bootcampragma.emazon.aplication.dto.CategoryResponse;

import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> getAllCategory(Integer page, Integer size, String sortDirection);
}