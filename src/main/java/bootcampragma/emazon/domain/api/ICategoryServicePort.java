package bootcampragma.emazon.domain.api;

import bootcampragma.emazon.domain.entity.Category;

import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(Category category);
    List<Category> getAllCategory(Integer page, Integer size, String sortDirection);

}
