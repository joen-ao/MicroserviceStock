package BootcampPragma.emazon.domain.spi;


import BootcampPragma.emazon.domain.entity.Category;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);


}