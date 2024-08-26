package bootcampragma.emazon.domain.spi;


import bootcampragma.emazon.domain.entity.Category;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);


}