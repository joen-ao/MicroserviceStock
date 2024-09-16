package bootcampragma.emazon.domain.spi;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.util.CustomPage;


public interface ICategoryPersistencePort {

    void saveCategory(Category category);
    CustomPage<Category> getAllCategory(Integer page, Integer size, String sortDirection);
    boolean existsById(Long id);
    boolean findByName(String name);

}