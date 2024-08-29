package bootcampragma.emazon.domain.spi;


import bootcampragma.emazon.domain.entity.Category;

import java.util.List;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);
    List<Category> getAllCategory(Integer page, Integer size, String sortDirection);
}