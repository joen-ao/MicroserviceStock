package bootcampragma.emazon.domain.spi;

import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.domain.entity.Category;


public interface ICategoryPersistencePort {

    void saveCategory(Category category);
    CustomPage<Category> getAllCategory(Integer page, Integer size, String sortDirection);
}