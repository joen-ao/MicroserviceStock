package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort{

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        categoryPersistencePort.saveCategory(category);
    }
    @Override
    public List<Category> getAllCategory(Integer page, Integer size, String sortDirection) {
        return categoryPersistencePort.getAllCategory(page, size, sortDirection);
    }

}

