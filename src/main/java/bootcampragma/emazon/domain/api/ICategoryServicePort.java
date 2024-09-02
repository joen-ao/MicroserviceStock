package bootcampragma.emazon.domain.api;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.util.CustomPage;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    CustomPage<Category> getAllCategory(Integer page, Integer size, String sortDirection);
}