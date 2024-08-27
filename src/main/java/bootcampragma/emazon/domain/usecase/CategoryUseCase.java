package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;

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