package BootcampPragma.emazon.domain.usecase;

import BootcampPragma.emazon.domain.api.ICategoryServicePort;
import BootcampPragma.emazon.domain.entity.Category;
import BootcampPragma.emazon.domain.spi.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort{

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        categoryPersistencePort.saveCategory(category);
    }

}

